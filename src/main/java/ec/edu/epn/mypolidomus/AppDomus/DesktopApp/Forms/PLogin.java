package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyButton;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyLabel;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyLabelLink;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyPassBox;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyTextBox;
import ec.edu.epn.mypolidomus.BusinessLogic.Entities.UsuarioBL;
import ec.edu.epn.mypolidomus.BusinessLogic.Sistema.ArduinoConector;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class PLogin extends VBox {

    private MyTextBox txtUsuario;
    private MyPassBox txtPassword;
    private MyButton  btnLogin;
    private MyLabelLink lblForgot;

    // Instancia de ArduinoConector (inicializada en hilo separado)
    private ArduinoConector arduino;

    public PLogin() {
        initUI();
        // Inicializar Arduino en un hilo separado para no bloquear la UI
        initializeArduinoAsync();
    }

    private void initializeArduinoAsync() {
        Thread arduinoThread = new Thread(() -> {
            try {
                CMD.println("PLogin ❱❱ Inicializando Arduino en hilo separado...");
                arduino = new ArduinoConector();
                CMD.println("PLogin ❱❱ Arduino inicializado correctamente");
            } catch (AppException e) {
                CMD.printlnError("PLogin ❱❱ Error al inicializar Arduino: " + e.getMessage());
                Platform.runLater(() -> {
                    // Mostrar advertencia pero permitir que la UI continúe
                    CMD.printlnError("Arduino no disponible - modo offline");
                });
            }
        });
        arduinoThread.setDaemon(true);
        arduinoThread.setName("ArduinoInitThread");
        arduinoThread.start();
    }

    private void initUI() {
        // ===== Configuración del panel =====
        setSpacing(15);
        setAlignment(AppStyle.CENTER);
        setPadding(AppStyle.createPadding());
        setMaxWidth(360);
        setBorder(AppStyle.createBorderRect());

        // ===== Título =====
        MyLabel lblTitle = new MyLabel("Iniciar Sesión");
        lblTitle.setFont(AppStyle.FONT_BOLD);
        lblTitle.setTextFill(AppStyle.COLOR_FONT);

        // ===== Usuario =====
        MyLabel lblUsuario = new MyLabel("Usuario");
        txtUsuario = new MyTextBox();
        txtUsuario.setPromptText("Ingrese su usuario");

        // ===== Contraseña =====
        MyLabel lblPassword = new MyLabel("Contraseña");
        txtPassword = new MyPassBox();
        txtPassword.setPromptText("Ingrese su contraseña");

        // ===== Botón Login =====
        btnLogin = new MyButton("Ingresar");
        btnLogin.setBackground(AppStyle.createButtonBackground(AppStyle.COLOR_BUTTON_BG));
        btnLogin.setTextFill(AppStyle.COLOR_BUTTON_TEXT);
        btnLogin.setCursor(AppStyle.CURSOR_HAND);
        btnLogin.setMaxWidth(Double.MAX_VALUE);

        // ===== Link Olvido =====
        lblForgot = new MyLabelLink("¿Olvidaste tu contraseña?");
        lblForgot.setAlignment(Pos.CENTER_RIGHT);

        // ===== Acciones =====
        btnLogin.setOnAction(e -> {
            try {
                onLogin();
            } catch (AppException e1) {
                e1.printStackTrace();
            }
        });
        lblForgot.setOnMouseClicked(e -> onForgotPassword());

        // ===== Agregar componentes =====
        getChildren().addAll(
                lblTitle,
                lblUsuario,
                txtUsuario,
                lblPassword,
                txtPassword,
                btnLogin,
                lblForgot
        );
    }

    private void onLogin() throws AppException {
        String usuario = txtUsuario.getText().trim();
        String claveUsuario = txtPassword.getText();

        if (usuario.isEmpty() || claveUsuario.isEmpty()) {
            System.out.println("Debe ingresar usuario y contraseña");
            return;
        }

        UsuarioBL bl = new UsuarioBL();
        try {
            // Validamos usuario + contraseña
            boolean accesoConcedido = bl.validarCredenciales(claveUsuario);

            if (accesoConcedido) {
                System.out.println("Acceso concedido");

                // Enviar señal a Arduino para abrir la puerta (si está disponible)
                if (arduino != null && arduino.estaConectado()) {
                    arduino.enviarAbrirPuerta();
                    arduino.enviarBuzzerOff();
                }

            } else {
                System.out.println("Usuario o contraseña incorrectos");

                // Señal de acceso denegado en Arduino (si está disponible)
                if (arduino != null && arduino.estaConectado()) {
                    arduino.enviarDenegado();
                    arduino.enviarBuzzerOn();
                }
            }

        } catch (AppException e) {
            e.printStackTrace();
            System.out.println("Error al validar el login");
        }
    }

    private void onForgotPassword() {
        System.out.println("Recuperar contraseña");
    }
}

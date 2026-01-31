package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyButton;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyLabel;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyLabelLink;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyPassBox;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyTextBox;
import ec.edu.epn.mypolidomus.BusinessLogic.Entities.UsuarioBL;
import ec.edu.epn.mypolidomus.BusinessLogic.Sistema.ArduinoConector;
import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioDTO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import ec.edu.epn.mypolidomus.Infrastructure.AppMSG;
import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import ec.edu.epn.mypolidomus.Infrastructure.HistorialAccesosService;
import ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class PLogin extends VBox {

    private MyTextBox txtCorreo;
    private MyPassBox txtPassword;
    private MyButton  btnLogin;
    private MyLabelLink lblForgot;

    private final AppStart appStart;
    private ArduinoConector arduino;

    public PLogin(AppStart appStart) {
        this.appStart = appStart;
        initUI();
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

        // ===== Correo =====
        MyLabel lblCorreo = new MyLabel("Correo");
        txtCorreo = new MyTextBox();
        txtCorreo.setPromptText("Ingrese su correo");

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

        getChildren().addAll(
                lblTitle,
                lblCorreo,
                txtCorreo,
                lblPassword,
                txtPassword,
                btnLogin,
                lblForgot
        );
    }

    private void onLogin() throws AppException {
        String correo = txtCorreo.getText().trim();
        String clave = txtPassword.getText();

        if (correo.isEmpty() || clave.isEmpty()) {
            AppMSG.show("Debe ingresar correo y contraseña");
            return;
        }

        UsuarioBL bl = new UsuarioBL();
        try {
            UsuarioDTO usuario = bl.validarCredencialesPorCorreo(correo, clave);

            if (usuario != null) {
                HistorialAccesosService.registrarAcceso(true);
                if (arduino != null && arduino.estaConectado()) {
                    arduino.enviarAbrirPuerta();
                    arduino.enviarBuzzerOff();
                }
                appStart.setCurrentUser(usuario);
                appStart.setPanel(new PHome(appStart, usuario), appStart.getRoot());
            } else {
                HistorialAccesosService.registrarAcceso(false);
                if (arduino != null && arduino.estaConectado()) {
                    arduino.enviarDenegado();
                    arduino.enviarBuzzerOn();
                }
                AppMSG.showError("Correo o contraseña incorrectos");
            }
        } catch (AppException e) {
            HistorialAccesosService.registrarAcceso(false);
            AppMSG.showError("Error al validar: " + e.getMessage());
        }
    }

    private void onForgotPassword() {
        System.out.println("Recuperar contraseña");
    }
}

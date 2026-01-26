package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyTextBox;
import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class PLogin extends VBox {

    private MyTextBox txtUsuario;
    private MyTextBox txtPassword;
    private MyButton  btnLogin;
    private MyLabelLink lblForgot;

    public PLogin() {
        initUI();
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
        txtPassword = new MyTextBox();
        txtPassword.setPromptText("Ingrese su contraseña");
        txtPassword.setPasswordMode(true); // asumido en tu control

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
        btnLogin.setOnAction(e -> onLogin());
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

    private void onLogin() {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        // Aquí va tu lógica de autenticación
        System.out.println("Login: " + user);
    }

    private void onForgotPassword() {
        System.out.println("Recuperar contraseña");
    }
}

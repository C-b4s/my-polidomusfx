package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;

import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.scene.control.PasswordField;

// Heredamos de PasswordField para tener la seguridad nativa de JavaFX
public class MyPassBox extends PasswordField {

    public MyPassBox() {
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(AppStyle.FONT);
        String textFill = AppStyle.toCssColor(AppStyle.COLOR_FONT_LIGHT);
        String promptFill = "rgba(255,255,255,0.9)";
        String borderColor = AppStyle.toCssColor(AppStyle.COLOR_BORDER);
        setStyle(
            "-fx-text-fill: " + textFill + ";" +
            "-fx-prompt-text-fill: " + promptFill + ";" +
            "-fx-background-color: transparent;" +
            "-fx-border-color: " + borderColor + ";" +
            "-fx-border-width: 0 0 1 0;" +
            "-fx-padding: 4 4 4 4;" +
            "-fx-font-size: 14px;"
        );
    }
}
package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;

import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.scene.control.PasswordField;

// Heredamos de PasswordField para tener la seguridad nativa de JavaFX
public class MyPassBox extends PasswordField {

    public MyPassBox() {
        customizeComponent();
    }

    private void customizeComponent() {
        // Usamos EXACTAMENTE el mismo estilo que en MyTextBox
        setFont(AppStyle.FONT);
        setStyle(
            "-fx-text-fill: " + AppStyle.COLOR_FONT_LIGHT + ";" +
            "-fx-prompt-text-fill: " + AppStyle.COLOR_FONT_LIGHT + ";" +
            "-fx-background-color: transparent;" +
            "-fx-border-color: " + AppStyle.COLOR_BORDER + ";" +
            "-fx-border-width: 0 0 1 0;" + 
            "-fx-padding: 4 4 4 4;"
        );
    }
}
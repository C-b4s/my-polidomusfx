package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;

import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.scene.control.TextField;

public class MyTextBox extends TextField {

    public MyTextBox() {
        customizeComponent();
    }

    private void customizeComponent() {

        // Fuente y color
        setFont(AppStyle.FONT);
        setStyle(
            "-fx-text-fill: " + AppStyle.COLOR_FONT_LIGHT + ";" +
            "-fx-prompt-text-fill: " + AppStyle.COLOR_FONT_LIGHT + ";" +
            "-fx-background-color: transparent;" +
            "-fx-border-color: " + AppStyle.COLOR_BORDER + ";" +
            "-fx-border-width: 0 0 1 0;" +     // solo línea inferior
            "-fx-padding: 4 4 4 4;"
        );
    }
}

package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;

import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class MyLabelText extends VBox {

    private MyLabel   lblEtiqueta = new MyLabel();
    private MyTextBox txtContenido = new MyTextBox();

    public MyLabelText(String etiqueta) {

        // Espaciado y alineación
        setSpacing(4);
        setAlignment(Pos.TOP_LEFT);

        // Configuración del label
        lblEtiqueta.setCustomizeComponent(
                etiqueta,
                AppStyle.FONT_SMALL,
                AppStyle.COLOR_FONT_LIGHT,
                Pos.CENTER_LEFT
        );

        getChildren().addAll(lblEtiqueta, txtContenido);
    }

    // Accesos útiles
    public String getText() {
        return txtContenido.getText();
    }

    public void setText(String text) {
        txtContenido.setText(text);
    }
}

package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;

import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class MyLabelLink extends Label {

    public MyLabelLink(String text) {
        super(text);
        setPersonalizacion();
    }

    public MyLabelLink(String text, String iconPath) {
        super(text);
        setPersonalizacion();
        setIcon(iconPath);
    }

    private void setPersonalizacion() {

        setTextFill(AppStyle.COLOR_FONT);

        setOnMouseClicked(this::mouseClicked);
        setOnMousePressed(this::mousePressed);
        setOnMouseReleased(this::mouseReleased);
        setOnMouseEntered(this::mouseEntered);
        setOnMouseExited(this::mouseExited);
    }

    private void setIcon(String iconPath) {
        Image image = new Image(
                getClass().getResource(iconPath).toExternalForm()
        );
        ImageView imageView = new ImageView(image);
        setGraphic(imageView); 
    }

    /* ======================
       Eventos de Mouse
       ====================== */

    private void mouseClicked(MouseEvent e) {
        // code
    }

    private void mousePressed(MouseEvent e) {
        // code
    }

    private void mouseReleased(MouseEvent e) {
        // code
    }

    private void mouseEntered(MouseEvent e) {
        setCursor(AppStyle.CURSOR_HAND);
    }

    private void mouseExited(MouseEvent e) {
        setCursor(AppStyle.CURSOR_DEFAULT);
    }
}
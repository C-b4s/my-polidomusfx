package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.Infrastructure.AppConfig;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import ec.edu.epn.mypolidomus.Infrastructure.AppMSG;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PHome extends Pane {

    private ImageView imageView;

    public PHome() {
        try {
            Image image = new Image(AppConfig.getImgMain().toURI().toString());

            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);

            // Ajustar la imagen al tamaño del Pane
            imageView.fitWidthProperty().bind(widthProperty());
            imageView.fitHeightProperty().bind(heightProperty());

            getChildren().add(imageView);

        } catch (Exception e) {
            AppException er = new AppException(
                    "Ups..! Error en la imagen de inicio",
                    e,
                    getClass(),
                    "PHome()"
            );
            AppMSG.showError(er.getMessage());
        }
    }
}

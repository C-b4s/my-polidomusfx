package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.Infrastructure.AppConfig;
import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppSplashScreen extends VBox {
    private static final double SCALE_PERCENT = 0.6; // Aumentado a 60% para mejor visibilidad
    private static final double IMG_WIDTH = 300;
    private ProgressBar progressBar;

    public AppSplashScreen() throws Exception {
        initializeSplashScreen();
    }

    private void initializeSplashScreen() throws Exception {
        Image splashImage = new Image(AppConfig.getImgSplash().toExternalForm());

        ImageView imageView = new ImageView(splashImage);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(IMG_WIDTH);

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(IMG_WIDTH);
        progressBar.setPrefHeight(10);

        setSpacing(15);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setBackground(
            new Background(
                new BackgroundFill(Color.WHITE, new CornerRadii(12), Insets.EMPTY)
            )
        );
        
        // Establecer tamaño fijo del VBox
        setPrefWidth(IMG_WIDTH + 60);
        setPrefHeight(IMG_WIDTH * 0.75 + 80);
        
        getChildren().addAll(imageView, progressBar);
    }

    public void startLoading(Stage stage) {
        simulateLoading(progressBar, stage);
    }

    private void simulateLoading(ProgressBar progressBar, Stage stage) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(40), e -> {
                progressBar.setProgress(progressBar.getProgress() + 0.01);
            })
        );
        timeline.setCycleCount(100);

        timeline.setOnFinished(e -> {
            // Esperar 500ms más antes de cerrar
            Timeline closeDelay = new Timeline(
                new KeyFrame(Duration.millis(500), evt -> {
                    stage.close();
                })
            );
            closeDelay.play();
        });
        timeline.play();
    }
}


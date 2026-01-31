package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.Infrastructure.AppConfig;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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

import java.net.URL;

public class AppSplashScreen extends VBox {
    private static final double IMG_WIDTH = 300;
    private ProgressBar progressBar;
    private Label lblProgress;

    public AppSplashScreen() throws Exception {
        initializeSplashScreen();
    }

    private void initializeSplashScreen() throws Exception {
        ImageView imageView;
        URL splashUrl = AppConfig.getImgSplash();
        if (splashUrl != null) {
            Image splashImage = new Image(splashUrl.toExternalForm());
            imageView = new ImageView(splashImage);
        } else {
            imageView = new ImageView();
            imageView.setStyle("-fx-background-color: #e0e0e0;");
        }
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(IMG_WIDTH);

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(IMG_WIDTH);
        progressBar.setPrefHeight(14);
        progressBar.setStyle(
            "-fx-accent: #1565c0;" +
            "-fx-control-inner-background: #e3f2fd;" +
            "-fx-background-color: #e3f2fd;" +
            "-fx-border-color: #1565c0;" +
            "-fx-border-width: 1;"
        );

        lblProgress = new Label("Cargando 0%");
        lblProgress.setStyle("-fx-text-fill: #333333; -fx-font-size: 12px;");

        setSpacing(12);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setBackground(
            new Background(
                new BackgroundFill(Color.WHITE, new CornerRadii(12), Insets.EMPTY)
            )
        );

        setPrefWidth(IMG_WIDTH + 60);
        setPrefHeight(IMG_WIDTH * 0.75 + 100);

        getChildren().addAll(imageView, lblProgress, progressBar);
    }

    /**
     * Inicia la animación de carga. Al terminar (100%) se ejecuta onFinished y se cierra el splash.
     * @param stage ventana del splash (se cierra al final)
     * @param onFinished callback que se ejecuta al terminar la carga (ej. mostrar ventana principal)
     */
    public void startLoading(Stage stage, Runnable onFinished) {
        simulateLoading(progressBar, lblProgress, stage, onFinished != null ? onFinished : () -> stage.close());
    }

    private void simulateLoading(ProgressBar progressBar, Label lblProgress, Stage stage, Runnable onFinished) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(40), e -> {
                double p = progressBar.getProgress() + 0.01;
                progressBar.setProgress(p);
                int percent = (int) (p * 100);
                lblProgress.setText("Cargando " + percent + "%");
            })
        );
        timeline.setCycleCount(100);

        timeline.setOnFinished(e -> {
            lblProgress.setText("Cargando 100%");
            Timeline closeDelay = new Timeline(
                new KeyFrame(Duration.millis(500), evt -> {
                    stage.close();
                    onFinished.run();
                })
            );
            closeDelay.play();
        });
        timeline.play();
    }
}


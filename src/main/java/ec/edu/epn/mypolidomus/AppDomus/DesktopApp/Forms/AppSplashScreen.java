package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.Infrastructure.AppConfig;
import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AppSplashScreen extends Application {
    private static final double SCALE_PERCENT = 0.4; // % del tamaño original de la imagen

    @Override
    public void start(Stage stage) throws Exception {

        Image splashImage = new Image(AppConfig.getImgSplash().toExternalForm());

        ImageView imageView = new ImageView(splashImage);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(splashImage.getWidth() *SCALE_PERCENT);

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(imageView.getFitWidth());

        VBox root = new VBox(8, imageView, progressBar);
        root.setAlignment(Pos.CENTER);
        root.setPadding((AppStyle.createPadding()));
        root.setBackground(
            new Background(
                new BackgroundFill(Color.WHITE, new CornerRadii(12), Insets.EMPTY)
            )
        );

        Scene scene = new Scene (root);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        simulateLoading(progressBar,stage);
    }
    private void simulateLoading(ProgressBar progressBar, Stage stage){
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(40), e ->{
                progressBar.setProgress(progressBar.getProgress()+0.01);
            })
        );
        timeline.setCycleCount(100);

        timeline.setOnFinished(e ->{
            stage.close();
        });
        timeline.play();
    }
    
   
}

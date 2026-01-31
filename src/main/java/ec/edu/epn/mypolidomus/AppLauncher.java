package ec.edu.epn.mypolidomus;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms.AppSplashScreen;
import ec.edu.epn.mypolidomus.Controllers.PrimaryController;
import ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppLauncher extends Application {
    @Override
    public void start(Stage primaryStage) {
        CMD.println("AppLauncher ❱❱ Iniciando aplicación...");
        try {
            CMD.println("AppLauncher ❱❱ Mostrando splash screen...");
            AppSplashScreen splashScreen = new AppSplashScreen();
            Stage splashStage = new Stage();
            splashStage.initStyle(StageStyle.UNDECORATED);
            Scene splashScene = new Scene(splashScreen);
            splashStage.setScene(splashScene);
            splashStage.setWidth(420);
            splashStage.setHeight(400);
            splashStage.centerOnScreen();
            splashStage.show();
            splashStage.toFront();

            CMD.println("AppLauncher ❱❱ Splash screen mostrado");

            // Cuando el splash termine (100%), cerrar splash y mostrar AppStart
            Runnable onSplashFinished = () -> {
                splashStage.close();
                showAppStart(primaryStage);
            };

            Platform.runLater(() -> splashScreen.startLoading(splashStage, onSplashFinished));

        } catch (Exception e) {
            CMD.printlnError("ERROR AppLauncher ❱❱ " + e.getMessage());
            e.printStackTrace();
            showAppStart(primaryStage);
        }
    }

    private void showAppStart(Stage primaryStage) {
        try {
            CMD.println("AppLauncher ❱❱ Inicializando AppStart...");
            PrimaryController primary = new PrimaryController();
            primary.initialize();

            Scene scene = new Scene(primary.getRoot(), 930, 600);
            primaryStage.setTitle("Polidomus FX");
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
            primaryStage.toFront();

            CMD.println("AppLauncher ❱❱ Aplicación iniciada correctamente");
        } catch (Exception e) {
            CMD.printlnError("ERROR AppLauncher ❱❱ (Inicialización) " + e.getMessage());
            e.printStackTrace();
        }
    }
}

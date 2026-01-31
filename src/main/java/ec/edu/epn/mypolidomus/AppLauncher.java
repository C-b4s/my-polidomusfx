package ec.edu.epn.mypolidomus;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms.AppSplashScreen;
import ec.edu.epn.mypolidomus.Controllers.PrimaryController;
import ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AppLauncher extends Application {
    @Override
    public void start(Stage stage) {
        CMD.println("AppLauncher ❱❱ Iniciando aplicación...");
        try {
            // Mostrar splash screen en ventana separada
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
            
            CMD.println("AppLauncher ❱❱ Splash screen mostrado");
            
            // Iniciar animación del splash (dura ~4 segundos)
            splashScreen.startLoading(splashStage);
            
            // Esperar a que el splash termine antes de mostrar la ventana principal
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                try {
                    CMD.println("AppLauncher ❱❱ Inicializando controlador principal...");
                    PrimaryController primary = new PrimaryController();
                    primary.initialize();
                    
                    // Crear escena y mostrar ventana principal
                    Scene scene = new Scene(primary.getRoot(), 930, 600);
                    stage.setTitle("Polidomus FX");
                    stage.setScene(scene);
                    stage.show();
                    
                    CMD.println("AppLauncher ❱❱ Aplicación iniciada correctamente");
                } catch (Exception e) {
                    CMD.printlnError("ERROR AppLauncher ❱❱ (Inicialización) " + e.getMessage());
                    e.printStackTrace();
                }
            });
            pause.play();
            
        } catch (Exception e) {
            CMD.printlnError("ERROR AppLauncher ❱❱ " + e.getMessage());
            e.printStackTrace();
        }
    }
}

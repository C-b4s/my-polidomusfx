package ec.edu.epn.mypolidomus;

import ec.edu.epn.mypolidomus.Controllers.PrimaryController;
import ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
    @Override
    public void start(Stage stage) {
        CMD.println("AppLauncher ❱❱ Iniciando aplicación...");
        try {
            PrimaryController primary = new PrimaryController();
            primary.initialize();
            
            // Crear escena y mostrar ventana
            Scene scene = new Scene(primary.getRoot(), 930, 600);
            stage.setTitle("Polidomus FX");
            stage.setScene(scene);
            stage.show();
            
            CMD.println("AppLauncher ❱❱ Aplicación iniciada correctamente");
        } catch (Exception e) {
            CMD.printlnError("ERROR AppLauncher ❱❱ " + e.getMessage());
            e.printStackTrace();
        }
    }
}

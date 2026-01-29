package ec.edu.epn.mypolidomus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App {

    private static Stage stage;

    public static void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setScene(new Scene(loadFXML("primary")));
        stage.show();
    }

    public static void setRoot(String fxml) {
        stage.getScene().setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) {
        try {
            return FXMLLoader.load(
                App.class.getResource(fxml + ".fxml")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

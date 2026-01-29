package ec.edu.epn.mypolidomus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms.AppSplashScreen;
import ec.edu.epn.mypolidomus.DataAccess.DAOs.EstadoDAO;
import ec.edu.epn.mypolidomus.DataAccess.DTOs.EstadoDTO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void init() throws AppException {
        EstadoDAO eDao = new EstadoDAO();
        for (EstadoDTO dto : eDao.readAll()) {
            System.out.println(dto);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader =
            new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return loader.load();
    }
    //private static Scene scene;
//
    //@Override
    //public void start(Stage stage) throws IOException {
    //    scene = new Scene(loadFXML("primary"), 640, 480);
    //    stage.setScene(scene);
    //    stage.show();
    //}
//
    //static void setRoot(String fxml) throws IOException {
    //    scene.setRoot(loadFXML(fxml));
    //}
//
    //private static Parent loadFXML(String fxml) throws IOException {
    //    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    //    return fxmlLoader.load();
    //}
//
    //public static void main(String[] args) throws AppException {
    //    launch();
    //    EstadoDAO eDao = new EstadoDAO();
    //    EstadoDTO eDTO = new EstadoDTO();
//
    //    for (EstadoDTO dto : eDao.readAll())
    //        System.out.println(eDTO.toString());
    //}

}
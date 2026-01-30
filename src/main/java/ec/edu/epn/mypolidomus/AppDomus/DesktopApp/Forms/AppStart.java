package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyButton;
import ec.edu.epn.mypolidomus.BusinessLogic.Sistema.ArduinoConector;
import ec.edu.epn.mypolidomus.Infrastructure.AppMSG;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppStart extends Application {
    private AppMenu   pnlMenu = new AppMenu();
    private PHome   pnlMain = new PHome();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setLeft(pnlMenu);
        root.setCenter(pnlMain);

        MyButton btnHome         = new MyButton("HOME");
        MyButton btnTest         = new MyButton("Aaaaaaaa");

        btnHome.setOnAction(e -> setPanel(new PHome(),root));
        btnTest.setOnAction(e -> AppMSG.showError("Mensaje de error"));

        pnlMenu.addMenuItem(btnHome);
        pnlMenu.addMenuItem(btnTest);

        Scene scene = new Scene(root,930,600);
        stage.setTitle(("My PoliDomus"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void setPanel(javafx.scene.layout.Pane newPanel , BorderPane root){
        root.setCenter(new PLogin(new ArduinoConector(null, null);
    }
}
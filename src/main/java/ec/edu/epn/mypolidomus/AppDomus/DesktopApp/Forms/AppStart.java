package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyButton;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import ec.edu.epn.mypolidomus.Infrastructure.AppMSG;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AppStart extends VBox {
    private AppMenu pnlMenu;
    private PLogin pnlLogin;
    private BorderPane root;

    public AppStart() throws AppException {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() throws AppException {
        pnlMenu = new AppMenu();
        pnlLogin = new PLogin();
    }

    private void setupLayout() {
        root = new BorderPane();
        root.setLeft(pnlMenu);
        root.setCenter(pnlLogin);

        MyButton btnHome = new MyButton("HOME");
        MyButton btnLogout = new MyButton("LOGOUT");
        MyButton btnTest = new MyButton("Test");

        btnHome.setOnAction(e -> {
            try {
                setPanel(new PHome(this), root);
            } catch (AppException e1) {
                e1.printStackTrace();
            }
        });
        
        btnLogout.setOnAction(e -> {
            try {
                setPanel(new PLogin(), root);
            } catch (AppException e1) {
                e1.printStackTrace();
            }
        });
        
        btnTest.setOnAction(e -> AppMSG.showError("Mensaje de error"));

        pnlMenu.addMenuItem(btnHome);
        pnlMenu.addMenuItem(btnLogout);
        pnlMenu.addMenuItem(btnTest);

        setAlignment(Pos.CENTER);
        getChildren().add(root);
    }

    public void setPanel(javafx.scene.layout.Pane newPanel, BorderPane root) throws AppException {
        root.setCenter(newPanel);
    }
}
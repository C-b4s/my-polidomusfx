package ec.edu.epn.mypolidomus.Controllers;

import java.io.IOException;
import ec.edu.epn.mypolidomus.App;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms.AppStart;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PrimaryController extends VBox {
    private AppStart appStartPanel;

    public PrimaryController() {
        setStyle("-fx-background-color: #1e1e1e;");
        setPrefWidth(930);
        setPrefHeight(600);
    }

    @FXML
    public void initialize() throws Exception {
        CMD.println("PrimaryController ❱❱ Inicializando...");
        createAppStartPanel();
        getChildren().add(appStartPanel);
    }

    private void createAppStartPanel() throws AppException {
        CMD.println("PrimaryController ❱❱ Creando AppStart...");
        appStartPanel = new AppStart();
        appStartPanel.setPrefWidth(930);
        appStartPanel.setPrefHeight(600);
        CMD.println("PrimaryController ❱❱ AppStart creado correctamente");
    }

    public VBox getRoot() {
        return this;
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}

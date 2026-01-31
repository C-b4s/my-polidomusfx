package ec.edu.epn.mypolidomus.Controllers;

import java.io.IOException;

import ec.edu.epn.mypolidomus.App;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms.AppStart;
import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms.AppSplashScreen;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import javafx.fxml.FXML;

public class SecondaryController {
    private AppStart appStartPanel;
    private AppSplashScreen appSplashScreen;

    @FXML
    private void initialize() throws AppException, Exception {
        initializeAppStartPanel();
        initializeAppSplashScreen();
    }

    private void initializeAppStartPanel() throws AppException {
        appStartPanel = new AppStart();
        appStartPanel.setPrefWidth(930);
        appStartPanel.setPrefHeight(600);
    }

    private void initializeAppSplashScreen() throws Exception {
        appSplashScreen = new AppSplashScreen();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
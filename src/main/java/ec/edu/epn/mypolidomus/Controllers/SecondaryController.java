package ec.edu.epn.mypolidomus.Controllers;

import java.io.IOException;

import ec.edu.epn.mypolidomus.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
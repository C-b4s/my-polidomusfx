package ec.edu.epn.mypolidomus.Controllers;

import java.io.IOException;
import ec.edu.epn.mypolidomus.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}

package ec.edu.epn.mypolidomus.Infrastructure;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public abstract class AppMSG {
    private AppMSG() {}
    private static final String APP_NAME = "My Polidomus";
    public static final void show(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(APP_NAME);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();  
    }
    public static final void showError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(APP_NAME);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    public static final boolean showConfirmYesNo(String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(APP_NAME);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.YES;
    }
}

package ec.edu.epn.mypolidomus.Infrastructure;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public abstract class AppMSG {

    private static final String APP_NAME = "My Polidomus";

    private AppMSG() {}

    /* =========================
       INFO
       ========================= */
    public static void show(String msg) {
        runFX(() -> alert(Alert.AlertType.INFORMATION, msg));
    }

    /* =========================
       ERROR
       ========================= */
    public static void showError(String msg) {
        runFX(() -> alert(Alert.AlertType.ERROR,
                msg == null || msg.isBlank()
                        ? AppConfig.MSG_DEFAULT_ERROR
                        : msg));
    }

    /* =========================
       CONFIRM YES / NO
       ========================= */
    public static boolean showConfirmYesNo(String msg) {

        if (!Platform.isFxApplicationThread()) {
            final boolean[] result = new boolean[1];
            Platform.runLater(() ->
                    result[0] = confirm(msg)
            );
            return result[0];
        }

        return confirm(msg);
    }

    /* =========================
       HELPERS
       ========================= */
    private static void alert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(APP_NAME);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private static boolean confirm(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(APP_NAME);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    private static void runFX(Runnable action) {
        if (Platform.isFxApplicationThread())
            action.run();
        else
            Platform.runLater(action);
    }
}

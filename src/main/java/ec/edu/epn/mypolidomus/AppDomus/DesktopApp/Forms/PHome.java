package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyButton;
import ec.edu.epn.mypolidomus.BusinessLogic.Entities.UsuarioBL;
import ec.edu.epn.mypolidomus.BusinessLogic.Security.PasswordHasher;
import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioDTO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import ec.edu.epn.mypolidomus.Infrastructure.AppMSG;
import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import ec.edu.epn.mypolidomus.Infrastructure.HistorialAccesosService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Optional;

public class PHome extends VBox {

    private final AppStart appStart;
    private final UsuarioDTO usuario;
    private final StackPane contentArea;
    private final GridPane gridHistorial;

    public PHome(AppStart appStart, UsuarioDTO usuario) {
        this.appStart = appStart;
        this.usuario = usuario;
        setSpacing(15);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #1e1e1e;");

        String nombre = usuario != null ? usuario.getNombre() : "Usuario";
        Label lblWelcome = new Label("Bienvenido, " + nombre);
        lblWelcome.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 18px;");

        MyButton btnVerHistorial = new MyButton("Ver historial de accesos");
        btnVerHistorial.setBackground(AppStyle.createButtonBackground(AppStyle.COLOR_BUTTON_BG));
        btnVerHistorial.setTextFill(AppStyle.COLOR_BUTTON_TEXT);
        btnVerHistorial.setPrefWidth(220);

        MyButton btnCambiarClave = new MyButton("Cambiar contraseña");
        btnCambiarClave.setBackground(AppStyle.createButtonBackground(AppStyle.COLOR_BUTTON_BG));
        btnCambiarClave.setTextFill(AppStyle.COLOR_BUTTON_TEXT);
        btnCambiarClave.setPrefWidth(220);

        HBox botones = new HBox(15);
        botones.setAlignment(Pos.CENTER);
        botones.getChildren().addAll(btnVerHistorial, btnCambiarClave);

        contentArea = new StackPane();
        contentArea.setMinSize(600, 350);

        gridHistorial = buildGridHistorial();
        ScrollPane scroll = new ScrollPane(gridHistorial);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: #2b2b2b; -fx-background-color: #2b2b2b;");
        scroll.setVisible(false);

        contentArea.getChildren().add(scroll);

        btnVerHistorial.setOnAction(e -> {
            if (usuario == null) {
                AppMSG.show("Inicie sesión para ver el historial.");
                return;
            }
            actualizarGridHistorial();
            scroll.setVisible(true);
        });

        btnCambiarClave.setOnAction(e -> {
            if (usuario == null) {
                AppMSG.show("Inicie sesión para cambiar la contraseña.");
                return;
            }
            abrirDialogoCambiarClave();
        });

        getChildren().addAll(lblWelcome, botones, contentArea);
    }

    private GridPane buildGridHistorial() {
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(8);
        grid.setPadding(new Insets(15));
        grid.setStyle("-fx-background-color: #2b2b2b;");

        Label colNum = new Label("#");
        Label colFecha = new Label("Fecha y Hora");
        Label colResultado = new Label("Resultado");
        colNum.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        colFecha.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        colResultado.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        grid.add(colNum, 0, 0);
        grid.add(colFecha, 1, 0);
        grid.add(colResultado, 2, 0);

        return grid;
    }

    private void actualizarGridHistorial() {
        gridHistorial.getChildren().removeIf(node -> {
            Integer row = GridPane.getRowIndex(node);
            return row != null && row >= 1;
        });

        List<String> lineas = HistorialAccesosService.leerLineas();
        int row = 1;
        for (int i = lineas.size() - 1; i >= 0; i--) {
            String linea = lineas.get(i).trim();
            if (linea.isEmpty()) continue;

            String fechaHora = "";
            String resultado = linea;
            if (linea.contains(" ACCESO ")) {
                int idx = linea.indexOf(" ACCESO ");
                fechaHora = linea.substring(0, idx).trim();
                resultado = linea.substring(idx + 1).trim();
            }

            Label lblNum = new Label(String.valueOf(row));
            Label lblFecha = new Label(fechaHora);
            Label lblRes = new Label(resultado);
            lblNum.setStyle("-fx-text-fill: #e0e0e0;");
            lblFecha.setStyle("-fx-text-fill: #e0e0e0;");
            lblRes.setStyle(resultado.contains("CORRECTO") ? "-fx-text-fill: #81c784;" : "-fx-text-fill: #e57373;");

            gridHistorial.add(lblNum, 0, row);
            gridHistorial.add(lblFecha, 1, row);
            gridHistorial.add(lblRes, 2, row);
            row++;
        }
    }

    private void abrirDialogoCambiarClave() {
        TextInputDialog dialogNueva = new TextInputDialog();
        dialogNueva.setTitle("Cambiar contraseña");
        dialogNueva.setHeaderText("Nueva contraseña");
        dialogNueva.setContentText("Nueva contraseña:");
        Optional<String> optNueva = dialogNueva.showAndWait();
        if (optNueva.isEmpty() || optNueva.get().isBlank()) return;

        String nueva = optNueva.get();
        TextInputDialog dialogConf = new TextInputDialog();
        dialogConf.setTitle("Confirmar contraseña");
        dialogConf.setHeaderText("Confirmar nueva contraseña");
        dialogConf.setContentText("Repita la contraseña:");
        Optional<String> optConf = dialogConf.showAndWait();
        if (optConf.isEmpty() || !optConf.get().equals(nueva)) {
            AppMSG.showError("Las contraseñas no coinciden.");
            return;
        }

        try {
            UsuarioDTO u = usuario;
            u.setContrasena(PasswordHasher.hash(nueva));
            UsuarioBL bl = new UsuarioBL();
            bl.upd(u);
            AppMSG.show("Contraseña actualizada correctamente.");
        } catch (AppException e) {
            AppMSG.showError("Error al actualizar: " + e.getMessage());
        }
    }
}

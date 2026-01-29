package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyButton;
import ec.edu.epn.mypolidomus.Infrastructure.AppConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

// CORRECCIÓN: Ahora heredamos de VBox (JavaFX) en lugar de JPanel (Swing)
public class AppMenu extends VBox {

    private final List<MyButton> menuItems = new ArrayList<>();
    private final VBox buttonsPanel = new VBox(); // VBox interno para los botones

    public AppMenu() {
        initComponents();
    }

    private void initComponents() {
        // ===== Configuración del Panel Principal =====
        // Equivalente a setLayout(BoxLayout.Y_AXIS) y setPreferredSize
        setSpacing(10); // Espacio entre elementos
        setMinWidth(250);
        setPrefWidth(250);
        setAlignment(Pos.TOP_CENTER); // Centrar contenido horizontalmente
        
        // Estilo de fondo (opcional, para que se vea bien)
        setStyle("-fx-background-color: #2b2b2b;"); 

        // ===== Logo =====
        try {
            String imagePath = AppConfig.getImgMain().toString(); 
            Image logoImage = new Image(imagePath, 100, 100, true, true);
            ImageView logoView = new ImageView(logoImage);
            
            // Margen superior para el logo
            VBox.setMargin(logoView, new javafx.geometry.Insets(20, 0, 10, 0));
            getChildren().add(logoView);

        } catch (Exception e) {
            e.printStackTrace();
            // Fallback si no carga la imagen
            getChildren().add(new Label("[LOGO]"));
        }

        // ===== Panel de Botones =====
        buttonsPanel.setAlignment(Pos.TOP_CENTER);
        buttonsPanel.setSpacing(5); // Espacio entre botones
        getChildren().add(buttonsPanel);

        // ===== Spacer (Glue) =====
        // En JavaFX usamos un Region con VGrow para empujar el contenido hacia abajo
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        getChildren().add(spacer);

        // ===== Copyright =====
        Label lblCopyright = new Label(" ──❰ 💀 ❱── © 2K26 PATMIC ");
        // Estilo simple para el label (blanco o gris para que se vea)
        lblCopyright.setStyle("-fx-text-fill: #888888; -fx-font-size: 10px;");
        lblCopyright.setPadding(new javafx.geometry.Insets(0, 0, 10, 0)); // Padding inferior
        getChildren().add(lblCopyright);
    }

    public void addMenuItem(MyButton button) {
        menuItems.add(button);
        
        button.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(button, new javafx.geometry.Insets(0, 10, 0, 10)); 
        
        buttonsPanel.getChildren().add(button);
    }

    public List<MyButton> getMenuItems() {
        return menuItems;
    }
}
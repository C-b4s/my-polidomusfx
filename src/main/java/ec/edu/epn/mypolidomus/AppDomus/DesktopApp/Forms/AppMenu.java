package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl.MyButton;
import ec.edu.epn.mypolidomus.Infrastructure.AppConfig;
import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class AppMenu extends VBox {
    private final List<MyButton> menuItems = new ArrayList<>();
    private final VBox buttonBox = new VBox(6); 

    public AppMenu() {
        initComponents();
    }

    private void initComponents() {

        setPrefWidth(250);
        setSpacing(10);
        setPadding(AppStyle.createPadding());
        setAlignment(Pos.TOP_CENTER);
        
        // add-logo
            ImageView logo = new ImageView(
                new Image(AppConfig.getImgMain().toExternalForm())
            );
            logo.setFitHeight(100);
            logo.setFitWidth(100);
            logo.setPreserveRatio(true);

        //Panel botones
        buttonBox.setAlignment(Pos.TOP_LEFT);

        VBox spacer = new VBox();
        VBox.setVgrow(spacer, Priority.ALWAYS);

            Label footer = new Label(" ──❰  ❱── © 2K26 MYPOLIDOMUS \"");
            footer.setFont(AppStyle.FONT_SMALL);
            footer.setTextFill(AppStyle.COLOR_FONT_LIGHT);

            getChildren().addAll(
                logo,
                buttonBox,
                spacer,
                footer
            );
        }         

    public void addMenuItem(MyButton button) {
        menuItems.add(button);
        buttonBox.getChildren().add(button);
    }

    public List<MyButton> getMenuItems() {
        return menuItems;
    }
}

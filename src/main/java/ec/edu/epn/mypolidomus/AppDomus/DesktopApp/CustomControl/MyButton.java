package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;

import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class MyButton extends Button {

    public MyButton(String text){
        customizeComponent(text,null);
    }
    public MyButton(String text, String iconPath){
        customizeComponent(text, iconPath);
    }
    public void customizeComponent(String text, String iconPath){ 
        setText(text);
        setPrefSize(90, 20);
        setFont(AppStyle.FONT);
        setCursor(Cursor.HAND);
        setAlignment(Pos.CENTER_LEFT);
        setBackground(
            AppStyle.createButtonBackground(AppStyle.COLOR_BUTTON_BG)
        );
        setTextFill(AppStyle.COLOR_BUTTON_TEXT);

       if (iconPath != null && !iconPath.isBlank()) {
            Image icon = new Image(
                getClass().getResourceAsStream(iconPath)
            );

            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(16);
            iconView.setFitHeight(16);
            iconView.setPreserveRatio(true);

            setGraphic(iconView);
        }
        setOnMouseEntered(e ->  {
            setOpacity(0.85); 
            setBackground(
                AppStyle.createButtonBackground(AppStyle.COLOR_BUTTON_HOVER)
            );
        });
            
        setOnMouseExited(e  -> {
            setOpacity(1.0);
            setBackground(
                AppStyle.createButtonBackground(AppStyle.COLOR_BUTTON_BG)
            );
        });
        
    }
    
}

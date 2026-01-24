package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;

import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MyLabel extends Label{
    public MyLabel(){
        customizeComponent(getText());
    }
    public MyLabel(String text){
        customizeComponent(text);
    }
    private void customizeComponent(String text){
        setCustomizeComponent(text, AppStyle.FONT, AppStyle.COLOR_FONT_LIGHT, AppStyle.ALIGNMENT_LEFT);

    }
    public void setCustomizeComponent(String text, Font  font, Color color, Pos alignment) {
        setText(text);
        setFont(font);
        setAlignment(alignment);
        setTextFill(color);
        setBackground(null);
    }
}
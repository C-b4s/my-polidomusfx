package ec.edu.epn.mypolidomus.App.DesktopApp.CustomControl;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Infrastructure.AppStyle;

public class MyTextBox extends JTextField {

    public MyTextBox() {
        customizeComponent();
    }

    private void customizeComponent() {
        setBorderRect();
        setBorderDownLine();
        setFont(AppStyle.FONT);  
        setForeground(AppStyle.COLOR_FONT_LIGHT);  
        setCaretColor(AppStyle.COLOR_CURSOR);   // Color del cursor
        setMargin(new Insets(4, 4, 4, 4));      // Ajusta los márgenes
        setOpaque(false);                       // Fondo transparente
        //setUI(new BasicTextFieldUI());        // Para deshabilitar el subrayado por defecto
    }

    public void setBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(AppStyle.COLOR_BORDER);
        Border emptyBorder = new EmptyBorder(4, 4, 4, 4);  // Márgenes internos
        setBorder( new CompoundBorder(lineBorder, emptyBorder));
    }

    private void setBorderDownLine(){
        int thickness = 1;
        setBorder(  BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 0, thickness, 0),
                    BorderFactory.createMatteBorder(0, 0, thickness, 0, AppStyle.COLOR_BORDER) 
        ));
    }
}

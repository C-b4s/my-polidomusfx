package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;

import javax.swing.*;

import Infrastructure.AppStyle;

import java.awt.*;

public class MyLabelText extends JPanel{
    private MyLabel    lblEtiqueta = new MyLabel();
    private MyTextBox  txtContenido= new MyTextBox();

    public MyLabelText(String etiqueta) {
        setLayout(new BorderLayout());

        lblEtiqueta.setCustomizeComponent(  etiqueta, 
                                            AppStyle.FONT_SMALL, 
                                            AppStyle.COLOR_FONT_LIGHT, 
                                            AppStyle.ALIGNMENT_LEFT); 
        add(lblEtiqueta, BorderLayout.NORTH);
        add(txtContenido, BorderLayout.CENTER);
    }
}

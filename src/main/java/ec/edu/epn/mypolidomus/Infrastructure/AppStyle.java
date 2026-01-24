//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package ec.edu.epn.mypolidomus.Infrastructure;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class AppStyle {
    private static final String FONT_FAMILY     = "Fira Code";
    

    public static final Color COLOR_FONT        = Color.BLACK;
    public static final Color COLOR_FONT_LIGHT  = Color.LIGHTCORAL;
    public static final Color COLOR_CURSOR      = Color.WHITE;
    public static final Color COLOR_BORDER      = Color.DARKBLUE;

    public static final Font  FONT              = Font.font(FONT_FAMILY, FontWeight.NORMAL,14);
    public static final Font  FONT_SMALL        = Font.font(FONT_FAMILY, FontWeight.THIN,10);
    public static final Font  FONT_BOLD         = Font.font(FONT_FAMILY, FontWeight.BOLD,14);

    public static final Pos ALIGNMENT_LEFT  = Pos.CENTER_LEFT;
    public static final Pos ALIGNMENT_RIGHT = Pos.CENTER_RIGHT;
    public static final Pos CENTER                  = Pos.CENTER;

    public static final Cursor CURSOR_HAND    = Cursor.HAND;
    public static final Cursor CURSOR_DEFAULT = Cursor.DEFAULT;

    private AppStyle() {}
    public static Border createBorderRect(){
        return new Border(
            new BorderStroke(
                Color.DARKBLUE,
                BorderStrokeStyle.SOLID,
                new CornerRadii(12),
                new BorderWidths(1)
            )
        );
    }
    public static Insets createPadding(){
        return new Insets(5);
    }
}


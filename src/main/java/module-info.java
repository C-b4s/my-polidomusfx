module ec.edu.epn.mypolidomus {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    requires com.fazecast.jSerialComm;

    
    exports ec.edu.epn.mypolidomus;
    exports ec.edu.epn.mypolidomus.Controllers;
    exports ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms;
    opens ec.edu.epn.mypolidomus to javafx.fxml;
    opens ec.edu.epn.mypolidomus.Controllers to javafx.fxml;
    opens ec.edu.epn.mypolidomus.AppDomus.DesktopApp.Forms to javafx.fxml;
    opens ec.edu.epn.mypolidomus.DataAccess.Helpers to javafx.base;
}

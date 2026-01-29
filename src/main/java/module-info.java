module ec.edu.epn.mypolidomus {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;      
    requires java.desktop;
    requires transitive javafx.graphics;  

    opens ec.edu.epn.mypolidomus to javafx.fxml;
    opens ec.edu.epn.mypolidomus.Controllers to javafx.fxml;
    opens ec.edu.epn.mypolidomus.DataAccess.Helpers to javafx.base;
    exports ec.edu.epn.mypolidomus;

}

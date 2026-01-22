module ec.edu.epn.mypolidomus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens ec.edu.epn.mypolidomus to javafx.fxml;
    exports ec.edu.epn.mypolidomus;
}

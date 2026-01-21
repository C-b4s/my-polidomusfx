module ec.edu.epn.mypolidomus {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.epn.mypolidomus to javafx.fxml;
    exports ec.edu.epn.mypolidomus;
}

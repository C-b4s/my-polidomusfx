module ec.edu.epn.mypolidomus {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;      // <-- ESTO ES OBLIGATORIO
    requires java.desktop;
    requires javafx.graphics;  // opcional, recomendado

    opens ec.edu.epn.mypolidomus
        to javafx.fxml;

    opens ec.edu.epn.mypolidomus.DataAccess.Helpers
        to javafx.base;



}

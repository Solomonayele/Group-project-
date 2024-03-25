module App {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
}
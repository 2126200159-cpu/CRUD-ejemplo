module edu.utj.evnd.poo.prueba {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires org.mariadb.jdbc;

    opens edu.utj.evnd.poo.prueba to javafx.fxml;
    opens edu.utj.evnd.poo.prueba.config to javafx.fxml;
    opens edu.utj.evnd.poo.prueba.daos to javafx.fxml;
    opens edu.utj.evnd.poo.prueba.controladores to javafx.fxml;
    opens edu.utj.evnd.poo.prueba.modelos to javafx.fxml;
    exports edu.utj.evnd.poo.prueba;
}

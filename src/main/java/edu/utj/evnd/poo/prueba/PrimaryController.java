package edu.utj.evnd.poo.prueba;

import edu.utj.evnd.poo.prueba.config.ConexionMariaDB;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.sql.SQLException;

public class PrimaryController {

//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
    
        @FXML
    void pruebaConexion(ActionEvent event) {
        try(Connection conexion =ConexionMariaDB.conectar() ) 
        {
            if(conexion != null && !conexion.isClosed()){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Conexion establecida");
                alerta.setTitle("Conexion exitosa");
                alerta.showAndWait();
            }
                
        } catch (Exception e) {
            Alert alerta=new Alert(Alert.AlertType.ERROR);
                  alerta.setHeaderText("Conexion fallida");
                  alerta.setContentText(e.getMessage());
                  alerta.showAndWait();
            
        }

    }

}

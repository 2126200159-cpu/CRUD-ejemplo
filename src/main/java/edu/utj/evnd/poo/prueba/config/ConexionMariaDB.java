/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utj.evnd.poo.prueba.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author AlumnosCCD
 */
public class ConexionMariaDB {
    private static final String URL="jdbc:mariadb://localhost:3306/poodb";
    private static final String USUARIO="root";
    private static final String CONTRASEÑA="";
    
    public static Connection conectar(){
        Connection con=null;
        try {
            con = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
           System.out.println("CONEXION ESTABLECIDA A LA BASE DE DATOS");
        } catch (SQLException e) {
            System.err.println("Error no fue posible establecer la conexion \n"+e.getMessage());
        }
        return con;
    }
}

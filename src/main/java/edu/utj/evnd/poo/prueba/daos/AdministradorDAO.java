/*
 */
package edu.utj.evnd.poo.prueba.daos;

import edu.utj.evnd.poo.prueba.modelos.Administrador;
import java.util.List;
import edu.utj.evnd.poo.prueba.config.ConexionMariaDB;
import edu.utj.evnd.poo.prueba.modelos.EEstados;
import edu.utj.evnd.poo.prueba.modelos.ERoles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author AlumnosCCD
 */
public class AdministradorDAO {
    public List<Administrador> obtenerTodos(){
        List<Administrador> lista = new ArrayList<>();
        String sql="SELECT * FROM administradores ";
        try{
            PreparedStatement ps = ConexionMariaDB.conectar().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Administrador ad = new Administrador();
                ad.setId(rs.getInt("id"));
                ad.setUsuario(rs.getString("usuario"));
                ad.setNombre(rs.getString("nombre"));
                ad.setApellido(rs.getString("apellidos"));
                ad.setCorreo(rs.getString("correo"));
                ad.setContraseña(rs.getString("contraseña"));
                ad.setRol(ERoles.valueOf(rs.getString("rol")));
                ad.setRutaImagen(rs.getString("rutaimagen"));
                ad.setEstado(EEstados.valueOf(rs.getString("estado")));
                lista.add(ad);
            }
        } catch (SQLException e) {
            System.out.println("Error"+e.getMessage());
        }
        return lista;
    }
    
    public Administrador obtenerPorId(int id){
        Administrador ad = null;
        String sql="SELECT * FROM administradores WHERE id=?";
        try{
            PreparedStatement ps = ConexionMariaDB.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                ad = new Administrador();
                ad.setId(rs.getInt("id"));
                ad.setUsuario(rs.getString("usuario"));
                ad.setNombre(rs.getString("nombre"));
                ad.setApellido(rs.getString("apellidos"));
                ad.setCorreo(rs.getString("correo"));
                ad.setContraseña(rs.getString("contraseña"));
                ad.setRol(ERoles.valueOf(rs.getString("rol")));
                ad.setRutaImagen(rs.getString("rutaimagen"));
                ad.setEstado(EEstados.valueOf(rs.getString("estado")));
            }
        } catch (SQLException e) {
            System.out.println("Error"+e.getMessage());
        }
        return ad;
    }
    
    public List<Administrador> obtenerAlgunosPorCampo(String campo){
        List<Administrador> lista = new ArrayList<>();
        String sql="SELECT * FROM administradores WHERE estado=? ";
        try{
            PreparedStatement ps = ConexionMariaDB.conectar().prepareStatement(sql);
            ps.setString(1, campo);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Administrador ad = new Administrador();
                ad.setId(rs.getInt("id"));
                ad.setUsuario(rs.getString("usuario"));
                ad.setNombre(rs.getString("nombre"));
                ad.setApellido(rs.getString("apellidos"));
                ad.setCorreo(rs.getString("correo"));
                ad.setContraseña(rs.getString("contraseña"));
                ad.setRol(ERoles.valueOf(rs.getString("rol")));
                ad.setRutaImagen(rs.getString("rutaimagen"));
                ad.setEstado(EEstados.valueOf(rs.getString("estado")));
                lista.add(ad);
            }
        } catch (SQLException e) {
            System.out.println("Error"+e.getMessage());
        }
        return lista;
    }
    
    public boolean crear(Administrador admin){
        String sql="INSERT INTO administradores "
                + " (usuario, nombre, apellidos, correo, contraseña, rol, "
                + "rutaimagen, estado) "
                + "VALUES (?,?,?,?,?,?,?,?);";
        try{
            PreparedStatement ps = ConexionMariaDB.conectar().prepareStatement(sql);
            ps.setString(1, admin.getUsuario());
            ps.setString(2, admin.getNombre());
            ps.setString(3, admin.getApellido());
            ps.setString(4, admin.getCorreo());
            ps.setString(5, admin.getContraseña());
            ps.setString(6, admin.getRol().name());
            ps.setString(7, admin.getRutaImagen());
            ps.setString(8, admin.getEstado().name());
            
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Error"+e.getMessage());
            
        }
        return false;
    }
    
    public boolean actualizar(Administrador admin){
            String sql="UPDATE administradores "
                + "SET usuario=?, nombre=?, apellidos=?, correo=?, contraseña=?, "
                + "rol=?, rutaimagen=?, estado=? "
                + "WHERE id=? ;";
        try{
            PreparedStatement ps = ConexionMariaDB.conectar().prepareStatement(sql);
            ps.setString(1, admin.getUsuario());
            ps.setString(2, admin.getNombre());
            ps.setString(3, admin.getApellido());
            ps.setString(4, admin.getCorreo());
            ps.setString(5, admin.getContraseña());
            ps.setString(6, admin.getRol().name());
            ps.setString(7, admin.getRutaImagen());
            ps.setString(8, admin.getEstado().name());
            ps.setInt(9, admin.getId());
            
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Error"+e.getMessage());
            
        }
        return false;
    }
    
    public boolean eliminar(int id){
         String sql="DELETE FROM administradores WHERE id=? ;";
        try{
            PreparedStatement ps = ConexionMariaDB.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Error"+e.getMessage());
        }
        return false;
    }
}

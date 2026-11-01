/*
 */
package edu.utj.evnd.poo.prueba.modelos;

/**
 *
 * @author AlumnosCCD
 */
public class Administrador {
    private int id;
    private String usuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private ERoles rol;
    private String rutaImagen;
    private EEstados estado;
    
    public Administrador(){
        
    }

    public Administrador(int id, String usuario, String nombre, String apellido, String correo, String contraseña, ERoles rol, String rutaImagen, EEstados estado) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
        this.rutaImagen = rutaImagen;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ERoles getRol() {
        return rol;
    }

    public void setRol(ERoles rol) {
        this.rol = rol;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public EEstados getEstado() {
        return estado;
    }

    public void setEstado(EEstados estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrador other = (Administrador) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Administrador{" + "id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + ", rol=" + rol + ", rutaImagen=" + rutaImagen + ", estado=" + estado + '}';
    }    
}

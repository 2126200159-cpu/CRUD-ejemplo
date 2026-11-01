/*
 */
package edu.utj.evnd.poo.prueba.controladores;

import edu.utj.evnd.poo.prueba.daos.AdministradorDAO;
import edu.utj.evnd.poo.prueba.modelos.Administrador;
import edu.utj.evnd.poo.prueba.modelos.EEstados;
import edu.utj.evnd.poo.prueba.modelos.ERoles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import java.io.File;

public class AdministradorController {
    
    AdministradorDAO dao;
    Administrador admin;

    @FXML private TextField txtId;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;
    @FXML private ComboBox<ERoles> cbRol;
    @FXML private ComboBox<EEstados> cbEstado;
    @FXML private TextField txtRutaImagen;
    @FXML private javafx.scene.image.ImageView imgAdministrador;
    @FXML private TextField txtBuscar;
    @FXML private Label lblMensaje;

    @FXML private TableView<Administrador> tablaAdministradores;
    @FXML private TableColumn<Administrador, Number> colId;
    @FXML private TableColumn<Administrador, String> colUsuario;
    @FXML private TableColumn<Administrador, String> colNombre;
    @FXML private TableColumn<Administrador, String> colApellidos;
    @FXML private TableColumn<Administrador, String> colCorreo;
    @FXML private TableColumn<Administrador, ERoles> colRol;
    @FXML private TableColumn<Administrador, EEstados> colEstado;

    private final ObservableList<Administrador> datos =
            FXCollections.observableArrayList();

    private File imagenSeleccionada;

    @FXML
    private void initialize() {
        
        dao = new AdministradorDAO();
        
        txtRutaImagen.setEditable(true);
        
        cbRol.setItems(
                FXCollections.observableArrayList(ERoles.values()));

        cbEstado.setItems(
                FXCollections.observableArrayList(EEstados.values()));

        // Requiere getters en la clase Administrador.
        colId.setCellValueFactory(
                c -> new javafx.beans.property.SimpleIntegerProperty(
                        c.getValue().getId()));

        colUsuario.setCellValueFactory(
                c -> new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getUsuario()));

        colNombre.setCellValueFactory(
                c -> new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getNombre()));

        colApellidos.setCellValueFactory(
                c -> new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getApellido()));

        colCorreo.setCellValueFactory(
                c -> new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getCorreo()));

        colRol.setCellValueFactory(
                c -> new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getRol()));

        colEstado.setCellValueFactory(
                c -> new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getEstado()));

        tablaAdministradores.setItems(datos);

        tablaAdministradores.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, anterior, actual) -> {
                    if (actual != null) {
                        mostrarSeleccion(actual);
                    }
                });

        cargarTabla();
    }

    @FXML
    private void cargarImagen() {
        FileChooser selector = new FileChooser();
        selector.setTitle("Seleccionar fotografía del administrador");
        selector.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes (PNG, JPG, JPEG)",
                        "*.png", "*.jpg", "*.jpeg"));

        imagenSeleccionada = selector.showOpenDialog(
                txtRutaImagen.getScene().getWindow());

        if (imagenSeleccionada != null) {
            txtRutaImagen.setText(imagenSeleccionada.getAbsolutePath());
            mostrarImagen(imagenSeleccionada.getAbsolutePath());
        }
    }

    // Muestra la imagen en el ImageView; si no existe o falla, lo deja vacío.
    private void mostrarImagen(String ruta) {
        imgAdministrador.setImage(null);
        if (ruta == null || ruta.trim().isEmpty()) {
            return;
        }
        try {
            File archivo = new File(ruta.trim());
            if (archivo.isFile()) {
                imgAdministrador.setImage(new Image(archivo.toURI().toString()));
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
    }

    @FXML
    private void crear() {
        if (!validarFormulario(false)) {
            return;
        }
        admin = new Administrador();
        if (!txtId.getText().trim().isEmpty()) {
            admin.setId(Integer.parseInt(txtId.getText().trim()));
        }
        admin.setUsuario(txtUsuario.getText());
        admin.setNombre(txtNombre.getText());
        admin.setApellido(txtApellidos.getText());
        admin.setCorreo(txtCorreo.getText());
        admin.setContraseña(txtContrasena.getText());
        admin.setRol(cbRol.getValue());
        admin.setRutaImagen(txtRutaImagen.getText());
        admin.setEstado(cbEstado.getValue());

        if(dao.crear(admin))
        {
          System.out.println("AGREGADO");
          cargarTabla();
          Alert alerta = new Alert(Alert.AlertType.INFORMATION);
          alerta.setContentText("Administrador registrado");
          alerta.showAndWait();
        }else{
          System.out.println("NO AGREGADO");
          Alert alerta = new Alert(Alert.AlertType.WARNING);
          alerta.setContentText("Administrador NO registrado");
          alerta.showAndWait();
        }
        
    }

    @FXML
    private void limpiar() {
        txtId.setText("");
        txtUsuario.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        cbRol.setValue(ERoles.MOSTRADOR);
        txtRutaImagen.setText("");
        cbEstado.setValue(EEstados.INACTIVO);
        imgAdministrador.setImage(null);
        imagenSeleccionada = null;

    }

    @FXML
    private void buscar() {
        String errorBusqueda = validarNumeroPositivo(txtBuscar.getText(), "El ID a buscar");
        if (!errorBusqueda.isEmpty()) {
            mostrarAlerta(errorBusqueda);
            return;
        }
        int id=Integer.parseInt(txtBuscar.getText().trim());
        admin=null;
        admin=dao.obtenerPorId(id);
        if(admin!=null){
            txtId.setText(String.valueOf(admin.getId()));
            txtUsuario.setText(admin.getUsuario());
            txtNombre.setText(admin.getNombre());
            txtApellidos.setText(admin.getApellido());
            txtCorreo.setText(admin.getCorreo());
            txtContrasena.setText(admin.getContraseña());
            cbRol.setValue(admin.getRol());
            txtRutaImagen.setText(admin.getRutaImagen());
            cbEstado.setValue(admin.getEstado());
            mostrarImagen(admin.getRutaImagen());

            txtUsuario.setEditable(false);
            txtNombre.setEditable(false);
            txtApellidos.setEditable(false);
            txtCorreo.setEditable(false);
            txtContrasena.setEditable(false);
            cbRol.setDisable(true);
            txtRutaImagen.setEditable(false);
            cbEstado.setDisable(true);
        }else{
            System.out.println("Administrador no encontrado ");
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Administrador no encontrado ");
            alerta.showAndWait();
        }
        

    }

    @FXML
    private void editar() {
            txtUsuario.setEditable(true);
            txtNombre.setEditable(true);
            txtApellidos.setEditable(true);
            txtCorreo.setEditable(true);
            txtContrasena.setEditable(true);
            cbRol.setDisable(false);
            txtRutaImagen.setEditable(true);
            cbEstado.setDisable(false);

    }

    @FXML
    private void actualizar() {
        try {
            System.out.println("ACTUALIZAR: botón presionado, ID=" + txtId.getText());
            actualizarAdministrador();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error inesperado al actualizar:\n" + e);
        }
    }

    private void actualizarAdministrador() {
        if (!validarFormulario(true)) {
            System.out.println("ACTUALIZAR: no pasó la validación");
            return;
        }
        admin = new Administrador();
        admin.setId(Integer.parseInt(txtId.getText()));
        admin.setUsuario(txtUsuario.getText());
        admin.setNombre(txtNombre.getText());
        admin.setApellido(txtApellidos.getText());
        admin.setCorreo(txtCorreo.getText());
        admin.setContraseña(txtContrasena.getText());
        admin.setRol(cbRol.getValue());
        admin.setRutaImagen(txtRutaImagen.getText());
        admin.setEstado(cbEstado.getValue());
        
        if(dao.actualizar(admin))
        {
          System.out.println("ACTUALIZADO");
          cargarTabla();
          Alert alerta = new Alert(Alert.AlertType.INFORMATION);
          alerta.setContentText("Administrador actualizado");
          alerta.showAndWait();
          limpiar();
        }else{
          System.out.println("NO AGREGADO");
          Alert alerta = new Alert(Alert.AlertType.WARNING);
          alerta.setContentText("Administrador NO fue posible su actualizacion");
          alerta.showAndWait();
        }
        

    }

    @FXML
    private void borrar() {
        String errorId = validarNumeroPositivo(txtId.getText(), "ID");
        if (!errorId.isEmpty()) {
            mostrarAlerta(errorId);
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        
        if(dao.eliminar(id))
        {
          System.out.println("ELIMINADO");
          cargarTabla();
          Alert alerta = new Alert(Alert.AlertType.INFORMATION);
          alerta.setContentText("Administrador eliminado");
          alerta.showAndWait();
          limpiar();
        }else{
          System.out.println("NO AGREGADO");
          Alert alerta = new Alert(Alert.AlertType.WARNING);
          alerta.setContentText("Administrador NO fue posible su eliminacion");
          alerta.showAndWait();
        }

    }

    @FXML
    private void cancelar() {
            txtUsuario.setEditable(true);
            txtNombre.setEditable(true);
            txtApellidos.setEditable(true);
            txtCorreo.setEditable(true);
            txtContrasena.setEditable(true);
            cbRol.setDisable(false);
            txtRutaImagen.setEditable(true);
            cbEstado.setDisable(false);
            limpiar();

    }

    // ------------------- VALIDACIONES -------------------

    private static final String SOLO_LETRAS =
            "[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+( [A-Za-zÁÉÍÓÚáéíóúÑñÜü]+)*";
    private static final String USUARIO = "[A-Za-z0-9_]+";
    private static final String CORREO =
            "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    private static final String SOLO_NUMEROS = "[0-9]+";
    private static final String IMAGEN = "(?i).+\\.(png|jpg|jpeg)";

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText("Datos no válidos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Valida un número entero positivo escrito en texto (ID, búsqueda).
    private String validarNumeroPositivo(String valor, String campo) {
        valor = valor == null ? "" : valor.trim();
        if (valor.isEmpty()) {
            return "- " + campo + " no puede estar vacío.\n";
        }
        if (!valor.matches(SOLO_NUMEROS)) {
            return "- " + campo + " solo acepta números positivos.\n";
        }
        if (valor.length() > 9 || Integer.parseInt(valor) <= 0) {
            return "- " + campo + " debe ser un número mayor a 0 (máx. 9 dígitos).\n";
        }
        return "";
    }

    // Valida un texto de solo letras con longitud mínima y máxima.
    private String validarLetras(String valor, String campo, int min, int max) {
        valor = valor == null ? "" : valor.trim();
        if (valor.isEmpty()) {
            return "- " + campo + " no puede estar vacío.\n";
        }
        if (!valor.matches(SOLO_LETRAS)) {
            return "- " + campo + " solo acepta letras y espacios.\n";
        }
        if (valor.length() < min || valor.length() > max) {
            return "- " + campo + " debe tener entre " + min + " y " + max
                    + " caracteres.\n";
        }
        return "";
    }

    // Valida todo el formulario. Devuelve true si no hay errores.
    private boolean validarFormulario(boolean requiereId) {
        StringBuilder errores = new StringBuilder();

        // Al crear el ID es opcional (si se deja vacío, se autogenera);
        // si se escribe, debe ser un número positivo.
        String idTexto = txtId.getText() == null ? "" : txtId.getText().trim();
        if (requiereId || !idTexto.isEmpty()) {
            errores.append(validarNumeroPositivo(idTexto, "ID"));
        }

        // Usuario: letras, números y guion bajo, 4 a 20 caracteres.
        String usuario = txtUsuario.getText() == null ? "" : txtUsuario.getText().trim();
        if (usuario.isEmpty()) {
            errores.append("- Usuario no puede estar vacío.\n");
        } else if (!usuario.matches(USUARIO)) {
            errores.append("- Usuario solo acepta letras, números y guion bajo (sin espacios).\n");
        } else if (usuario.length() < 4 || usuario.length() > 20) {
            errores.append("- Usuario debe tener entre 4 y 20 caracteres.\n");
        }

        errores.append(validarLetras(txtNombre.getText(), "Nombre", 2, 50));
        errores.append(validarLetras(txtApellidos.getText(), "Apellidos", 2, 50));

        // Correo: formato válido, máximo 100 caracteres.
        String correo = txtCorreo.getText() == null ? "" : txtCorreo.getText().trim();
        if (correo.isEmpty()) {
            errores.append("- Correo no puede estar vacío.\n");
        } else if (correo.length() > 100) {
            errores.append("- Correo no puede pasar de 100 caracteres.\n");
        } else if (!correo.matches(CORREO)) {
            errores.append("- Correo no tiene un formato válido (ejemplo: usuario@dominio.com).\n");
        }

        // Contraseña: 6 a 30 caracteres, sin espacios.
        String pass = txtContrasena.getText() == null ? "" : txtContrasena.getText();
        if (pass.isEmpty()) {
            errores.append("- Contraseña no puede estar vacía.\n");
        } else if (pass.contains(" ")) {
            errores.append("- Contraseña no puede llevar espacios.\n");
        } else if (pass.length() < 6 || pass.length() > 30) {
            errores.append("- Contraseña debe tener entre 6 y 30 caracteres.\n");
        }

        if (cbRol.getValue() == null) {
            errores.append("- Debe seleccionar un Rol.\n");
        }
        if (cbEstado.getValue() == null) {
            errores.append("- Debe seleccionar un Estado.\n");
        }

        // Imagen: obligatoria, PNG/JPG/JPEG, máximo 255 caracteres.
        String ruta = txtRutaImagen.getText() == null ? "" : txtRutaImagen.getText().trim();
        if (ruta.isEmpty()) {
            errores.append("- La imagen no puede estar vacía.\n");
        } else if (ruta.length() > 255) {
            errores.append("- La ruta de la imagen no puede pasar de 255 caracteres.\n");
        } else if (!ruta.matches(IMAGEN)) {
            errores.append("- La imagen debe ser PNG, JPG o JPEG.\n");
        }

        if (errores.length() > 0) {
            mostrarAlerta(errores.toString());
            return false;
        }
        return true;
    }

    // Vuelve a consultar la BD y llena la tabla del FXML.
    private void cargarTabla() {
        datos.setAll(dao.obtenerTodos());
    }

    // Al hacer clic en una fila, pasa sus datos al formulario.
    private void mostrarSeleccion(Administrador a) {
        txtId.setText(String.valueOf(a.getId()));
        txtUsuario.setText(a.getUsuario());
        txtNombre.setText(a.getNombre());
        txtApellidos.setText(a.getApellido());
        txtCorreo.setText(a.getCorreo());
        txtContrasena.setText(a.getContraseña());
        cbRol.setValue(a.getRol());
        txtRutaImagen.setText(a.getRutaImagen());
        cbEstado.setValue(a.getEstado());
        mostrarImagen(a.getRutaImagen());
    }
}
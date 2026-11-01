# Gestión de Administradores

CRUD de administradores hecho con **JavaFX** y **MariaDB/MySQL** (proyecto Maven).
Permite crear, buscar, editar, actualizar y borrar administradores, con
validación de todos los campos y una tabla que se actualiza sola.

## Requisitos

- JDK 11 o superior
- Maven (o NetBeans, que ya lo incluye)
- XAMPP (Apache y MySQL/MariaDB) o cualquier servidor MariaDB/MySQL

## Cómo ejecutarlo

1. Inicia **MySQL** desde el panel de XAMPP.
2. Abre phpMyAdmin (`http://localhost/phpmyadmin`) y crea una base de datos
   llamada `poodb`.
3. Selecciona `poodb`, ve a **Importar** y carga el archivo `poodb.sql`
   (en esta carpeta o en `sql/`).
4. Ejecuta el proyecto:
   - Desde NetBeans: **Clean and Build** y luego **Run**.
   - Desde la terminal: `mvn clean javafx:run`

## Configuración de la base de datos

La conexión está en `src/main/java/edu/utj/evnd/poo/prueba/config/ConexionMariaDB.java`:

| Dato | Valor |
|---|---|
| URL | `jdbc:mariadb://localhost:3306/poodb` |
| Usuario | `root` |
| Contraseña | *(vacía)* |

Si tu servidor usa otros datos, cámbialos en ese archivo.

## Funciones

- **Crear:** registra un administrador. El ID lo genera la base de datos
  (AUTO_INCREMENT), por eso el campo ID está bloqueado.
- **Buscar:** busca por ID y carga los datos en el formulario.
- **Editar / Actualizar:** habilita los campos y guarda los cambios.
- **Borrar:** elimina el administrador seleccionado.
- **Limpiar / Cancelar:** vacía el formulario.
- **Tabla:** se llena al abrir y después de crear, actualizar o borrar.
  Al hacer clic en una fila, sus datos pasan al formulario.
- **Cargar imagen:** permite elegir una foto (PNG, JPG o JPEG) y muestra una
  vista previa.

## Validaciones

| Campo | Regla |
|---|---|
| ID | Solo números, mayor a 0 |
| Usuario | Letras, números y `_`, 4 a 20 caracteres, sin espacios |
| Nombre y Apellidos | Solo letras y espacios, 2 a 50 caracteres |
| Correo | Formato válido (`usuario@dominio.com`), máximo 100 caracteres |
| Contraseña | 6 a 30 caracteres, sin espacios |
| Rol y Estado | Obligatorios |
| Imagen | Obligatoria, PNG/JPG/JPEG, máximo 255 caracteres |

Ningún campo puede quedar vacío.

## Estructura del proyecto

```
src/main/java/edu/utj/evnd/poo/prueba/
├── App.java                 Arranque de la aplicación
├── config/                  Conexión a la base de datos
├── controladores/           Lógica de la pantalla (AdministradorController)
├── daos/                    Acceso a datos (AdministradorDAO)
└── modelos/                 Clases del modelo (Administrador, ERoles, EEstados)
src/main/resources/.../Administrador.fxml   Diseño de la pantalla
```

## Tecnologías

JavaFX 13 · MariaDB JDBC 3.5.10 · Maven · Java 11

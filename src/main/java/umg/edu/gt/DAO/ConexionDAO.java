/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author c4180
 */
public class ConexionDAO {
   
    private String url = "jdbc:mysql://localhost:3306/ventas"; // URL de la base de datos
    private String usuario = "root";
    private String contraseña = "";
    
    /**
     * Establece la conexión con la base de datos MySQL.
     *
     * @return La conexión establecida.
     * @throws Exception Si ocurre algún error durante la conexión.
     */
    public Connection conexionMysql() throws Exception {
        // Cargamos el controlador JDBC de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establecemos la conexión
        Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
        
        System.out.println("La conexión es exitosa: " + conexion);
        
        return conexion;
    }

    // Getters y setters para las propiedades

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
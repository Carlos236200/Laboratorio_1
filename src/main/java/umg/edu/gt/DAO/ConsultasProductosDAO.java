/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import umg.edu.gt.DTO.ProductosDTO;
/**
 *
 * @author c4180
 */
public class ConsultasProductosDAO {
    
        ConexionDAO con = new ConexionDAO();
    
    
        public List<ProductosDTO> findAllProductos() throws Exception{
        List<ProductosDTO> ListProductos = new ArrayList<ProductosDTO>();
       
        try{
            String query="SELECT id, nombre, descripcion, precio, cantidad FROM productos";
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                ProductosDTO producto = new ProductosDTO();
                producto.setId(r.getLong("id"));
                producto.setNombre(r.getString("nombre"));
                producto.setDescripcion(r.getString("descripcion"));
                producto.setPrecio(r.getString("precio"));
                producto.setCantidad(r.getString("cantidad"));
                ListProductos.add(producto);
            }          
        } catch(Exception e){
            System.out.println("Error al realizar la consulta");
        }
        System.out.println("El tamaño de la lista" + ListProductos.size());
        return ListProductos;
    }
    
        
         // Inserta datos de un cliente en la base de datos.
    public void insertarDatosProductos(Connection conexion, ProductosDTO datos) {
        if (datos.getNombre()!= null  || datos.getDescripcion()!= null ||
                              datos.getPrecio()!= null && !datos.getPrecio().isEmpty() ||
                              datos.getCantidad()!= null && !datos.getCantidad().isEmpty()) {
            String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, datos.getNombre());
                pstmt.setString(2, datos.getDescripcion());
                pstmt.setString(3, datos.getPrecio());
                pstmt.setString(4, datos.getCantidad());
                
                pstmt.executeUpdate();
                
                System.out.println("Datos insertados correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al insertar datos: " + e.getMessage());
            }
        } else {
            System.out.println("No se insertaron datos porque no se proporcionaron valores válidos.");
        }
    }

    // Actualiza los datos de un cliente en la base de datos.
    public void actualizarDatosProductos(Connection conexion, Long idproductos, ProductosDTO datosActualizarProductos) {
        String sql = "UPDATE productos SET ";
        
        if (datosActualizarProductos.getNombre()!= null && !datosActualizarProductos.getNombre().isEmpty()) {
            sql += "nombre = ?, ";
        }
        if (datosActualizarProductos.getDescripcion()!= null && !datosActualizarProductos.getDescripcion().isEmpty()) {
            sql += "descripcion = ?, ";
        }
        
        if (datosActualizarProductos.getPrecio()!= null && !datosActualizarProductos.getPrecio().isEmpty()) {
            sql += "precio = ?, ";
        }
        if (datosActualizarProductos.getCantidad()!= null && !datosActualizarProductos.getCantidad().isEmpty()) {
            sql += "cantidad = ?, ";
        }
            
        sql = sql.substring(0, sql.length() - 2);
    
        sql += " WHERE id = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            int parameterIndex = 1;
            
            if (datosActualizarProductos.getNombre()!= null && !datosActualizarProductos.getNombre().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizarProductos.getNombre());
            }
            if (datosActualizarProductos.getDescripcion()!= null && !datosActualizarProductos.getDescripcion().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizarProductos.getDescripcion());
            }
            if (datosActualizarProductos.getPrecio()!= null && !datosActualizarProductos.getPrecio().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizarProductos.getPrecio());
            }
            if (datosActualizarProductos.getCantidad()!= null && !datosActualizarProductos.getCantidad().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizarProductos.getCantidad());
            }
            pstmt.setLong(parameterIndex, idproductos);
    
            pstmt.executeUpdate();
    
            System.out.println("Datos actualizados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }
        
        
}
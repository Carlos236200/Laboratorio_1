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
import umg.edu.gt.DTO.Detalles_OrdenesDTO;
/**
 *
 * @author c4180
 */
public class Consultas_detalle_ordenesDAO {
    
        ConexionDAO con = new ConexionDAO();
    
    
        public List<Detalles_OrdenesDTO> findAllDetallesOrdenes() throws Exception{
        List<Detalles_OrdenesDTO> ListDetalles = new ArrayList<Detalles_OrdenesDTO>();
       
        try{
            String query="SELECT id, orden_id, producto_id, cantidad, precio FROM detalles_ordenes";
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                Detalles_OrdenesDTO detalle = new Detalles_OrdenesDTO();
                detalle.setId(r.getLong("id"));
                detalle.setOrden_id(r.getLong("orden_id"));
                detalle.setProducto_id(r.getLong("producto_id"));
                detalle.setCantidad(r.getString("cantidad"));
                detalle.setPrecio(r.getString("precio"));
                ListDetalles.add(detalle);
            }          
        } catch(Exception e){
            System.out.println("Error al realizar la consulta");
        }
        System.out.println("El tamaño de la lista" + ListDetalles.size());
        return ListDetalles;
    }
    
        
         // Inserta datos de un cliente en la base de datos.
    public void insertarDatosOrdenes(Connection conexion, Detalles_OrdenesDTO datos) {
        if (datos.getOrden_id()!= null  || datos.getProducto_id()!= null ||
                              datos.getCantidad()!= null && !datos.getCantidad().isEmpty() ||
                              datos.getPrecio()!= null && !datos.getPrecio().isEmpty()) {
            String sql = "INSERT INTO detalles_ordenes (orden_id, producto_id, cantidad, precio) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setLong(1, datos.getOrden_id());
                pstmt.setLong(2, datos.getProducto_id());
                pstmt.setString(3, datos.getCantidad());
                pstmt.setString(4, datos.getPrecio());
                
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
    public void actualizarDatosOrdenes(Connection conexion, Long idOrdenes, Detalles_OrdenesDTO datosActualizarOrdenes) {
        String sql = "UPDATE detalles_ordenes SET ";
        
        if (datosActualizarOrdenes.getOrden_id()!= null) {
            sql += "orden_id = ?, ";
        }
        if (datosActualizarOrdenes.getProducto_id()!= null) {
            sql += "producto_id = ?, ";
        }
        
        if (datosActualizarOrdenes.getCantidad()!= null && !datosActualizarOrdenes.getCantidad().isEmpty()) {
            sql += "cantidad = ?, ";
        }
        if (datosActualizarOrdenes.getPrecio()!= null && !datosActualizarOrdenes.getPrecio().isEmpty()) {
            sql += "precio = ?, ";
        }
            
        sql = sql.substring(0, sql.length() - 2);
    
        sql += " WHERE id = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            int parameterIndex = 1;
            
            if (datosActualizarOrdenes.getOrden_id()!= null) {
                pstmt.setLong(parameterIndex++, datosActualizarOrdenes.getOrden_id());
            }
            if (datosActualizarOrdenes.getProducto_id()!= null) {
                pstmt.setLong(parameterIndex++, datosActualizarOrdenes.getProducto_id());
            }
            if (datosActualizarOrdenes.getCantidad() != null && !datosActualizarOrdenes.getCantidad().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizarOrdenes.getCantidad());
            }
            if (datosActualizarOrdenes.getPrecio() != null && !datosActualizarOrdenes.getPrecio().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizarOrdenes.getPrecio());
            }
            pstmt.setLong(parameterIndex, idOrdenes);
    
            pstmt.executeUpdate();
    
            System.out.println("Datos actualizados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }
        
        
}

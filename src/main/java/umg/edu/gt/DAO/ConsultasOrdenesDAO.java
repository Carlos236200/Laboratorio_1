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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import umg.edu.gt.DTO.OrdenesDTO;

/**
 *
 * @author c4180
 */
public class ConsultasOrdenesDAO {
    ConexionDAO con = new ConexionDAO();
    
    
        public List<OrdenesDTO> findAllOrdenes() throws Exception{
        List<OrdenesDTO> ListOrdenes = new ArrayList<OrdenesDTO>();
       
        try{
            String query="SELECT id, cliente_id, fecha, total FROM ordenes";
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                OrdenesDTO ordenes = new OrdenesDTO();
                ordenes.setId(r.getLong("id"));
                ordenes.setCliente_id(r.getLong("cliente_id"));
                ordenes.setFecha(r.getTimestamp("fecha").toLocalDateTime());
                ordenes.setTotal(r.getString("total"));;
                ListOrdenes.add(ordenes);
            }          
        } catch(Exception e){
            System.out.println("Error al realizar la consulta");
        }
        System.out.println("El tamaño de la lista es: " + ListOrdenes.size());
        return ListOrdenes;
    }
    
        
         // Inserta datos de un cliente en la base de datos.
    public void insertarDatosTablaOrdenes(Connection conexion, OrdenesDTO datos) {
        if (datos.getCliente_id()!= null  || datos.getFecha()!= null ||
                              datos.getTotal()!= null && !datos.getTotal().isEmpty() ) {
            String sql = "INSERT INTO ordenes (cliente_id, fecha, total) VALUES (?, ?, ?)";
            
            LocalDateTime localDateTime = datos.getFecha();  
            java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(localDateTime);
            
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setLong(1, datos.getCliente_id());
                pstmt.setTimestamp(2, sqlTimestamp);
                pstmt.setString(3, datos.getTotal());
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
    public void actualizarDatosTablaOrdenes(Connection conexion, Long idTablaOrdenes, OrdenesDTO datosActualizarTablaOrdenes) {
        String sql = "UPDATE ordenes SET ";
        
        if (datosActualizarTablaOrdenes.getCliente_id()!= null) {
            sql += "cliente_id = ?, ";
        }
        if (datosActualizarTablaOrdenes.getFecha()!= null) {
            sql += "fecha = ?, ";
        }
        
        if (datosActualizarTablaOrdenes.getTotal()!= null && !datosActualizarTablaOrdenes.getTotal().isEmpty()) {
            sql += "total = ?, ";
        }
            
        sql = sql.substring(0, sql.length() - 2);
    
        sql += " WHERE id = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            int parameterIndex = 1;
            
            if (datosActualizarTablaOrdenes.getCliente_id()!= null) {
                pstmt.setLong(parameterIndex++, datosActualizarTablaOrdenes.getCliente_id());
            }
            if (datosActualizarTablaOrdenes.getFecha()!= null) {
                LocalDateTime localDateTime = datosActualizarTablaOrdenes.getFecha();
                java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(localDateTime);
                pstmt.setTimestamp(parameterIndex++, sqlTimestamp);
            }
            if (datosActualizarTablaOrdenes.getTotal()!= null && !datosActualizarTablaOrdenes.getTotal().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizarTablaOrdenes.getTotal());
            }

            pstmt.setLong(parameterIndex,  idTablaOrdenes);
    
            pstmt.executeUpdate();
    
            System.out.println("Datos actualizados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }
}

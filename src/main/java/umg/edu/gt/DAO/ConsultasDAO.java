package umg.edu.gt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import umg.edu.gt.DTO.DatosDTO;

public class ConsultasDAO {
    
    ConexionDAO con = new ConexionDAO();
    
    // Recupera una lista de todos los clientes almacenados en la base de datos.
    public List<DatosDTO> findAllCliente() {
        List<DatosDTO> lista = new ArrayList<DatosDTO>();
        
        try {
            String query = "SELECT id, nombre, correo, direccion, telefono FROM clientes";
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            
            while (r.next()) {
                DatosDTO dato = new DatosDTO();
                dato.setId(r.getLong("id"));
                dato.setNombre(r.getString("nombre"));
                dato.setDireccion(r.getString("direccion"));
                dato.setCorreo(r.getString("correo"));
                dato.setTelefono(r.getString("telefono"));
                lista.add(dato);
            }
        } catch (Exception ex) {
            System.out.println("Error al realizar la consulta...");
        }
        
        System.out.println("el tamaño de la lista es: " + lista.size());
        return lista;
    }
    
    // Inserta datos de un cliente en la base de datos.
    public void insertarDatos(Connection conexion, DatosDTO datos) {
        if (datos != null && (datos.getNombre() != null && !datos.getNombre().isEmpty() ||
                              datos.getCorreo() != null && !datos.getCorreo().isEmpty() ||
                              datos.getDireccion() != null && !datos.getDireccion().isEmpty() ||
                              datos.getTelefono() != null && !datos.getTelefono().isEmpty())) {
            String sql = "INSERT INTO clientes (nombre, correo, direccion, telefono) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, datos.getNombre());
                pstmt.setString(2, datos.getCorreo());
                pstmt.setString(3, datos.getDireccion());
                pstmt.setString(4, datos.getTelefono());
                
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
    public void actualizarDatosCliente(Connection conexion, Long idCliente, DatosDTO datosActualizar) {
        String sql = "UPDATE clientes SET ";
        
        if (datosActualizar.getNombre() != null && !datosActualizar.getNombre().isEmpty()) {
            sql += "nombre = ?, ";
        }
        if (datosActualizar.getCorreo() != null && !datosActualizar.getCorreo().isEmpty()) {
            sql += "correo = ?, ";
        }
        if (datosActualizar.getDireccion() != null && !datosActualizar.getDireccion().isEmpty()) {
            sql += "direccion = ?, ";
        }
        if (datosActualizar.getTelefono() != null && !datosActualizar.getTelefono().isEmpty()) {
            sql += "telefono = ?, ";
        }
    
        
        sql = sql.substring(0, sql.length() - 2);
    
        sql += " WHERE id = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            int parameterIndex = 1;
            if (datosActualizar.getNombre() != null && !datosActualizar.getNombre().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizar.getNombre());
            }
            if (datosActualizar.getCorreo() != null && !datosActualizar.getCorreo().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizar.getCorreo());
            }
            if (datosActualizar.getDireccion() != null && !datosActualizar.getDireccion().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizar.getDireccion());
            }
            if (datosActualizar.getTelefono() != null && !datosActualizar.getTelefono().isEmpty()) {
                pstmt.setString(parameterIndex++, datosActualizar.getTelefono());
            }
            pstmt.setLong(parameterIndex, idCliente);
    
            pstmt.executeUpdate();
    
            System.out.println("Datos actualizados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }

}
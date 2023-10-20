/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.labo1;

/**
 *
 * @author c4180
 */
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import umg.edu.gt.DAO.ConexionDAO;
import umg.edu.gt.DAO.ConsultasProductosDAO;
import umg.edu.gt.DTO.ProductosDTO;

@ManagedBean(name = "Inicio_ProductosUI")
public class ProductosUI implements Serializable {

    private List<ProductosDTO> lista = new ArrayList<ProductosDTO>();
    private ProductosDTO datosAInsertarProductos;
    private ProductosDTO datosAActualizarProductos;
    private Long idRegistroActualizarProductos;

    public List<ProductosDTO> getLista() {
        return lista;
    }

    public void setLista(List<ProductosDTO> lista) {
        this.lista = lista;
    }

    public ProductosDTO getDatosAInsertarProductos() {
        return datosAInsertarProductos;
    }

    public void setDatosAInsertarProductos(ProductosDTO datosAInsertarProductos) {
        this.datosAInsertarProductos = datosAInsertarProductos;
    }

    public ProductosDTO getDatosAActualizarProductos() {
        return datosAActualizarProductos;
    }

    public void setDatosAActualizarProductos(ProductosDTO datosAActualizarProductos) {
        this.datosAActualizarProductos = datosAActualizarProductos;
    }

    public Long getIdRegistroActualizarProductos() {
        return idRegistroActualizarProductos;
    }

    public void setIdRegistroActualizarProductos(Long idRegistroActualizarProductos) {
        this.idRegistroActualizarProductos = idRegistroActualizarProductos;
    }

   

    @PostConstruct
    public void init() {
    ConexionDAO con = new ConexionDAO();
    ConsultasProductosDAO consulta = new ConsultasProductosDAO();

    try {
        setLista(consulta.findAllProductos()); 
        System.out.println("La conexión es: " + con.conexionMysql());
        System.out.println("La lista de detalles de órdenes tiene " + getLista().size() + " registros.");

        datosAInsertarProductos = new ProductosDTO();
        datosAActualizarProductos = new ProductosDTO(); // Inicializa datosAActualizarOrdenes

    } catch (Exception ex) {
        System.out.println("Error al realizar la consulta de detalles de órdenes... " + ex);
    }
}

    // Método para insertar datos en la base de datos
    public void insertarDatosProductos() {
        if (datosAInsertarProductos != null && (datosAInsertarProductos.getNombre()!=null || datosAInsertarProductos.getDescripcion()!=null ||
                datosAInsertarProductos.getPrecio()!= null || datosAInsertarProductos.getCantidad()!= null)) {
            
            ConexionDAO con = new ConexionDAO();
            try (Connection conexion = con.conexionMysql()) {
                ConsultasProductosDAO consulta = new ConsultasProductosDAO();
                consulta.insertarDatosProductos(conexion, datosAInsertarProductos);
            } catch (Exception ex) {
                System.out.println("Error al establecer la conexión o al insertar datos: " + ex);
            }
        } else {
            System.out.println("No se insertaron datos porque no se proporcionaron valores válidos.");
        }
    }

    // Método para actualizar datos en la base de datos
    public void actualizarDatosProductos() {
        ConexionDAO con = new ConexionDAO();
        try (Connection conexion = con.conexionMysql()) {
            ConsultasProductosDAO consulta = new ConsultasProductosDAO();
            consulta.actualizarDatosProductos(conexion, idRegistroActualizarProductos, datosAActualizarProductos);
        } catch (Exception ex) {
            System.out.println("Error al establecer la conexión o al actualizar datos: " + ex);
        }
    }
}
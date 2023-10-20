import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import umg.edu.gt.DAO.ConexionDAO;
import umg.edu.gt.DAO.Consultas_detalle_ordenesDAO;
import umg.edu.gt.DTO.Detalles_OrdenesDTO;

@ManagedBean(name = "Inicio_OrdenesUI")
public class DetallesOrdenesUI implements Serializable {

    private List<Detalles_OrdenesDTO> lista = new ArrayList<Detalles_OrdenesDTO>();
    private Detalles_OrdenesDTO datosAInsertarOrdenes;
    private Detalles_OrdenesDTO datosAActualizarOrdenes;
    private Long idRegistroActualizarOrdenes;

    public Detalles_OrdenesDTO getDatosAInsertarOrdenes() {
        return datosAInsertarOrdenes;
    }

    public void setDatosAInsertarOrdenes(Detalles_OrdenesDTO datosAInsertarOrdenes) {
        this.datosAInsertarOrdenes = datosAInsertarOrdenes;
    }

    public Detalles_OrdenesDTO getDatosAActualizarOrdenes() {
        return datosAActualizarOrdenes;
    }

    public void setDatosAActualizarOrdenes(Detalles_OrdenesDTO datosAActualizarOrdenes) {
        this.datosAActualizarOrdenes = datosAActualizarOrdenes;
    }

    public Long getIdRegistroActualizarOrdenes() {
        return idRegistroActualizarOrdenes;
    }

    public void setIdRegistroActualizarOrdenes(Long idRegistroActualizarOrdenes) {
        this.idRegistroActualizarOrdenes = idRegistroActualizarOrdenes;
    }

    public List<Detalles_OrdenesDTO> getLista() {
        return lista;
    }

    public void setLista(List<Detalles_OrdenesDTO> lista) {
        this.lista = lista;
    }

    @PostConstruct
    public void init() {
    ConexionDAO con = new ConexionDAO();
    Consultas_detalle_ordenesDAO consulta = new Consultas_detalle_ordenesDAO();

    try {
        setLista(consulta.findAllDetallesOrdenes());
        System.out.println("La conexión es: " + con.conexionMysql());
        System.out.println("La lista de detalles de órdenes tiene " + getLista().size() + " registros.");

        datosAInsertarOrdenes = new Detalles_OrdenesDTO();
        datosAActualizarOrdenes = new Detalles_OrdenesDTO(); // Inicializa datosAActualizarOrdenes

    } catch (Exception ex) {
        System.out.println("Error al realizar la consulta de detalles de órdenes... " + ex);
    }
}

    // Método para insertar datos en la base de datos
    public void insertarDatosOrdenes() {
        if (datosAInsertarOrdenes != null && (datosAInsertarOrdenes.getOrden_id()!=null || datosAInsertarOrdenes.getProducto_id()!=null ||
                datosAInsertarOrdenes.getCantidad() != null || datosAInsertarOrdenes.getPrecio() != null)) {
            
            ConexionDAO con = new ConexionDAO();
            try (Connection conexion = con.conexionMysql()) {
                Consultas_detalle_ordenesDAO consulta = new Consultas_detalle_ordenesDAO();
                consulta.insertarDatosOrdenes(conexion, datosAInsertarOrdenes);
            } catch (Exception ex) {
                System.out.println("Error al establecer la conexión o al insertar datos: " + ex);
            }
        } else {
            System.out.println("No se insertaron datos porque no se proporcionaron valores válidos.");
        }
    }

    // Método para actualizar datos en la base de datos
    public void actualizarDatosOrdenes() {
        ConexionDAO con = new ConexionDAO();
        try (Connection conexion = con.conexionMysql()) {
            Consultas_detalle_ordenesDAO consulta = new Consultas_detalle_ordenesDAO();
            consulta.actualizarDatosOrdenes(conexion, idRegistroActualizarOrdenes, datosAActualizarOrdenes);
        } catch (Exception ex) {
            System.out.println("Error al establecer la conexión o al actualizar datos: " + ex);
        }
    }
}

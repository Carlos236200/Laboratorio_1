package umg.edu.gt.labo1;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import umg.edu.gt.DAO.ConexionDAO;
import umg.edu.gt.DAO.ConsultasOrdenesDAO;
import umg.edu.gt.DTO.OrdenesDTO;

@ManagedBean(name = "Inicio_TablaOrdenesUI")
public class OrdenesUI implements Serializable {

    private List<OrdenesDTO> lista = new ArrayList<OrdenesDTO>();
    private OrdenesDTO datosAInsertarTablaOrdenes;
    private OrdenesDTO datosAActualizarTablaOrdenes;
    private Long idRegistroActualizarTablaOrdenes;

    public List<OrdenesDTO> getLista() {
        return lista;
    }

    public void setLista(List<OrdenesDTO> lista) {
        this.lista = lista;
    }

    public OrdenesDTO getDatosAInsertarTablaOrdenes() {
        return datosAInsertarTablaOrdenes;
    }

    public void setDatosAInsertarTablaOrdenes(OrdenesDTO datosAInsertarTablaOrdenes) {
        this.datosAInsertarTablaOrdenes = datosAInsertarTablaOrdenes;
    }

    public OrdenesDTO getDatosAActualizarTablaOrdenes() {
        return datosAActualizarTablaOrdenes;
    }

    public void setDatosAActualizarTablaOrdenes(OrdenesDTO datosAActualizarTablaOrdenes) {
        this.datosAActualizarTablaOrdenes = datosAActualizarTablaOrdenes;
    }

    public Long getIdRegistroActualizarTablaOrdenes() {
        return idRegistroActualizarTablaOrdenes;
    }

    public void setIdRegistroActualizarTablaOrdenes(Long idRegistroActualizarTablaOrdenes) {
        this.idRegistroActualizarTablaOrdenes = idRegistroActualizarTablaOrdenes;
    }


    

    @PostConstruct
    public void init() {
    ConexionDAO con = new ConexionDAO(); 
    ConsultasOrdenesDAO consulta = new ConsultasOrdenesDAO();

    try {
        setLista(consulta.findAllOrdenes());
        System.out.println("La conexión es: " + con.conexionMysql());
        System.out.println("La lista de detalles de órdenes tiene " + getLista().size() + " registros.");

        datosAInsertarTablaOrdenes = new OrdenesDTO();
        datosAActualizarTablaOrdenes = new OrdenesDTO(); // Inicializa datosAActualizarOrdenes

    } catch (Exception ex) {
        System.out.println("Error al realizar la consulta de detalles de órdenes... " + ex);
    }
}

    // Método para insertar datos en la base de datos
    public void insertarDatosTablaOrdenes() {
        if (datosAInsertarTablaOrdenes != null && (datosAInsertarTablaOrdenes.getCliente_id()!=null || datosAInsertarTablaOrdenes.getCliente_id()!=null ||
                datosAInsertarTablaOrdenes.getFecha()!= null || datosAInsertarTablaOrdenes.getTotal()!= null)) {
            ConexionDAO con = new ConexionDAO();
            try (Connection conexion = con.conexionMysql()) {
                ConsultasOrdenesDAO consulta = new ConsultasOrdenesDAO();
                consulta.insertarDatosTablaOrdenes(conexion, datosAInsertarTablaOrdenes);
            } catch (Exception ex) {
                System.out.println("Error al establecer la conexión o al insertar datos: " + ex);
            }
        } else {
            System.out.println("No se insertaron datos porque no se proporcionaron valores válidos.");
        }
    }

    // Método para actualizar datos en la base de datos
    public void actualizarDatosTablaOrdenes() {
        ConexionDAO con = new ConexionDAO();
        try (Connection conexion = con.conexionMysql()) {
            ConsultasOrdenesDAO consulta = new ConsultasOrdenesDAO();
            consulta.actualizarDatosTablaOrdenes(conexion, idRegistroActualizarTablaOrdenes, datosAActualizarTablaOrdenes);
        } catch (Exception ex) {
            System.out.println("Error al establecer la conexión o al actualizar datos: " + ex);
        }
    }
}
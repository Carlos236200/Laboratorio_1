package umg.edu.gt.labo1;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import umg.edu.gt.DAO.ConexionDAO;
import umg.edu.gt.DAO.ConsultasDAO;
import umg.edu.gt.DTO.DatosDTO;

@ManagedBean(name = "bkn_Inicio")
public class InicioUI implements Serializable {

    private String mensaje;
    private String dato1;
    private String dato2;
    private String dato3;
    private boolean band;
    private List<DatosDTO> lista = new ArrayList<DatosDTO>();
    private DatosDTO datosAInsertar;
    private DatosDTO datosAActualizar;
    private Long idRegistroActualizar;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    public String getDato3() {
        return dato3;
    }

    public void setDato3(String dato3) {
        this.dato3 = dato3;
    }

    public boolean isBand() {
        return band;
    }

    public void setBand(boolean band) {
        this.band = band;
    }

    public List<DatosDTO> getLista() {
        return lista;
    }

    public void setLista(List<DatosDTO> lista) {
        this.lista = lista;
    }

    public DatosDTO getDatosAInsertar() {
        return datosAInsertar;
    }

    public void setDatosAInsertar(DatosDTO datosAInsertar) {
        this.datosAInsertar = datosAInsertar;
    }

    public DatosDTO getDatosAActualizar() {
        return datosAActualizar;
    }

    public void setDatosAActualizar(DatosDTO datosAActualizar) {
        this.datosAActualizar = datosAActualizar;
    }

    public Long getIdRegistroActualizar() {
        return idRegistroActualizar;
    }

    public void setIdRegistroActualizar(Long idRegistroActualizar) {
        this.idRegistroActualizar = idRegistroActualizar;
    }

    public InicioUI() {
    }

    @PostConstruct
    public void init() {
        ConexionDAO con = new ConexionDAO();
        ConsultasDAO consulta = new ConsultasDAO();
        datosAInsertar = new DatosDTO();
        datosAActualizar = new DatosDTO(); // Inicializa datosAActualizar

        try {
            setLista(consulta.findAllCliente());
            System.out.println("La conexion es: " + con.conexionMysql());
            System.out.println(" La lista es: " + getLista().size());
            System.out.println("el nombre es: " + lista.get(0).getNombre());
        } catch (Exception ex) {
            System.out.println("error al realizar la consulta... " + ex);
        }

        this.mensaje1();
    }

    // Método para establecer un mensaje
    public void mensaje1() {
        mensaje = "LABORATORIO DESAROLLO WEB, CARLOS RODRIGUEZ";
    }

    // Método para mostrar datos en la consola
    public void mostrarDatos() {
        System.out.println("Método mostrarDatos se ha ejecutado.");
        System.out.println("Dato1: " + getDato1());
        System.out.println("Dato2: " + getDato2());
        System.out.println("Nombre: " + dato1 + "Apellido: " + dato2);
        dato3 = "Nombre: " + dato1 + ",  Apellido: " + dato2;
        band = true;
    }

    // Método para insertar datos en la base de datos
    public void insertarDatos() {
        if (datosAInsertar != null && (datosAInsertar.getNombre() != null || datosAInsertar.getCorreo() != null || datosAInsertar.getDireccion() != null || datosAInsertar.getTelefono() != null)) {
            ConexionDAO con = new ConexionDAO();
            try (Connection conexion = con.conexionMysql()) {
                ConsultasDAO consulta = new ConsultasDAO();
                consulta.insertarDatos(conexion, datosAInsertar);
            } catch (Exception ex) {
                System.out.println("Error al establecer la conexión o al insertar datos: " + ex);
            }
        } else {
            System.out.println("No se insertaron datos porque no se proporcionaron valores válidos.");
        }
    }

    // Método para actualizar datos en la base de datos
    public void actualizarDatos() {
        ConexionDAO con = new ConexionDAO();
        try (Connection conexion = con.conexionMysql()) {
            ConsultasDAO consulta = new ConsultasDAO();
            consulta.actualizarDatosCliente(conexion, idRegistroActualizar, datosAActualizar);
        } catch (Exception ex) {
            System.out.println("Error al establecer la conexión o al actualizar datos: " + ex);
        }
    }
}

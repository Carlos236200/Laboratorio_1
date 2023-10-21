/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicioHibernate;

/**
 *
 * @author c4180
 */
import controladorHibernate.ControladorOrdenes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import ordenHibernate.OrdenesHibernate;


@ManagedBean(name="bkn_Ordenes")
public class OrdenesHibernateUI implements Serializable{
    
      private List<OrdenesHibernate> lista = new ArrayList<>();
      private OrdenesHibernate datosAInsertar = new OrdenesHibernate();

    public OrdenesHibernate getDatosActualizar() {
        return datosActualizar;
    }

    public void setDatosActualizar(OrdenesHibernate datosActualizar) {
        this.datosActualizar = datosActualizar;
    }
      private OrdenesHibernate datosActualizar = new OrdenesHibernate();

    public OrdenesHibernate getDatosAInsertar() {
        return datosAInsertar;
    }

    public void setDatosAInsertar(OrdenesHibernate datosAInsertar) {
        this.datosAInsertar = datosAInsertar;
    }   

    public List<OrdenesHibernate> getLista() {
        return lista;
    }

    public void setLista(List<OrdenesHibernate> lista) {
        this.lista = lista;
    }
    
    
     @PostConstruct
    public void init(){
        
        ControladorOrdenes usuarioControlers = new ControladorOrdenes();
        List<OrdenesHibernate> ordenes = usuarioControlers.getOrdenes();
        
         for(OrdenesHibernate orden : ordenes){
            lista.add(orden);
        }               
    }
    
    
    //metodo para insertar cliente
    public void insertarOrden(){
    
   Long cliente_id = datosAInsertar.getCliente_id();
   String fecha = datosAInsertar.getFecha();
   String total = datosAInsertar.getTotal();
        
    String orden = new ControladorOrdenes().crearOrdenes(cliente_id, fecha, total);
    
    }
    
    //metodo para actualizar cliente
    
    public void actualizarOrden(){
        
   Long id = datosActualizar.getId();
   Long cliente_id = datosActualizar.getCliente_id();
   String fecha = datosActualizar.getFecha();
   String total = datosActualizar.getTotal();
        
      String orden = new ControladorOrdenes().ActualizarOrden(id, cliente_id, fecha, total);     
    }
   
    public void eliminarOrden(){
        
    Long id = datosAInsertar.getId();
    
    String orden = new ControladorOrdenes().EliminarOrden(id);
    
    }
    
   
}

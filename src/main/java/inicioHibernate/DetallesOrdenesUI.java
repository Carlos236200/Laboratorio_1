/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicioHibernate;

/**
 *
 * @author c4180
 */
import controladorHibernate.ControladorDetallesOrdenesHibernate;
import detallesOrdeneshibernate.DetallesOrdenesHibernate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


@ManagedBean(name="bkn_detallesOrdenes")
public class DetallesOrdenesUI implements Serializable{
    
      private List<DetallesOrdenesHibernate> lista = new ArrayList<>();
      private DetallesOrdenesHibernate datosAInsertar = new DetallesOrdenesHibernate();
      

    public DetallesOrdenesHibernate getDatosActualizar() {
        return datosActualizar;
    }

    public void setDatosActualizar(DetallesOrdenesHibernate datosActualizar) {
        this.datosActualizar = datosActualizar;
    }
      private DetallesOrdenesHibernate datosActualizar = new DetallesOrdenesHibernate();

    public DetallesOrdenesHibernate getDatosAInsertar() {
        return datosAInsertar;
    }

    public void setDatosInsert(DetallesOrdenesHibernate datosAInsertar) {
        this.datosAInsertar = datosAInsertar;
    }   

    public List<DetallesOrdenesHibernate> getLista() {
        return lista;
    }

    public void setLista(List<DetallesOrdenesHibernate> lista) {
        this.lista = lista;
    }
    
    
     @PostConstruct
    public void init(){
        
        ControladorDetallesOrdenesHibernate usuarioControlers = new  ControladorDetallesOrdenesHibernate();
        List< DetallesOrdenesHibernate> detalles = usuarioControlers.getDetalles_ordenes();
        
         for( DetallesOrdenesHibernate detalle : detalles){
            lista.add(detalle);
        }               
    }
    
    
    //metodo para insertar cliente
    public void insertarDetalleOrden(){
    
   Long orden_id = datosAInsertar.getOrden_id();
   Long producto_id = datosAInsertar.getProducto_id();
   String cantidad = datosAInsertar.getCantidad();
   String precio = datosAInsertar.getPrecio();
        
    String detalle = new ControladorDetallesOrdenesHibernate().crearDetallesOrdenes(orden_id, producto_id, cantidad, precio);
    
    }
    
    //metodo para actualizar cliente
    
    public void actualizarDetalleOrden(){
        
   Long id = datosActualizar.getId();
   Long orden_id = datosActualizar.getOrden_id();
   Long producto_id = datosActualizar.getProducto_id();
   String cantidad = datosActualizar.getCantidad();
   String precio = datosActualizar.getPrecio();
        
      String detalle = new ControladorDetallesOrdenesHibernate().ActualizarDetalleOrden(id, orden_id, producto_id, cantidad, precio);     
    }
   
    public void eliminarDetalleOrden(){
        
    Long id = datosAInsertar.getId();
    
    String cliente = new ControladorDetallesOrdenesHibernate().EliminarDetalleOrden(id);
    
    }
    
   
}
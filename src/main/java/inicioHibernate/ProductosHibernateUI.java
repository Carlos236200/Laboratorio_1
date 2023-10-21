/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicioHibernate;

/**
 *
 * @author c4180
 */
import controladorHibernate.ControladorProductos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import productosHibernate.ProductosHibernate;


@ManagedBean(name="bkn_Productos")
public class ProductosHibernateUI implements Serializable{
    
      private List<ProductosHibernate> lista = new ArrayList<>();
      private ProductosHibernate datosAInsertar = new ProductosHibernate();

    public ProductosHibernate getDatosActualizar() {
        return datosActualizar;
    }

    public void setDatosActualizar(ProductosHibernate datosActualizar) {
        this.datosActualizar = datosActualizar;
    }
      private ProductosHibernate datosActualizar = new ProductosHibernate();

    public ProductosHibernate getDatosAInsertar() {
        return datosAInsertar;
    }

    public void setDatosAInsertar(ProductosHibernate datosAInsertar) {
        this.datosAInsertar = datosAInsertar;
    }   

    public List<ProductosHibernate> getLista() {
        return lista;
    }

    public void setLista(List<ProductosHibernate> lista) {
        this.lista = lista;
    }
    
    
     @PostConstruct
    public void init(){
        
        ControladorProductos usuarioControlers = new ControladorProductos();
        List<ProductosHibernate> productos = usuarioControlers.getProductos();
        
         for(ProductosHibernate producto : productos){
            lista.add(producto);
        }               
    }
    
    
    //metodo para insertar cliente
    public void insertarProducto(){
    
   String nombre = datosAInsertar.getNombre();
   String descripcion = datosAInsertar.getDescripcion();
   String precio = datosAInsertar.getPrecio();
   String cantidad = datosAInsertar.getCantidad();
        
    String producto = new ControladorProductos().crearProductos(nombre, descripcion, precio, cantidad);
    
    }
    
    //metodo para actualizar cliente
    
    public void actualizarProducto(){
        
   Long id = datosActualizar.getId();
   String nombre = datosActualizar.getNombre();
   String descripcion = datosActualizar.getDescripcion();
   String precio = datosActualizar.getPrecio();
   String cantidad = datosActualizar.getCantidad();
        
      String producto = new ControladorProductos().ActualizarProducto(id, nombre, descripcion, precio, cantidad);     
    }
   
    public void eliminarProducto(){
        
    Long id = datosAInsertar.getId();
    
    String producto = new ControladorProductos().EliminarProducto(id);
    
    }
    
   
}

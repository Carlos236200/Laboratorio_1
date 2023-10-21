/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package detallesOrdeneshibernate;

/**
 *
 * @author c4180
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //para decirle que es una entidad(BD)
@Table(name="detalles_ordenes")
public class DetallesOrdenesHibernate {
    
     
    
    @Id
    @Column(name="id")
    private Long id;
    
     @Column(name="orden_id")
    private Long orden_id;
     
      @Column(name="producto_id")
    private Long producto_id;
      
       @Column(name="cantidad")
    private String cantidad;
       
        @Column(name="precio")
    private String precio;

    public DetallesOrdenesHibernate() {
    	//constructor por defecto
    };    
        
    public DetallesOrdenesHibernate( Long orden_id, Long producto_id, String cantidad, String precio) {
        this.orden_id = orden_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.precio = precio;
    }
     
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }

    public Long getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(Long orden_id) {
        this.orden_id = orden_id;
    }

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad =  cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetallesOrdenesHibernate{" + "id=" + id + ", orden_id=" + orden_id + ", producto_id=" + producto_id + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
    
    
}
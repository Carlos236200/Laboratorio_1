/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenHibernate;

/**
 *
 * @author c4180
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //para decirle que es una entidad(BD)
@Table(name="Ordenes")
public class OrdenesHibernate {
    
      //DATOS PARA LA TABLA CLIENTE 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
     @Column(name="cliente_id")
    private Long cliente_id;
     
      @Column(name="fecha")
    private String fecha;
      
       @Column(name="total")
    private String total;
      

    public OrdenesHibernate() {
    	
    };    
        
    public OrdenesHibernate( Long cliente_id, String fecha, String total) {
        this.cliente_id = cliente_id;
        this.fecha = fecha;
        this.total = total;
    }
     
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "OrdenesHibernate{" + "id=" + id + ", cliente_id=" + cliente_id + ", fecha=" + fecha + ", total=" + total + '}';
    }
    
    
    
}
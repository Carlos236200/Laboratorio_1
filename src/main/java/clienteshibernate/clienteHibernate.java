/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteshibernate;

/**
 *
 * @author c4180
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //para decirle que es una entidad(BD)
@Table(name="clientes")
public class clienteHibernate {
    
      //DATOS PARA LA TABLA CLIENTE 
    
    @Id
    @Column(name="id")
    private Long id;
    
     @Column(name="nombre")
    private String nombre;
     
      @Column(name="correo")
    private String correo;
      
       @Column(name="direccion")
    private String direccion;
       
        @Column(name="telefono")
    private String telefono;

    public clienteHibernate() {
    	
    };    
        
    public clienteHibernate( String nombre, String correo, String direccion, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }
     
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "clienteHibernate{" + "id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }
    
    
    
}
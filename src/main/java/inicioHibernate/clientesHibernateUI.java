/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicioHibernate;

/**
 *
 * @author c4180
 */
import clienteshibernate.clienteHibernate;
import controladorHibernate.ControladorClientes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


@ManagedBean(name="bkn_Cliente")
public class clientesHibernateUI implements Serializable{
    
      private List<clienteHibernate> lista = new ArrayList<>();
      private clienteHibernate datosInsert = new clienteHibernate();

    public clienteHibernate getDatosActualizar() {
        return datosActualizar;
    }

    public void setDatosActualizar(clienteHibernate datosActualizar) {
        this.datosActualizar = datosActualizar;
    }
      private clienteHibernate datosActualizar = new clienteHibernate();

    public clienteHibernate getDatosInsert() {
        return datosInsert;
    }

    public void setDatosInsert(clienteHibernate datosInsert) {
        this.datosInsert = datosInsert;
    }   

    public List<clienteHibernate> getLista() {
        return lista;
    }

    public void setLista(List<clienteHibernate> lista) {
        this.lista = lista;
    }
    
    
     @PostConstruct
    public void init(){
        
        ControladorClientes usuarioControlers = new ControladorClientes();
        List<clienteHibernate> clientes = usuarioControlers.getClientes();
        
         for(clienteHibernate cliente : clientes){
            lista.add(cliente);
        }               
    }
    
    
    //metodo para insertar cliente
    public void insertarCliente(){
    
   String nombre = datosInsert.getNombre();
   String correo = datosInsert.getCorreo();
   String direccion = datosInsert.getDireccion();
   String telefono = datosInsert.getTelefono();
        
    String cliente = new ControladorClientes().createCliente(nombre, correo, direccion, telefono);
    
    }
    
    //metodo para actualizar cliente
    
    public void actualizarCliente(){
        
   Long id = datosActualizar.getId();
   String nombre = datosActualizar.getNombre();
   String correo = datosActualizar.getCorreo();
   String direccion = datosActualizar.getDireccion();
   String telefono = datosActualizar.getTelefono();
        
      String cliente = new ControladorClientes().ActualizarCliente(id, nombre, correo, direccion, telefono);     
    }
   
    public void eliminarCliente(){
        
    Long id = datosInsert.getId();
    
    String cliente = new ControladorClientes().EliminarCliente(id);
    
    }
    
   
}
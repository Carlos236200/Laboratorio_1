/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorHibernate;

/**
 *
 * @author c4180
 */
import clienteshibernate.clienteHibernate;
import detallesOrdeneshibernate.DetallesOrdenesHibernate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

// crud con hibernate 
public class ControladorDetallesOrdenesHibernate {
    
 public  String crearDetallesOrdenes(Long orden_id, Long producto_id, String cantidad, String precio){    
   SessionFactory sessionFactory   = new
   Configuration().configure("hibernate.cfg.xml").
           addAnnotatedClass(DetallesOrdenesHibernate.class).
           buildSessionFactory();
       
   Session session = sessionFactory.openSession();
   
       try {
           
      DetallesOrdenesHibernate clientes = new DetallesOrdenesHibernate(orden_id, producto_id, cantidad, precio);  
      session.beginTransaction();  //conexion a base de datos 
      
      session.save(clientes);
      
      session.getTransaction().commit(); //Guardar cambios en base de datos 
      
      sessionFactory.close();
      
       return "Detalle Orden Creada";            
       } catch (Exception e) {
           e.printStackTrace();
       }
   
     return "Error al crear Detalle Orden";
       
    }
  
   //metodo para eliminar clientes 
 
 	public String EliminarDetalleOrden(Long id) {
 		
 	   SessionFactory sessionFactory   = new
 	   Configuration().configure("hibernate.cfg.xml").
           addAnnotatedClass(clienteHibernate.class).
           buildSessionFactory();
 			       
            Session session = sessionFactory.openSession();
 			   
 	 try {
 			           
        session.beginTransaction(); //conexion a base de datos 
 			      
        clienteHibernate cliente = session.get(clienteHibernate.class, id); //recibir id del cliente
 			      
        session.delete(cliente);
 			      
 	session.getTransaction().commit(); //guardar cambios en base de datos
 			      
        sessionFactory.close(); 
 			      
        return "Detalle de Orden Eliminada";    
 			       
        } catch (Exception e) {
 	   e.printStackTrace();
       }
 				
 		return "No se pudo eliminar detalle Orden";
 	}
    
        //metodo de actualizacion de clientes

    public String ActualizarDetalleOrden(Long id, Long orden_id, Long producto_id, String cantidad, String precio) {

      // Crear una instancia de la SessionFactory.
      SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DetallesOrdenesHibernate.class).buildSessionFactory();

      // Crear una sesión.
      Session session = sessionFactory.openSession();

      // Iniciar una transacción.
      Transaction transaction = session.beginTransaction();

      // Obtener el cliente de la base de datos.
      DetallesOrdenesHibernate detalle = session.get(DetallesOrdenesHibernate.class, id);

      // Actualizar los datos del detalle orden
      if (orden_id != null) {
        detalle.setOrden_id(orden_id);
      }
      if (producto_id != null) {
        detalle.setProducto_id(producto_id);
      }
      if (cantidad != null && !cantidad.isEmpty()) {
        detalle.setCantidad(cantidad);
      }
      if (precio != null && !precio.isEmpty()) {
        detalle.setPrecio(precio);
      }

      // Guardar los cambios en la base de datos.
      session.update(detalle);

      // Confirmar la transacción.
      transaction.commit();

      // Cerrar la sesión y la SessionFactory.
      session.close();
      sessionFactory.close();

      // Devolver un mensaje de éxito o error.
      if (detalle != null) {
        return "Cliente actualizado correctamente";
      } else {
        return "Error al actualizar cliente";
      }
    }
        
        //Mostrar clientes
  public List<DetallesOrdenesHibernate> getDetalles_ordenes() {

    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(DetallesOrdenesHibernate.class)
            .buildSessionFactory();

    Session session = sessionFactory.openSession();

    try {
        session.beginTransaction();

        Query query = session.createQuery("from DetallesOrdenesHibernate");

        List<DetallesOrdenesHibernate> detalles_ordenes = query.list();

        session.getTransaction().commit();

        sessionFactory.close();

        return detalles_ordenes;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
        
 	
 	
}

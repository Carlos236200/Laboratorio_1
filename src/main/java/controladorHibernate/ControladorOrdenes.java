/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorHibernate;

/**
 *
 * @author c4180
 */
import java.util.List;
import ordenHibernate.OrdenesHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

// crud con hibernate 
public class ControladorOrdenes {
    
 public  String crearOrdenes(Long cliente_id, String fecha, String total){    
   SessionFactory sessionFactory   = new
   Configuration().configure("hibernate.cfg.xml").
           addAnnotatedClass(OrdenesHibernate.class).
           buildSessionFactory();
       
   Session session = sessionFactory.openSession();
   
       try {
           
      OrdenesHibernate productos = new OrdenesHibernate(cliente_id, fecha, total);  
      session.beginTransaction(); //para abrir la conexion a la base de datos 
      
      session.save(productos);
      
      session.getTransaction().commit(); //para que grabe los cambios en la bse de datos 
      
      sessionFactory.close(); //para cerrar todo
      
       return "Orden Creada";            
       } catch (Exception e) {
           e.printStackTrace();
       }
   
     return "No se pudo crear la orden";
       
    }
  
   //metodo para eliminar clientes 
 
 	public String EliminarOrden(Long id) {
 		
 	   SessionFactory sessionFactory   = new
 	   Configuration().configure("hibernate.cfg.xml").
           addAnnotatedClass(OrdenesHibernate.class).
           buildSessionFactory();
 			       
            Session session = sessionFactory.openSession();
 			   
 	 try {
 			           
        session.beginTransaction(); //para abrir la conexion a la base de datos 
 			      
        OrdenesHibernate producto = session.get(OrdenesHibernate.class, id); //para obtener el id del cliente a eliminar
 			      
        session.delete(producto);
 			      
 	session.getTransaction().commit(); //para que grabe los cambios en la bse de datos 
 			      
        sessionFactory.close(); //para cerrar todo
 			      
        return "Orden Eliminada";    
 			       
        } catch (Exception e) {
 	   e.printStackTrace();
       }
 				
 		return "No se pudo eliminar la orden";
 	}
    
        //metodo de actualizacion de clientes

    public String ActualizarOrden(Long id, Long cliente_id, String fecha, String total) {

      // Crear una instancia de la SessionFactory.
      SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OrdenesHibernate.class).buildSessionFactory();

      // Crear una sesión.
      Session session = sessionFactory.openSession();

      // Iniciar una transacción.
      Transaction transaction = session.beginTransaction();

      // Obtener el cliente de la base de datos.
      OrdenesHibernate orden = session.get(OrdenesHibernate.class, id);

      // Actualizar los datos del cliente.
      if (cliente_id != null) {
        orden.setCliente_id(cliente_id);
      }
      if (fecha != null && !fecha.isEmpty()) {
        orden.setFecha(fecha);
      }
      if (total != null && !total.isEmpty()) {
        orden.setTotal(total);
      }


      // Guardar los cambios en la base de datos.
      session.update(orden);

      // Confirmar la transacción.
      transaction.commit();

      // Cerrar la sesión y la SessionFactory.
      session.close();
      sessionFactory.close();

      // Devolver un mensaje de éxito o error.
      if (total != null) {
        return "orden actualizada correctamente";
      } else {
        return "No se pudo actualizar la orden";
      }
    }
        
        //para mostrar los clientes 
  public List<OrdenesHibernate> getOrdenes() {

    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(OrdenesHibernate.class)
            .buildSessionFactory();

    Session session = sessionFactory.openSession();

    try {
        session.beginTransaction();

        Query query = session.createQuery("from OrdenesHibernate");

        List<OrdenesHibernate> ordenes = query.list();

        session.getTransaction().commit();

        sessionFactory.close();

        return ordenes;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
} 	
}

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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import productosHibernate.ProductosHibernate;

// crud con hibernate 
public class ControladorProductos {
    
 public  String crearProductos(String nombre, String descripcion, String precio, String cantidad){    
   SessionFactory sessionFactory   = new
   Configuration().configure("hibernate.cfg.xml").
           addAnnotatedClass(ProductosHibernate.class).
           buildSessionFactory();
       
   Session session = sessionFactory.openSession();
   
       try {
           
      ProductosHibernate productos = new ProductosHibernate(nombre, descripcion, precio, cantidad);  
      session.beginTransaction(); //para abrir la conexion a la base de datos 
      
      session.save(productos);
      
      session.getTransaction().commit(); //para que grabe los cambios en la bse de datos 
      
      sessionFactory.close(); //para cerrar todo
      
       return "Producto creado";            
       } catch (Exception e) {
           e.printStackTrace();
       }
   
     return "No se pudo crear producto";
       
    }
  
   //metodo para eliminar clientes 
 
 	public String EliminarProducto(Long id) {
 		
 	   SessionFactory sessionFactory   = new
 	   Configuration().configure("hibernate.cfg.xml").
           addAnnotatedClass(ProductosHibernate.class).
           buildSessionFactory();
 			       
            Session session = sessionFactory.openSession();
 			   
 	 try {
 			           
        session.beginTransaction(); //para abrir la conexion a la base de datos 
 			      
        ProductosHibernate producto = session.get(ProductosHibernate.class, id); //para obtener el id del cliente a eliminar
 			      
        session.delete(producto);
 			      
 	session.getTransaction().commit(); //para que grabe los cambios en la bse de datos 
 			      
        sessionFactory.close(); //para cerrar todo
 			      
        return "Producto Eliminado";    
 			       
        } catch (Exception e) {
 	   e.printStackTrace();
       }
 				
 		return "No se pudo eliminar";
 	}
    
        //metodo de actualizacion de clientes

    public String ActualizarProducto(Long id, String nombre, String descripcion, String precio, String cantidad) {

      // Crear una instancia de la SessionFactory.
      SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ProductosHibernate.class).buildSessionFactory();

      // Crear una sesión.
      Session session = sessionFactory.openSession();

      // Iniciar una transacción.
      Transaction transaction = session.beginTransaction();

      // Obtener el cliente de la base de datos.
      ProductosHibernate producto = session.get(ProductosHibernate.class, id);

      // Actualizar los datos del cliente.
      if (nombre != null && !nombre.isEmpty()) {
        producto.setNombre(nombre);
      }
      if (descripcion != null && !descripcion.isEmpty()) {
        producto.setDescripcion(descripcion);
      }
      if (precio != null && !precio.isEmpty()) {
        producto.setPrecio(precio);
      }
      if (cantidad != null && !cantidad.isEmpty()) {
        producto.setCantidad(cantidad);
      }

      // Guardar los cambios en la base de datos.
      session.update(producto);

      // Confirmar la transacción.
      transaction.commit();

      // Cerrar la sesión y la SessionFactory.
      session.close();
      sessionFactory.close();

      // Devolver un mensaje de éxito o error.
      if (producto != null) {
        return "Producto actualizado correctamente";
      } else {
        return "No se pudo actualizar el producto";
      }
    }
        
        //para mostrar los clientes 
  public List<ProductosHibernate> getProductos() {

    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(ProductosHibernate.class)
            .buildSessionFactory();

    Session session = sessionFactory.openSession();

    try {
        session.beginTransaction();

        Query query = session.createQuery("from ProductosHibernate");

        List<ProductosHibernate> productos = query.list();

        session.getTransaction().commit();

        sessionFactory.close();

        return productos;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
} 	
}
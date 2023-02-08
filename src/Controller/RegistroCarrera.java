package Controller;

import Model.Carrera;
import DB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 *
 * @author Matias
 */
public class RegistroCarrera {
    
    public boolean agregarCarrera(Carrera carrera){
    
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            String query = "INSERT INTO carrera(nombre) VALUES (?)";
            PreparedStatement stmt = cnx.prepareCall(query);
            stmt.setString(1, carrera.getNombre());
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
            
            
        } catch (SQLException e) {
            
            System.out.println("Error en la consulta SQL agregar: "+ e.getMessage());
            return false;
        }
    
    }
    
    
    public boolean eliminarCarrera(String nombre){
    
        boolean flag = false;
        
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            String query = "DELETE FROM carrera WHERE nombre = ?";
            PreparedStatement stmt = cnx.prepareCall(query);
            stmt.setString(1, nombre);
            
            int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que deas eliminar?", "Eliminar Carrera", 1);
            
            if (resp == 0) {
                
                stmt.executeUpdate();
                stmt.close();
                cnx.close();
                flag = true;    
            }
            

        } catch (SQLException e) {
            
            System.out.println("Error en la consulta SQL eliminar: "+ e.getMessage());
            flag = false;
        }
        
        return flag;
    
    } 
    
    public boolean actualizarCarrera(String nombre, String nombreNew){
    
        
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            String query = "UPDATE carrera SET nombre = ? WHERE nombre = ?";
            PreparedStatement stmt = cnx.prepareCall(query);
            stmt.setString(1, nombreNew);
            stmt.setString(2, nombre);
 
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;    
              
        } catch (SQLException e) {
            
            System.out.println("Error en la consulta SQL actualizar: "+ e.getMessage());
            return false;
        }
        
    
    } 

    
    public Carrera buscarCarreraNombre(String nombre){
    
        Carrera car = new Carrera();
        
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            String query = "SELECT * From carrera WHERE nombre = ?";
            PreparedStatement stmt = cnx.prepareCall(query);
            stmt.setString(1, nombre);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                car.setIdCarrera(rs.getInt("idCarrera"));
                car.setNombre(rs.getString("nombre"));
                
            }
            
            
            stmt.close();
            cnx.close();   
              
        } catch (SQLException e) {
            
            System.out.println("Error en la consulta SQL buscar por nombre: "+ e.getMessage());
            
        }
        return car;
    
    } 
    
    public List<Carrera> buscarTodasCarreras(){
    
        List<Carrera> lista = new ArrayList<>();
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            String query = "SELECT * From carrera";
            PreparedStatement stmt = cnx.prepareCall(query);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Carrera car = new Carrera();
                car.setIdCarrera(rs.getInt("idCarrera"));
                car.setNombre(rs.getString("nombre"));
                
                lista.add(car);
                
            }
            
            
            stmt.close();
            cnx.close();   
              
        } catch (SQLException e) {
            
            System.out.println("Error en la consulta SQL traer todos los datos: "+ e.getMessage());
            
        }
        return lista;
    
    } 
}

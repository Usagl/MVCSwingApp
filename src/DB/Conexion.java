package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Matias
 */
public class Conexion {
    
    public Connection obtenerConexion(){
    
        Connection conexion = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/estudios", "root", "");
            
            System.out.println("Conexion establecida correctamente!");
            
        } catch (ClassNotFoundException | SQLException e) {
            
            System.out.println("Error al conectar :" + e.getMessage());
            
        }
        return conexion;
    }
    
    
}

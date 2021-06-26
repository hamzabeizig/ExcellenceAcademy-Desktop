package pi.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21655
 */
public class MyConnection1 {
    private  String url ="jdbc:mysql://localhost:3306/ea1";
    private  String user ="root";
    private  String pwd ="";
    
    private static Connection cnx;
 
    static MyConnection1 instance;
    
    private MyConnection1() {
        
        try {
            cnx = DriverManager.getConnection(url, user, pwd);

        } catch (SQLException ex) {
            Logger.getLogger(MyConnection1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MyConnection1 getInstance(){
        if(instance == null)
            instance = new MyConnection1();
        
        return instance;
    }

    public static Connection getConnection() {
        return cnx;
    }
    
}
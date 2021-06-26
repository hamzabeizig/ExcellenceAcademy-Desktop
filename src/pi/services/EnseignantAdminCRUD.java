
package pi.services;


import pi.entities.Classe;
import pi.entities.Enseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import pi.tools.MyConnection;

/**
 *
 * @author Fayechi
 */
public class EnseignantAdminCRUD {
        Connection cnx;
        Statement ste;
        ResultSet rs;

    public EnseignantAdminCRUD() {
       cnx = MyConnection.getInstance().getConnection();
         try {
             ste =  cnx.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(EnseignantAdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
  
    public ObservableList<String> displayAlll() 
     {
         ObservableList<String> list=FXCollections.observableArrayList();
        String req="select nom , prenom from user where (UPPER(role) = 'ENSEIGNANT')";
       try {
           rs=ste.executeQuery(req);
            while(rs.next()){
               String cl;
               cl= rs.getString(1) +" "+ rs.getString(2);
                list.add(cl);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantAdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}

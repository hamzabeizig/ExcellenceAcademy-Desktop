/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;

import pi.entities.Classe;
import pi.entities.Matiere;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.tools.MyConnection;

/**
 *
 * @author walid
 */
public class ClasseAdminCRUD {
    Connection cnx;
        Statement ste;
        ResultSet rs;

    public ClasseAdminCRUD() {
       cnx = MyConnection.getInstance().getConnection();
         try {
             ste =  cnx.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(ClasseAdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
     
    
    public ObservableList<String> displayAlll() 
     {
         ObservableList<String> list=FXCollections.observableArrayList();
        String req="select nom_classe , nom_salle from classe";
       try {
           rs=ste.executeQuery(req);
            while(rs.next()){
               String m;
               m=rs.getString(1)+ " - " + rs.getString(2);
                list.add(m);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseAdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}

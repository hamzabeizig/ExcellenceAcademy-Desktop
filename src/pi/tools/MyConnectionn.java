/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import pi.entities.Stages;


public class MyConnectionn {
    
    public static Connection connect(){
        try {
            String url ="jdbc:mysql://localhost:3306/ea1";
            String login="root";
            String pwd ="";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,login,pwd);
            return conn ; 
            
        } catch (ClassNotFoundException | SQLException e ) {
            Logger.getLogger(MyConnectionn.class.getName()).log(Level.SEVERE,null,e);
            
        }
    return null ;
    }
    
    public static void  save (Stages stg) throws SQLException{
        
        String sql="INSERT INTO stage ('societe','pays','date_debut','date_fin','type_stage') VALUES ('"+stg.getSociete()+"','"+stg.getPays()+"','"+stg.getDate_debut()+"','"+stg.getDate_fin()+"','"+stg.getType_stage()+"')" ; 
                Connection con=MyConnectionn.connect();
                PreparedStatement stat ;
                stat=con.prepareStatement(sql);
                stat.executeUpdate(sql);
                con.close();

                
    }
}
    
   
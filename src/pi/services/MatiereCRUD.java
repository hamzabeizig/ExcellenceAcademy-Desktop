/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import pi.entities.Matiere;
import pi.tools.MyConnection;

/**
 *
 * @author pc
 */

public class MatiereCRUD {
     private Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs ; 
    public MatiereCRUD () {
        cnx = MyConnection.getInstance().getConnection();
    }
     public void ajoutMatiere( Matiere m){
       String req ="INSERT INTO matiere (nom_matiere,coefficient,volume_h)"+"values (?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1,m.getNom());
            ste.setString(2, m.getCoeff());
            ste.setString(3, m.getVolume_h());
            
       
           ste.executeUpdate();
            System.out.println("Matiere ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
}
      public void supprimerMatiere(Matiere m) {
        String req="DELETE FROM  matiere WHERE id_matiere=?";
         
        try {
            ste=cnx.prepareStatement(req);
            ste.setInt(1,m.getId());
            
            
            ste.executeUpdate();
            System.out.println("Matiere supprimé");
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
}
       public ObservableList<Matiere> afficherMatiere() throws SQLException{
      ObservableList<Matiere> ls = FXCollections.observableArrayList();
      String req =" select id_matiere,nom_matiere,coefficient,volume_h from matiere" ;
      ste= cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while(rs.next())
      {
          Matiere de = new  Matiere();
          de.setId(rs.getInt("id_matiere"));
          de.setNom(rs.getString("nom_matiere"));
      {
          de.setCoeff(rs.getString("coefficient"));
          de.setVolume_h(rs.getString("volume_h"));
          ls.add(de);
      }
      }
      return ls;
      
      
  
  }
    
}

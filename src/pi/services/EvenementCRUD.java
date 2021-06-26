/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;

import pi.entities.Evenement;
import pi.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.entities.Participer;
import pi.entities.Utilisateur;


public class EvenementCRUD {
    
    private static EvenementCRUD instance;
        Connection cnx;
        Statement ste;
        ResultSet rs;

    public EvenementCRUD()  {
        cnx = MyConnection.getInstance().getConnection();
         try {
             ste = cnx.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(EvenementCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
     public static EvenementCRUD getInstance(){
        if(instance==null) 
           instance=new EvenementCRUD();
           return instance;
    }
    
     public void Organiser(Evenement a) throws SQLException {

        String sql = "INSERT INTO evenement (`nom_evenement`,`date_evenement`,`responsable`,`description`,`id_user`,`nbr_place`,`etat`) VALUES ( '" + a.getNom() + "','"+a.getDate()+"','"+a.getResponsable()+"','"+a.getDescription()+"','"+a.getId_user()+"','"+a.getNbr_place()+"','"+a.getEtat()+"')";
        ste.executeUpdate(sql);
            System.out.println("evenement ajoutes");
 
    }
     
     public ObservableList<Evenement> displayAll() 
     {
        String req="select * from evenement";
        ObservableList<Evenement> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Evenement p=new Evenement();
                
                p.setId(rs.getString(1));
                p.setNom(rs.getString(2));
                p.setDate(rs.getDate(3));
                p.setResponsable(rs.getString(4));
                p.setDescription(rs.getString(5));
                p.setEtat(rs.getString("etat"));
                p.setId_user(rs.getString("id_user"));
                p.setNbr_place(rs.getString("nbr_place"));

                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


     public void delete(String id) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("Delete from evenement where id_evenement= ?");
        pre.setString(1,id);
        pre.executeUpdate();
    }

    private Evenement displayById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void update(Evenement p) throws SQLException {
        
        PreparedStatement PS=cnx.prepareStatement("UPDATE `evenement` SET `nom_evenement`=? ,`date_evenement`=?,`responsable`=?,`description`=?,`nbr_place`=? WHERE `id_evenement`=?");
        PS.setString(1, p.getNom());
        PS.setDate(2, p.getDate());
        PS.setString(3, p.getResponsable());
        PS.setString(4, p.getDescription());
        PS.setString(5, p.getNbr_place());
        PS.setString(6, p.getId());

        PS.executeUpdate();

    }
    
        public void updateapp(Evenement p) throws SQLException {
        
        PreparedStatement PS=cnx.prepareStatement("UPDATE `evenement` SET `etat`=?  WHERE `id_evenement`=?");
        PS.setString(1, p.getEtat());
        PS.setString(2, p.getId());

        PS.executeUpdate();

    }

         public void participer(String idevent,String iduser) throws SQLException {
 
       PreparedStatement PS = cnx.prepareStatement("INSERT INTO `participer` ( `idevent`, `iduser`) VALUES (?, ?);");
        PS.setString(1, idevent);
        PS.setString(2, iduser);

        PS.executeUpdate();

    }
       public void departiciper(String idevent,String iduser) throws SQLException {
 
        PreparedStatement PS = cnx.prepareStatement("Delete from participer where idevent= ? and iduser= ?");
        PS.setString(1, idevent);
        PS.setString(2, iduser);

        PS.executeUpdate();

    }

      public List<Participer> checkparticiper() throws SQLException {
        List<Participer> AL = new ArrayList<>();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from participer");
        while (rs.next()) {
            int idp = rs.getInt(1);
            String iduser = rs.getString(2);
            String idevent = rs.getString(3);

            Participer a = new Participer(iduser, idevent);
            AL.add(a);
        }
        return AL;
    }
        public Participer getById(String idevent,String iduser) {
          Participer a = null;
         String requete = " select* from participer where idevent='"+idevent+"' and iduser ='"+iduser+"' " ;
        try {
           
            ste = cnx.createStatement();
            rs=ste.executeQuery(requete);
            if (rs.next())
            {a=new Participer(rs.getInt(1),rs.getString(2),rs.getString(3));}
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }

    public  void deletepart(String id) throws SQLException {
        PreparedStatement PS = cnx.prepareStatement("Delete from participer where idevent= ?");
        PS.setString(1, id);

        PS.executeUpdate();

    }


         public Utilisateur getmail(String iduser) {
             Utilisateur a = null;
         String requete = " select * from user where id_user ='"+iduser+"' " ;
        try {
           
            ste = cnx.createStatement();
            rs=ste.executeQuery(requete);
            if (rs.next())
            {a=new Utilisateur(rs.getString(1),rs.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    
     
}

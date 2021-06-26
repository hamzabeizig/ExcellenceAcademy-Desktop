/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;

import pi.entities.Utilisateur;
import pi.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.entities.Admin;


public class UtilisateurCRUD {
    
       private static UtilisateurCRUD instance;
        Connection cnx;
        Statement ste;
        ResultSet rs;
        
        

    public UtilisateurCRUD () {
        cnx = MyConnection.getInstance().getConnection();
         try {
             ste = cnx.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
     public static UtilisateurCRUD getInstance(){
        if(instance==null) 
           instance=new UtilisateurCRUD();
           return instance;
    }
    
     public void ajouter(Utilisateur a) throws SQLException {

        String ad = "Administrateur";
        String en = "Enseignant";
        String et = "Etudiant";
        
        String requeteInsert = "INSERT INTO user (`user_name`,`nom`, `prenom`,`email`,`date_de_naissance`,`role`,`Mdp`) VALUES ('" + a.getLogin()+ "', '" + a.getNom() + "','"+a.getPrenom()+"','"+a.getMail()+"','"+a.getDdn()+"','"+a.getRole()+"','"+a.getMdp()+"')";
        ste.executeUpdate(requeteInsert);
  
       
        String reqq = "SELECT id_user from user where user_name ='"+a.getLogin()+"' ";
        ResultSet result = ste.executeQuery(reqq);
        
        result.next();
        String id = result.getString(1);
        
//        String requeteInsert1 = "INSERT INTO conn (`id_user`,`img`, `etat`) VALUES ('" +id+ "', null ,0)";
//        ste.executeUpdate(requeteInsert1);
       
        String req;
        
         if (a.getRole().equals(ad))
         {
             req = "Insert INTO admin (`id_user`) values ('"+id+"')";
         }
         else if (a.getRole().equals(et))
         {
             req = "Insert INTO etudiant (`id_user`,niveau,specialite,id_emp,id_stage) values ('"+id+"',null,null,null,null)";
         }
         else
         {
             req = "Insert INTO enseignant (`id_user` , `id_matiere` , `nom_departement`) values ('"+id+"',null ,null)";
         }
        ste.executeUpdate(req);
    }
     
     public ObservableList<Utilisateur> displayAll() 
     {
        String req="select * from user";
        ObservableList<Utilisateur> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Utilisateur p=new Utilisateur();
                p.setIdi(rs.getString(1)); 
                p.setLogini(rs.getString(2));
                p.setNomi(rs.getString(3));
                p.setPrenomi(rs.getString(4));
                p.setMaili(rs.getString(5));
                p.setDdn(rs.getDate(6));
                p.setRolei(rs.getString(7));
                p.setMdp(rs.getString(8));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


     public boolean delete(int id) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("Delete from user where id_user= '"+id+"'");
        pre.executeUpdate();
        return true;
        
    }

    private Admin displayById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean update(Utilisateur p) throws SQLException {
        String qry = "UPDATE user SET user_name = '"+p.getLogin()+"', prenom = '"+p.getPrenom()+"', nom = '"+p.getNom()+"',email = '"+p.getMail()+"',date_de_naissance = '"+p.getDdn()+"', role = '"+p.getRole()+"', mdp = '"+p.getMdp()+"' WHERE id_user = '"+p.getId()+"'";
  
        try {
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
 public ObservableList<Utilisateur> che(int ids) 
     {
        String req="select * from user where id_user = "+ids+" ";
        ObservableList<Utilisateur> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Utilisateur p=new Utilisateur();
                p.setIdi(rs.getString(1)); 
                p.setLogini(rs.getString(2));
                p.setNomi(rs.getString(3));
                p.setPrenomi(rs.getString(4));
                p.setMaili(rs.getString(5));
                p.setDdn(rs.getDate(6));
                p.setRolei(rs.getString(7));
                p.setMdp(rs.getString(8));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 
 public void clean () throws SQLException{
     String s = "Delete From user";
     ste.executeUpdate(s);    
 }
 
 
  public ObservableList<Utilisateur> displayAllN() 
     {
        String req="select * from user order by nom asc";
        ObservableList<Utilisateur> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Utilisateur p=new Utilisateur();
                p.setIdi(rs.getString(1)); 
                p.setLogini(rs.getString(2));
                p.setNomi(rs.getString(3));
                p.setPrenomi(rs.getString(4));
                p.setMaili(rs.getString(5));
                p.setDdn(rs.getDate(6));
                p.setRolei(rs.getString(7));
                p.setMdp(rs.getString(8));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

  public ObservableList<Utilisateur> displayAllP() 
     {
        String req="select * from user Order by prenom asc";
        ObservableList<Utilisateur> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Utilisateur p=new Utilisateur();
                p.setIdi(rs.getString(1)); 
                p.setLogini(rs.getString(2));
                p.setNomi(rs.getString(3));
                p.setPrenomi(rs.getString(4));
                p.setMaili(rs.getString(5));
                p.setDdn(rs.getDate(6));
                p.setRolei(rs.getString(7));
                p.setMdp(rs.getString(8));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

  public ObservableList<Utilisateur> displayAllD() 
     {
        String req="select * from user order by date_de_naissance asc";
        ObservableList<Utilisateur> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Utilisateur p=new Utilisateur();
                p.setIdi(rs.getString(1)); 
                p.setLogini(rs.getString(2));
                p.setNomi(rs.getString(3));
                p.setPrenomi(rs.getString(4));
                p.setMaili(rs.getString(5));
                p.setDdn(rs.getDate(6));
                p.setRolei(rs.getString(7));
                p.setMdp(rs.getString(8));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

   public ObservableList<Utilisateur> displayAllR() 
     {
        String req="select * from user order by role asc";
        ObservableList<Utilisateur> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Utilisateur p=new Utilisateur();
                p.setIdi(rs.getString(1)); 
                p.setLogini(rs.getString(2));
                p.setNomi(rs.getString(3));
                p.setPrenomi(rs.getString(4));
                p.setMaili(rs.getString(5));
                p.setDdn(rs.getDate(6));
                p.setRolei(rs.getString(7));
                p.setMdp(rs.getString(8));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
    public void ajouterad(Admin j) throws SQLException {

        String requeteInse = "INSERT INTO admin(`id_user`) VALUES ('"+j.getId()+"')";
        ste.executeUpdate(requeteInse); 
         
    }

    public String connecter(String h , String x) throws SQLException
    {   
        String req = " SELECT id_user from User where user_name = '"+h+"' AND mdp = '"+x+"' ";
        ResultSet v = ste.executeQuery(req);
        v.next();
        String l = v.getString(1);
        return l;  
        //return id
        
     }
    
        public String connecter2(String m) throws SQLException
    {   
        String req = " SELECT role from User where id_user = '"+m+"'";
        ResultSet v = ste.executeQuery(req);
        v.next();
        String p = v.getString(1);
        return p; 
        //return role
     }
        
        public void deconnecter() throws SQLException
        {
            
        String req1 = " Update conn SET etat = 1 ";
        ResultSet v1 = ste.executeQuery(req1);
        }
        
        public Utilisateur fill(String g) 
     {
        String req="select * from user where user_name = '"+g+"' ";
        Utilisateur p=new Utilisateur();
        try {
                rs=ste.executeQuery(req);
                rs.next();
 
                p.setId(rs.getString(1)); 
                p.setLogin(rs.getString(2));
                p.setNom(rs.getString(3));
                p.setPrenom(rs.getString(4));
                p.setMail(rs.getString(5));
                p.setDdn(rs.getDate(6));
                p.setRole(rs.getString(7));
                p.setMdp(rs.getString(8));       
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p);
        return p;
    }
        
        public String mdp (String l) throws SQLException
        {
            String req = "SELECT mdp from user where user_name = '"+l+"' ";    
            ResultSet r = ste.executeQuery(req);  
            r.next();
            String m = r.getString(1);
            //System.out.println(m);
            return m;
        }
        public String mail (String j) throws SQLException
        {
            String req = "SELECT email from user where user_name = '"+j+"' ";    
            ResultSet r = ste.executeQuery(req);  
            r.next();
            String m = r.getString(1);
          //  System.out.println(m);
            return m;
        }
        
        
        
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;

/**
 *
 * @author pc
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pi.entities.Departement;
import pi.entities.Enseignant;
import pi.entities.Reunion;
import pi.entities.Utilisateur;
import pi.gui.DepartementController;
import pi.tools.MyConnection;
public class DepartementCRUD {
     private Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs ; 

public DepartementCRUD () {
        cnx = MyConnection.getInstance().getConnection();
    }
 public void ajoutDepartement(Departement d){
       String req ="INSERT INTO departement (nom_departement,chef_departement)"+"values (?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1,d.getNom());
            ste.setString(2, d.getNom_chef());
            
       
           ste.executeUpdate();
            System.out.println("Departement ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
}
 public void supprimerDepartement(Departement d) {
        String req="DELETE FROM  departement WHERE nom_departement=?";
         
        try {
            ste=cnx.prepareStatement(req);
            ste.setString(1,d.getNom());
            
            
            ste.executeUpdate();
            System.out.println("deaprtement supprimé");
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
}
  public ObservableList<Departement> afficherdepartement() throws SQLException{
      ObservableList<Departement> ls = FXCollections.observableArrayList();
      String req =" select id_departement,nom_departement,chef_departement from departement" ;
      ste= cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while(rs.next())
      {
          Departement de = new  Departement();
          de.setId(rs.getInt("id_departement"));
          de.setNom(rs.getString("nom_departement"));
      {
          de.setNom_chef(rs.getString("chef_departement"));
          ls.add(de);
      }
      }
      return ls;
      
      
  
  }
  public List<String> affichernomdep() throws SQLException{
      List<String> ls =new ArrayList<String>();
      String req =" select nom_departement from departement" ;
      ste= cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while(rs.next())
      {
          ls.add(rs.getString("nom_departement"));
      }
      return ls;
      
      
  }
  public List<String> affichermat()throws SQLException
  {
      List<String> ls =new ArrayList<String>();
      String req =" select nom_matiere from matiere" ;
       ste= cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while(rs.next())
      {
          ls.add(rs.getString("nom_matiere"));
      }
      return ls;
  }
  public List<String> afficherusrnmens() throws SQLException {
      List<String> ls =new ArrayList<>();
      String req ="select user_name from user where role='enseignant'";
      ste=cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while (rs.next())
      {
          ls.add(rs.getString("user_name"));
      }
      return ls ;
          
  }

public void affecterchef(String ens,String dep){
            try {
                String email = "";
                
                String req1 =" select  email from user  where user_name='"+ens+"'";
                
                ste=cnx.prepareStatement(req1);
                
                
                rs=ste.executeQuery();
                
                
                while ( rs.next()){
                    
                    email=rs.getString("email");
                    
                }
                
                try {
                    sendMails(dep,email );
                } catch (Exception ex) {
                    Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String req = " update departement set chef_departement='"+ens+"' where nom_departement ='"+dep+"'";
                
                ste=cnx.prepareStatement(req);
                
                
                ste.executeUpdate();
                
                
                System.out.println("chef affecté");
            } catch (SQLException ex) {
                Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        
 
   }
public void affecterens(String ens,String dep){
        try {
            String id = "";
            String email = "";
            String req1 = "select id_user,email from user where user_name = '"+ens+"'";
            ste=cnx.prepareStatement(req1);
            rs=ste.executeQuery();
              while(rs.next())
            {
                             id=rs.getString("id_user");
                             email=rs.getString("email");

            } 
            try {
               sendMail(dep,email);
           } catch (Exception ex) {
               Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
           }

                
            
            String req = " update enseignant set nom_departement='"+dep+"' where id_user ='"+id+"'";
            ste=cnx.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("ensei affecté");
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
public void supprimerens(String ens ){
            try {
     
                String s="";
                String req =" select id_user from user where user_name='"+ens+"' ";
                ste=cnx.prepareStatement(req);
                rs=ste.executeQuery();
                while ( rs.next())
                {
                    s=rs.getString("id_user");
                 
                }
                String req2 =" update enseignant set nom_departement='' where id_user='"+s+"'";
                ste=cnx.prepareStatement(req2);
                ste.executeUpdate();
                System.out.println("ensei supprimé");
            } catch (SQLException ex) {
                Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
}
public void supprimerchef(String dep ){
            try {
                String req =" update departement set chef_departement ='' where nom_departement='"+dep+"' ";
                ste=cnx.prepareStatement(req);
                ste.executeUpdate();
                System.out.println("chef supprimé");
            } catch (SQLException ex) {
                Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    
}
public static void sendMail(String dep ,String recepient ) throws Exception {
    System.out.println("Preparting to send email ");
    Properties properties = new Properties();
    properties.put("mail.smtp.auth","true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    
    String myAccountEmail ="excellenceacademy878@gmail.com";
    String password ="freefire1234";
    
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(myAccountEmail, password);

        }
      
});
    Message message = prepareMessage(dep, session,myAccountEmail, recepient);
    Transport.send(message);
    System.out.println("Message sent sccessfully");
    
}
private static Message prepareMessage(String dep, Session session, String myAccountEmail, String recepient) throws MessagingException{
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new  InternetAddress(recepient));
                message.setSubject("Excellence Academy");
                message.setText("Vous etes affectés au departement "+dep+" ");
                return message;
            } catch (AddressException ex) {
                Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null ;
}
  public static void sendMails(String dep ,String recepient ) throws Exception {
    System.out.println("Preparting to send email ");
    Properties properties = new Properties();
    properties.put("mail.smtp.auth","true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    
    String myAccountEmail ="excellenceacademy878@gmail.com";
    String password ="freefire1234";
    
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(myAccountEmail, password);

        }
      
});
    Message message = prepareMessage1(dep,session,myAccountEmail,recepient);
    Transport.send(message);
    System.out.println("Message sent sccessfully");
    
} 
  private static Message prepareMessage1(String dep,Session session,String myAccountEmail,String recepient) throws MessagingException{
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new  InternetAddress(recepient));
                message.setSubject("Excellence Academy");
                message.setText("Vous etes affectés comme chef au departement" +dep+" ");
                return message;
            } catch (AddressException ex) {
                Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null ;
}
  public void ProgrammerReu ( Reunion r ){
            try {
                String req =" INSERT INTO reunion (date,nom_departement,matiere,objectif,horaire)"+"values (?,?,?,?,?) ";
                ste = cnx.prepareStatement(req);
                ste.setString(1,String.valueOf(r.getDate()));
                ste.setString(2, r.getDepartement());
                ste.setString(3, r.getMatiere());
                ste.setString(4, r.getObjectif());
                ste.setString(5, r.getHoraire());
                
                
                
                ste.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
      
  }
  public void SupprimerReu ( Reunion r){
            try {
                String req =" DELETE FROM  reunion WHERE id_reunion=? ";
                ste=cnx.prepareStatement(req);
                ste.setInt(1,r.getId());
                
                
                
                
                ste.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
  }
  public ObservableList<Reunion>  afficherREu () throws SQLException{
        ObservableList<Reunion> ls = FXCollections.observableArrayList();
      String req =" select id_reunion,date,nom_departement,matiere,horaire from reunion" ;
      ste= cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while(rs.next())
      {
          Reunion r = new  Reunion();
          r.setId(rs.getInt("id_reunion"));
          r.setDepartement(rs.getString("nom_departement"));
      
          r.setDate(rs.getDate("date").toLocalDate());
          r.setMatiere(rs.getString("matiere"));
          r.setHoraire(rs.getString("horaire"));
          ls.add(r);
      
      }
      return ls;
  }
   public List<String> afficherobjreu() throws SQLException{
      List<String> ls =new ArrayList<String>();
      String req =" select objectif from reunion" ;
      ste= cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while(rs.next())
      {
          ls.add(rs.getString("objectif"));
      }
      return ls;
      
      
  }
 
  public static void sendMailre(String heure ,String reu,String date  ,String recepient ) throws Exception {
    System.out.println("Preparting to send email ");
    Properties properties = new Properties();
    properties.put("mail.smtp.auth","true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    
    String myAccountEmail ="excellenceacademy878@gmail.com";
    String password ="freefire1234";
    
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(myAccountEmail, password);

        }
      
});
    Message message = prepareMessag(heure,reu,date,session,myAccountEmail,recepient);
    Transport.send(message);
    System.out.println("Message sent sccessfully");
    
} 
  private static Message prepareMessag(String heure ,String reu,String date,Session session,String myAccountEmail,String recepient) throws MessagingException{
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new  InternetAddress(recepient));
                message.setSubject("Excellence Academy");
                message.setText("Vous avez une reunion a "+date+" avec horaire "+heure+" et comme objectif on va consulter "+reu+" ");
                return message;
            } catch (AddressException ex) {
                Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null ;
}
  public void mailreunion(String heure,String reu , String ens ,String date  ){
    
                try {
                    
                    String email = "";
                    
                    String req1 =" select  email from user  where user_name='"+ens+"'";
                    
                    ste=cnx.prepareStatement(req1);
                    
                    
                    rs=ste.executeQuery();
                    
                    
                    while ( rs.next()){
                        
                        email=rs.getString("email");
                        
                    }
                    
                    try {
                        sendMailre(heure,reu,date,email );
                    } catch (Exception ex) {
                        Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
  }  
  public String datereu (String obj ) throws SQLException{
          
            
  String ls =" ";
      String req =" select date from reunion  where objectif ='"+obj+"'" ;
      ste= cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while(rs.next())
      {
         ls=rs.getString("date");
      }
      return ls;
      
}
  public String horairereu (String obj ) throws SQLException{
          
            
  String ls =" ";
      String req =" select horaire from reunion  where objectif ='"+obj+"'" ;
      ste= cnx.prepareStatement(req);
      rs=ste.executeQuery();
      while(rs.next())
      {
         ls=rs.getString("horaire");
      }
      return ls;
      
}

  public ObservableList<Utilisateur> enseignants( String dep ) throws SQLException {
       
            ObservableList<Enseignant> ensinf =FXCollections.observableArrayList();
            ObservableList<Utilisateur> ensinf1 =FXCollections.observableArrayList();
            
            String req ="select * from enseignant where nom_departement = '"+dep+"'";
            ste=cnx.prepareStatement(req);
            rs=ste.executeQuery();
            while(rs.next())
            {
              Enseignant e =new  Enseignant();
              e.setId(rs.getString("id_user"));
              e.setNom_departement(rs.getString("nom_departement"));
              e.setId_matiere(rs.getString("id_matiere"));
              ensinf.add(e);
            }   
            
            for(int i=0;i<ensinf.size();i++)
            {
            String req2 ="select * from user where id_user="+ensinf.get(i).getId()+"";
              
            ste=cnx.prepareStatement(req2);
            rs=ste.executeQuery();
            while(rs.next())
            {
              Utilisateur u =new  Utilisateur();
              u.setId(rs.getString("id_user"));
              u.setNom(rs.getString("nom"));
              u.setPrenom(rs.getString("prenom"));
              
              ensinf1.add(u);
            }   
            }
            return ensinf1;
            
        } 
    
}

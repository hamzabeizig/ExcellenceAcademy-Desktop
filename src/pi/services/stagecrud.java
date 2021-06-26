package pi.services;

import pi.entities.Evenement;
import pi.entities.Stages;
import pi.entities.convention;
import pi.tools.MyConnection1;
import pi.tools.MyConnectionn;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pi.entities.Utilisateur;

public class stagecrud {
    
    private static stagecrud instance;
        Connection cnx;
        Statement ste;
        PreparedStatement ste1 ;
        ResultSet rs;

    public stagecrud()  {
        cnx = MyConnection1.getInstance().getConnection();
         try {
             ste = cnx.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(stagecrud.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
     public static stagecrud getInstance(){
        if(instance==null) 
           instance=new stagecrud();
           return instance;
    }
    
     public void Ajouter(Stages s) throws SQLException {

        String sql = "INSERT INTO stage (societe,Email_Société,`pays`,`date_debut`,`date_fin`,`type_stage`) VALUES ( '" + s.getSociete() + "','" + s.getEmail_societe() + "','"+s.getPays()+"','"+s.getDate_debut()+"','"+s.getDate_fin()+"','"+s.getType_stage()+"')";
        ste.executeUpdate(sql);
            System.out.println("stage ajouté");
            
            Alert alert=new Alert(AlertType.INFORMATION) ;
    		alert.setTitle("Excellence Academy");
    		alert.setHeaderText(null);
    		alert.setResizable(false);
    		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    		stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
    		alert.setContentText("stage ajouté");
    		alert.showAndWait();
 
    }
     
     

   public void modifier(Stages s) throws SQLException{
	   
       String sql = "Update stage SET societe=? ,Email_Société=? ,pays=? ,date_debut=?,date_fin=?,type_stage=? WHERE id_stage=?";
       
       Connection con;
       con =MyConnectionn.connect();
	   PreparedStatement pre = con.prepareStatement(sql);
       
        pre.setString(1,s.getSociete());
        pre.setString(2,s.getEmail_societe());
        pre.setString(3,s.getPays());
        pre.setString(4, s.getDate_debut().toString());
        pre.setString(5,s.getDate_fin().toString());
        pre.setString(6,s.getType_stage());
        pre.setInt(7,s.getId());
        pre.executeUpdate() ; 
        Alert alert=new Alert(AlertType.INFORMATION) ;
		alert.setTitle("Exellence Academy");
		alert.setHeaderText(null);
		alert.setResizable(false);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
		alert.setContentText("Stage modifié avec succés");
		alert.showAndWait();
   }
   
   
   public void demander(convention cvt) throws SQLException {
	   
	   String sql = "INSERT INTO dem_conv (`user_name`,`nom`,`prenom`,`email`,id_user) VALUES ( '"+cvt.getUser_name()+"','"+cvt.getNom()+"','"+cvt.getPrenom()+"','"+cvt.getEmail()+"','"+cvt.getId_user()+"')";
       ste.executeUpdate(sql);
        System.out.println("demande envoy�e");
           
           Alert alert=new Alert(AlertType.INFORMATION) ;
   		alert.setTitle("Excellence Academy");
   		alert.setHeaderText(null);
   		alert.setResizable(false);
   		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
   		stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
   		alert.setContentText("demande envoyée");
   		alert.showAndWait();
	   
	   
   }
   
   
   public void delete (Stages item) throws SQLException {
	   
	   if(item!=null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Confirmation");
			alert.setContentText("étes-vous sur de vouloir supprimer ce stage ?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				int id=item.getId();
				System.out.println(id) ; 
				Connection con;
		        con =MyConnectionn.connect();
				PreparedStatement pre = con.prepareStatement("Delete from stage where id_stage= ?");
		        pre.setInt(1,id);
		        pre.executeUpdate();
				}		} else { 
					Alert alertt=new Alert(AlertType.INFORMATION) ;
					alertt.setTitle("Exellence Academy");
					alertt.setHeaderText(null);
					alertt.setResizable(false);
					Stage stage = (Stage) alertt.getDialogPane().getScene().getWindow();
					stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
					alertt.setContentText("Prière de selectionner un stage a supprimer");
					alertt.showAndWait();
					
				}	
			}
   public ObservableList<Utilisateur> loginut(String log) throws SQLException {
        
            ObservableList<Utilisateur> ls = FXCollections.observableArrayList();
            String req="select * from user where user_name='"+log+"'";
            ste1=cnx.prepareStatement(req);
            rs=ste1.executeQuery();
            while(rs.next()){
                Utilisateur u = new Utilisateur();
                u.setId(rs.getString("id_user"));
                u.setLogin(rs.getString("user_name"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMail(rs.getString("email"));
                u.setDdn(rs.getDate("date"));
                u.setRole(rs.getString("role"));
                u.setMdp(rs.getString("Mdp"));
                ls.add(u);
                
            }
            return ls ;
            
                    
                    
       
        
       
    
}
	   
	   
	   
   
   
   public static void sendMailaccept(String recepient, convention cvt) throws Exception {
       System.out.println("Preparing to send email");
       Properties properties = new Properties();

       //Enable authentication
       properties.put("mail.smtp.auth", "true");
       //Set TLS encryption enabled
       properties.put("mail.smtp.starttls.enable", "true");
       //Set SMTP host
       properties.put("mail.smtp.host", "smtp.gmail.com");
       //Set smtp port
       properties.put("mail.smtp.port", "587");
       
       properties.put("mail.smtp.ssl.trust", "*");

      //Your gmail address
       String myAccountEmail = "exellenceacademy.cours@gmail.com";
       //Your gmail password
       String password = "hamza123456789";

       //Create a session with account credentials
       Session session = Session.getInstance(properties, new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myAccountEmail, password);
           }
       });
       
       //Prepare email message
       Message message = prepareMessageaccept(session, myAccountEmail, recepient,cvt);

       //Send mail
       Transport.send(message);
       System.out.println("Message sent successfully");
   }

   private static Message prepareMessageaccept(Session session, String myAccountEmail, String recepient, convention cvt ) {
       try {
    	   
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(myAccountEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
           message.setSubject("Convention de stage");        
           BodyPart messageBodyPart = new MimeBodyPart();

           // Now set the actual message
           messageBodyPart.setText("Bonjour"+" "+cvt.getPrenom()+" "+cvt.getNom()+ ",Ci joint une copie de convention que vous doyez remplir afin de valider votre stage " );

           // Create a multipar message
           Multipart multipart = new MimeMultipart();

           // Set text message part
           multipart.addBodyPart(messageBodyPart);
           messageBodyPart = new MimeBodyPart();
             File filename = new File("C:\\Users\\ASUS\\Desktop\\CoursFile2.pdf");
           DataSource source = new FileDataSource(filename);
           messageBodyPart.setDataHandler(new DataHandler(source));
           messageBodyPart.setFileName("convention");
           multipart.addBodyPart(messageBodyPart);

           // Send the complete message parts
           message.setContent(multipart);

           return message;
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return null;
   }
   
   
   public static void sendMailrefuser(String recepient, convention cvt) throws Exception {
       System.out.println("Preparing to send email");
       Properties properties = new Properties();

       //Enable authentication
       properties.put("mail.smtp.auth", "true");
       //Set TLS encryption enabled
       properties.put("mail.smtp.starttls.enable", "true");
       //Set SMTP host
       properties.put("mail.smtp.host", "smtp.gmail.com");
       //Set smtp port
       properties.put("mail.smtp.port", "587");
       
       properties.put("mail.smtp.ssl.trust", "*");

       //Your gmail address
       String myAccountEmail = "exellenceacademy.cours@gmail.com";
       //Your gmail password
       String password = "hamza123456789";

       //Create a session with account credentials
       Session session = Session.getInstance(properties, new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myAccountEmail, password);
           }
       });
       
       //Prepare email message
       Message message = prepareMessagerefuser(session, myAccountEmail, recepient,cvt);

       //Send mail
       Transport.send(message);
       System.out.println("Message sent successfully");
   }

   private static Message prepareMessagerefuser(Session session, String myAccountEmail, String recepient, convention cvt ) {
       try {
    	   
    	   Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(myAccountEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
           message.setSubject("Convention de stage");
           message.setText("Bonjour"+" "+cvt.getPrenom()+" "+cvt.getNom()+" "+ "nous regrettons de ne pouvoir donner suite favorable a votre demande d'une convention, contactez l administration pour plus de détails" );

           
           return message;

          

       
           

          
          
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return null;
   }
    
   public static void sendMailpostulation(String recepient,File cv ,File ldm ) throws Exception {
       System.out.println("Preparing to send email");
       Properties properties = new Properties();
      
       //Enable authentication
       properties.put("mail.smtp.auth", "true");
       //Set TLS encryption enabled
       properties.put("mail.smtp.starttls.enable", "true");
       //Set SMTP host
       properties.put("mail.smtp.host", "smtp.gmail.com");
       //Set smtp port
       properties.put("mail.smtp.port", "587");
       
       properties.put("mail.smtp.ssl.trust", "*");

    //Your gmail address
       String myAccountEmail = "exellenceacademy.cours@gmail.com";
       //Your gmail password
       String password = "hamza123456789";

       //Create a session with account credentials
       Session session = Session.getInstance(properties, new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myAccountEmail, password);
           }
       });
       
       //Prepare email message
       Message message = prepareMessagepostulation(session, myAccountEmail, recepient,cv,ldm);

       //Send mail
       Transport.send(message);
       System.out.println("Message sent successfully");
   }

   private static Message prepareMessagepostulation(Session session, String myAccountEmail, String recepient, File cv ,File ldm ) {
 try {
    	   
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(myAccountEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
           message.setSubject("postulation");        
           BodyPart messageBodyPart = new MimeBodyPart();

           // Now set the actual message
           messageBodyPart.setText("Bonjour,Ci joint une copie de convention que vous doyez remplir afin de valider votre stage " );

           // Create a multipar message
           Multipart multipart = new MimeMultipart();

           // Set text message part
           multipart.addBodyPart(messageBodyPart);
           String filename = cv.getAbsolutePath();
           DataSource source = new FileDataSource(filename);
           messageBodyPart.setDataHandler(new DataHandler(source));
           messageBodyPart.setFileName(cv.getName());
           multipart.addBodyPart(messageBodyPart);
           BodyPart messageBodyPart2 = new MimeBodyPart();

           String filename1 = ldm.getAbsolutePath();
           DataSource source1 = new FileDataSource(filename1);
           messageBodyPart2.setDataHandler(new DataHandler(source1));
           messageBodyPart2.setFileName(ldm.getName());
           multipart.addBodyPart(messageBodyPart2);
           // Send the complete message parts
           message.setContent(multipart);

           return message;
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return null;
     
}}
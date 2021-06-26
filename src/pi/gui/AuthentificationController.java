/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pi.entities.Enseignant;
import static pi.gui.GuiloginController.e;
import pi.services.EnseignantCRUD;
import pi.services.UtilisateurCRUD;

/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class AuthentificationController implements Initializable {
    public static Enseignant e  ; 

    @FXML
    private TextField logintf;
    @FXML
    private TextField mdptf;
    @FXML
    private Button con;
    @FXML
    private Button quit;
    @FXML
    private Label rq;
    @FXML
    private Button btnmdpo;
    @FXML
    private Label rqcon;

    public static String val1 ; 
    public static String val2 ; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rqcon.setVisible(false);
     
    }  

    @FXML
    private void conec(ActionEvent event) throws SQLException, IOException {
        String log = logintf.getText();
        String mdp = mdptf.getText();
        UtilisateurCRUD j = new UtilisateurCRUD();
        j.connecter2(j.connecter(log, mdp));
        
       if(j.connecter2(j.connecter(log, mdp)).equals("Administrateur") )
       {   
           rqcon.setText("admin");    
           rqcon.setVisible(true);
           loadSecondFxml();
       }
       else if (j.connecter2(j.connecter(log, mdp)).equals("Enseignant") )
       {
           rqcon.setText("enseignant");   
           rqcon.setVisible(true);
           loadens();
       }
       else if(j.connecter2(j.connecter(log, mdp)).equals("Etudiant") )
       {   
           rqcon.setText("etudiant");    
           rqcon.setVisible(true);
           loadfourth();
       }
       else  {rqcon.setVisible(true); rqcon.setText("No !"); }
      
    }

    @FXML
    private void exit(ActionEvent event) {
        
    }

    @FXML
    private void mdpo(ActionEvent event) throws MessagingException, SQLException {
        
      UtilisateurCRUD p = new UtilisateurCRUD();
 
      String r = logintf.getText();
        
      String mdpae = p.mdp(r);
      String mailae = p.mail(r);
             
Properties prop = System.getProperties();
prop.put("mail.smtp.port", "587");
prop.put("mail.smtp.auth", true);
prop.put("mail.smtp.starttls.enable", "true");
Session newSession = Session.getDefaultInstance(prop, null);

String emailsubject="Excellence academy mot de passe !";//titre
String emailbody="Le mot de passe de votre compte est : :"+mdpae;//contenu
Message message = new MimeMessage(newSession);
message.addRecipient(Message.RecipientType.TO, new InternetAddress("zbiramenikhan@gmail.com"));//recepteur

message.setSubject(emailsubject);




MimeBodyPart mimeBodyPart = new MimeBodyPart();
mimeBodyPart.setContent(emailbody, "text/html");
Multipart multipart = new MimeMultipart();
multipart.addBodyPart(mimeBodyPart);


message.setContent(multipart);

String fromuser ="excellenceacademy878@gmail.com"; //emetteir
String pass ="freefire1234";
String emailhost="smtp.gmail.com";
Transport transport =newSession.getTransport("smtp");
transport.connect(emailhost,fromuser,pass);
transport.sendMessage( message, message.getAllRecipients());
transport.close();
        
    }
    
    public void loadSecondFxml() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("Gestion_admin.fxml");
     
} 
    
        public void loadens() throws IOException{
            Pi l = new Pi();
            val1=logintf.getText() ; 
            val2=mdptf.getText() ; 
            l.changeScene("guilogin.fxml");           
            System.out.println("auth done");
     
} 
      public void loadThirdFxml() throws IOException{
     
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
       Parent root = loader.load();
            
            
             
            //Get controller of scene2
            ProfileController scene2Controller = loader.getController();
            FXMLDocumentController1 o = loader.getController();
             o.transferMessage(logintf.getText());
            //Pass whatever data you want. You can have multiple method calls here
            scene2Controller.transferMessage(logintf.getText());
           
            //Show scene 2 in new window            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Excellence Academy");
            stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
            stage.show();
} 
            public void loadfourth() throws IOException{
     
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
       Parent root = loader.load();
            
            
             
            //Get controller of scene2
           // ProfileController scene2Controller = loader.getController();
            ProfileController o = loader.getController();
             o.transferMessage(logintf.getText());
            //Pass whatever data you want. You can have multiple method calls here
           // scene2Controller.transferMessage(logintf.getText());
           
            //Show scene 2 in new window            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Excellence Academy");
            stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
            stage.show();
} 
}

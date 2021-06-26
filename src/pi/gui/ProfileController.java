/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import javafx.scene.image.Image ;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import pi.services.UtilisateurCRUD;
import pi.entities.Utilisateur;
/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class ProfileController implements Initializable {

    @FXML
    private Label lol;
    @FXML
    private VBox lbox;
    @FXML
    private VBox tfbox;
    @FXML
    private TextField idt;
    @FXML
    private TextField logt;
    @FXML
    private TextField nomt;
    @FXML
    private TextField pret;
    @FXML
    private DatePicker datt;
    @FXML
    private TextField mait;
    @FXML
    private PasswordField mdpt;
    @FXML
    private Label idlab;
    @FXML
    private Button btnmod;
    @FXML
    private Button dobtn;
    @FXML
    private Label imge;
    @FXML
    private Button impo;

    private ObservableList<Utilisateur> admin=FXCollections.observableArrayList();
    @FXML
    private Label label;
    @FXML
    private TextField path;
   
    private Image image; 
    private FileInputStream fis;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurCRUD l = new UtilisateurCRUD();
        Gestion_adminController k = new Gestion_adminController();        
 
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         
              java.sql.Date dn = java.sql.Date.valueOf(datt.getValue());        
              String ida = idt.getText();
              String nn = nomt.getText();
              String pp = pret.getText();
              String log = logt.getText();  
              String r = "Enseignant";
              String mail = mait.getText();
              String mdp = mdpt.getText();
             
               if(logt.getText().trim().isEmpty() || nomt.getText().trim().isEmpty() || pret.getText().trim().isEmpty() || mait.getText().trim().isEmpty() || mdpt.getText().trim().isEmpty())
              {
                          Alert alert = new Alert(AlertType.INFORMATION);
                          alert.setTitle("Information Dialog");
                          alert.setHeaderText("Oops ! Un erreur c'est produit !");
                          alert.setContentText("Champs vide !");
                          alert.showAndWait();      
              }
              else if (nn.contains("[0-9]") || pp.matches("[0-9]"))
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, Chaine de characteres seulement !");
                  alert.showAndWait();
              }
               else if (mdp.length() < 8)
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, au moins 8 characteres !");
                  alert.showAndWait();
              }   
               else if (mait.getText().matches("[a-z]"))
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, mail non valide !");
                  alert.showAndWait();
              }            
              else {
              UtilisateurCRUD ad = new UtilisateurCRUD();
              Utilisateur a = new Utilisateur (ida,log,nn,pp,mail,dn,r,mdp);
              ad.update(a);         
               }
    }

    @FXML
    private void decon(ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }
       String transferMessage(String message) {
       UtilisateurCRUD l = new UtilisateurCRUD();
       Utilisateur n = new Utilisateur();
       idlab.setText("Utilisateur :"+message);
       logt.setText(message);
       String h = logt.getText();
 
       l.fill(h);
        nomt.setText(l.fill(h).getNom1());
        pret.setText(l.fill(h).getPrenom1());
        mait.setText(l.fill(h).getMail1());
        idt.setText(l.fill(h).getId1());
        mdpt.setText(l.fill(h).getMdp());
        datt.setValue(l.fill(h).getDdn().toLocalDate());    
       System.out.println(l.fill(h));
       return h;
 
    }

    @FXML
    private void upload(ActionEvent event) {

       
           
         }
     @FXML
    private void Stage (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument1.fxml");
    }
     @FXML
 public void evenement() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("FXMLDocument.fxml");
 }
  
    @FXML
    private void Emplois (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }

@FXML
    private void Resultat (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
@FXML
    private void Matrieres (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("Matiere.fxml");
    }
@FXML
    private void Cours (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
@FXML
    private void Assiduité (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
@FXML
    private void Deconnecter (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }

   
    
    

     
    

    
}

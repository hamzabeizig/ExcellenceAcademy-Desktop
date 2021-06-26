/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import pi.entities.Enseignant;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import pi.services.EnseignantCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class guiGestionPassController implements Initializable {

 @FXML
    private TextField kkk;

    @FXML
    private Label ensname;

    @FXML
    private Button profilbutton;

    @FXML
    private PasswordField p1f;

    @FXML
    private PasswordField p2f;

    @FXML
    private PasswordField p3f;
     Enseignant ConnectedUser = GuiloginController.e ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               ensname.setText(ConnectedUser.getNom()+" "+ConnectedUser.getPrenom());
    }    

       @FXML
    void handleeditpass(ActionEvent event) {
        String oldpass = EnseignantCRUD.getInstance().verifypass(ConnectedUser,p2f.getText()); 
        if ( p2f.getText().length()>=8 && p3f.getText().length()>=8 && p3f.getText().equals(p2f.getText()) && oldpass.equals(p1f.getText()))
        { EnseignantCRUD.getInstance().editpass(ConnectedUser,p2f.getText()); 
        JOptionPane.showMessageDialog(null,"Votre mot passe a été modifié");}
        else 
        {   JOptionPane.showMessageDialog(null,"Verifier vos données");
             p1f.setText("");
             p2f.setText("");
             p3f.setText("");}
    }
        
        
        
        
        
        
        
                @FXML
    void handlegoprofil(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.goprofil(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
         @FXML
    void handlegonote(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.gonote(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionAssiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   @FXML
    void handlegoassi(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.goAssi(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    @FXML
    void handlegofirst(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.gofirst(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionAssiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    void handlegohistoriqueAssi(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.goHistoriqueAssi(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionAssiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         
   @FXML
    void handlegocours(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.goCours(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    void handlegoemp(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.goemploi(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionAssiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}

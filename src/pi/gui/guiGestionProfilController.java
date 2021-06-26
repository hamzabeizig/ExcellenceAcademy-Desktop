/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import pi.entities.Enseignant;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import pi.services.EnseignantCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class guiGestionProfilController implements Initializable {

 @FXML
    private Label label;

    @FXML
    private TextField kkk;

    @FXML
    private Label ensname;

    @FXML
    private TextField nomf;

    @FXML
    private TextField prenomf;

    @FXML
    private TextField loginf;

    @FXML
    private TextField matieref;

    @FXML
    private TextField emailf;

    @FXML
    private TextField depf;

    @FXML
    private DatePicker datef;

    @FXML
    private PasswordField passf;
     Enseignant ConnectedUser = GuiloginController.e ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               ensname.setText(ConnectedUser.getNom()+" "+ConnectedUser.getPrenom());
               setValues();
    }    
    
      @FXML
    void handleeditprofil(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.EditProfil(ConnectedUser,nomf,prenomf,loginf,datef); 
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
    private void modi(ActionEvent event) {
    }
    
     private void setValues() {
         nomf.setText(ConnectedUser.getNom());
         prenomf.setText(ConnectedUser.getPrenom());
         loginf.setText(ConnectedUser.getLogin());
         datef.setValue(ConnectedUser.getDdn().toLocalDate());
         emailf.setText(ConnectedUser.getMail());
         depf.setText(ConnectedUser.getNom_departement());


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
    void handlegopass(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.gopass(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionNoteController.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import pi.entities.Classe;
import pi.entities.Enseignant;
import pi.entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pi.gui.AuthentificationController ; 
import pi.services.EnseignantCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GuiloginController implements Initializable {

    @FXML
    private TextField usernamef ;
    @FXML
    private PasswordField passwordf;
    @FXML
    private Button connectbtn;
    
    public static Enseignant e  ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernamef.setText(AuthentificationController.val1);
        passwordf.setText(AuthentificationController.val2);

    }    
    
     @FXML
    void handleconnect(ActionEvent event) {
 try {
            
 
            Enseignant e1 = new Enseignant() ; 
            e1.setLogin(usernamef.getText());
            e1.setPassword(passwordf.getText());
            
            EnseignantCRUD prc1 = new EnseignantCRUD();  
            e1 = prc1.getSessionData(e1,event) ;
            e1 = prc1.getSessionData2(e1,event) ;
            e= e1 ;
            Pi l = new Pi();
            prc1.Connect(usernamef,passwordf,event); 

 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(GuiloginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    }
    
   
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import pi.entities.Enseignant;
import pi.entities.Wrapper2;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pi.services.EnseignantCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class guiHistoriqueAssiController implements Initializable {

      @FXML
    private TextField notif;
    @FXML
    private Label label;
    @FXML
    private TableView<Wrapper2> notetable;
    @FXML
    private TableColumn<Wrapper2, Integer> idf;
    @FXML
    private TableColumn<Wrapper2, String> nomf;
    @FXML
    private TableColumn<Wrapper2, String> prenomf;
    @FXML
    private TableColumn<Wrapper2, String> Assif;
    @FXML
    private TextField kkk;
      @FXML
    private ComboBox<String> cb;
    @FXML
    private DatePicker datef;
    @FXML
    private Label ensname;
     Enseignant ConnectedUser = GuiloginController.e ; 
    /**
     * Initializes the controller class.
     */
     
             List<String> listclass ;   ;
        ObservableList<Wrapper2> listt = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        ensname.setText(ConnectedUser.getNom()+" "+ConnectedUser.getPrenom());
               LoadClasses();
      // UpdateTableee() ; 
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
            Logger.getLogger(guiGestionNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @FXML
         public void UpdateTableee() {
              idf.setCellValueFactory(new PropertyValueFactory<Wrapper2 , Integer>("id_user"));
              nomf.setCellValueFactory(new PropertyValueFactory<Wrapper2 , String>("nom"));
              prenomf.setCellValueFactory(new PropertyValueFactory<Wrapper2 , String>("prenom"));           
              Assif.setCellValueFactory(new PropertyValueFactory<Wrapper2 , String>("assi"));
              System.out.println(cb.getValue());
              listt = EnseignantCRUD.getInstance().displayAlll3(cb.getValue(),ConnectedUser,datef); 
              System.out.println(listt.toString());
              notetable.setItems(listt);

            
              
    }
      public void LoadClasses(){

        listclass = EnseignantCRUD.getInstance().displayClass(Integer.parseInt(ConnectedUser.getId()));
        cb.getItems().addAll(listclass);
        System.out.println(listclass.toString());
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

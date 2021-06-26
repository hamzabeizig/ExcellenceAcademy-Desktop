/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import pi.entities.Enseignant;
import pi.entities.Wrapper3;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class guiGestionEmploiController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField kkk;
    @FXML
    private Label ensname;
    @FXML
    private Button profilbutton;
    @FXML
    private TableView<Wrapper3> tabcour;
    @FXML
    private TableColumn<Wrapper3, String> coursname;
    @FXML
    private Button visb;
        ObservableList<Wrapper3> listt = FXCollections.observableArrayList();
    Enseignant ConnectedUser = GuiloginController.e ; 
        int index = -1 ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             ensname.setText(ConnectedUser.getNom()+" "+ConnectedUser.getPrenom());
        try { 
            UpdateTableEmp() ;
        } catch (SQLException ex) {
            Logger.getLogger(guiGestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

          @FXML
    void getSelected (MouseEvent event){
    index = tabcour.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    kkk.setText(coursname.getCellData(index).toString());
    visb.setDisable(false);
    
    }
    
        @FXML
         public void UpdateTableEmp() throws SQLException {
              coursname.setCellValueFactory(new PropertyValueFactory<Wrapper3 , String>("nom_cours"));
              listt = EnseignantCRUD.getInstance().displayAlll5(ConnectedUser); 
              tabcour.setItems(listt);
            //  System.out.println(listt.toString());
            
              
    } 


         
        public void handledownfile(final ActionEvent e) throws SQLException, IOException {
 
        try {
          EnseignantCRUD.getInstance().downloadfileemp(ConnectedUser);
        } catch (Exception ex) {
            Logger.getLogger(
                guiGestionCoursController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
        
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
            Logger.getLogger(guiGestionNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

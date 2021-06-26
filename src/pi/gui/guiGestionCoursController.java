/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import pi.entities.Cours;
import pi.entities.Enseignant;
import pi.entities.Wrapper2;
import pi.entities.Wrapper3;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pi.services.EnseignantCRUD;
 
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class guiGestionCoursController implements Initializable {
     Enseignant ConnectedUser = GuiloginController.e ; 
    @FXML
    private ProgressIndicator progress;
    @FXML
    private TextField notif;
    @FXML
    private Label label;
    @FXML
    private TableView<Wrapper3> tabcour;
    @FXML
    private TableColumn<Wrapper3, String> coursname;
    @FXML
    private TextField kkk;
    @FXML
    private ComboBox<String> cb;
    @FXML
    private Label ensname;
    @FXML
    private Button profilbutton;
    @FXML
    private Button visb;

    @FXML
    private Button addcours;

    @FXML
    private Button suppb;
    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    visb.setDisable(true);
     suppb.setDisable(true);
     LoadClasses();
      ensname.setText(ConnectedUser.getNom()+" "+ConnectedUser.getPrenom());
            try {
                UpdateTableCours();
            } catch (SQLException ex) {
                Logger.getLogger(guiGestionCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }   
    List<String> listclass ;  
 public void LoadClasses(){
          
        listclass = EnseignantCRUD.getInstance().displayClass(Integer.parseInt(ConnectedUser.getId()));
        cb.getItems().addAll(listclass);
        System.out.println(listclass.toString());
    }



 

    @FXML
    private void modi(ActionEvent event) {
    }

    

  
         private Desktop desktop = Desktop.getDesktop();
        final FileChooser fileChooser = new FileChooser();
        ObservableList<Wrapper3> listt = FXCollections.observableArrayList();
        int index = -1 ; 

      @FXML
    void getSelected (MouseEvent event){
    index = tabcour.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    kkk.setText(coursname.getCellData(index).toString());
    visb.setDisable(false);
    suppb.setDisable(false);
    
    }
        
        @FXML
         public void UpdateTableCours() throws SQLException {
              coursname.setCellValueFactory(new PropertyValueFactory<Wrapper3 , String>("nom_cours"));
              listt = EnseignantCRUD.getInstance().displayAlll4(cb.getValue(),ConnectedUser); 
              tabcour.setItems(listt);

            
              
    }   
         @FXML
         public void handledelfile() throws SQLException, IOException {
              EnseignantCRUD.getInstance().deletecours(cb.getValue(),ConnectedUser,kkk.getText()); 
               UpdateTableCours();
            
              
    }   
        
       public void handleopenfile(final ActionEvent e) throws SQLException {
           List<String> listmails =null; 
           String c = cb.getValue() ; 
                           Stage window = (Stage)((Node)e.getSource()).getScene().getWindow() ; 

                    File file = fileChooser.showOpenDialog(window);
                    if (file != null) {
                        uploadfile(file,c);
                        UpdateTableCours();
                         try {  
            listmails =  EnseignantCRUD.getInstance().getMails(cb.getValue()) ;
            System.out.println(listmails.toString());
        } catch (SQLException ex) {
            Logger.getLogger(guiGestionCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
                         try {
                         for (int i = 0 ; i<listmails.size() ; i++)   
               { EnseignantCRUD.getInstance().sendMailrefuser(listmails.get(i),ConnectedUser,file.getName()); }
            } catch (Exception ex) {
                Logger.getLogger(guiGestionCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
                }
       
         private void uploadfile(File file,String c) {
             
        try {
            if (cb.getValue()!=null)
            EnseignantCRUD.getInstance().uploadfile(file,ConnectedUser,c);

        } catch (Exception ex) {
            Logger.getLogger(
                guiGestionCoursController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
           

         
    }
         
         
         
         
       
        public void handledownfile(final ActionEvent e) throws SQLException, IOException {
 
        try {
          EnseignantCRUD.getInstance().downloadfile(cb.getValue(),ConnectedUser,kkk.getText());
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

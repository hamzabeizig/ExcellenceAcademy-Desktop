/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import pi.entities.Assiduite;
import pi.entities.Enseignant;
import pi.entities.Note;
import pi.entities.Wrapper1;
import pi.entities.Wrapper2;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import pi.services.EnseignantCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class guiGestionAssiController implements Initializable {
  @FXML
    private Button makeassi;
 @FXML
    private Button validerb;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notif.setDisable(true);
        validerb.setDisable(true);
        datef.setValue(LocalDate.now());
        ensname.setText(ConnectedUser.getNom()+" "+ConnectedUser.getPrenom());
        LoadClasses();
        UpdateTableee();
    }    

    @FXML
    void getSelectedd (MouseEvent event){
    index = notetable.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
     notif.setDisable(false);
    validerb.setDisable(false);
    notif.setText(Assif.getCellData(index));
    kkk.setText(idf.getCellData(index).toString());

    
    }
   public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
    @FXML
     private void modi(ActionEvent event) {
        
         String n = notif.getText();
         String h = kkk.getText();
         
         java.sql.Date b =java.sql.Date.valueOf(datef.getValue()) ; 
         if (n.equals("P")||n.equals("A"))
         {   EnseignantCRUD ad = new EnseignantCRUD();
              Assiduite o = new Assiduite() ; 
              o.setValeur(n);
              o.setDate(b);
              ad.Edit2(o,h,ConnectedUser,datef);
             System.out.println(o.getValeur());
             UpdateTableee();}
         else {
      JOptionPane.showMessageDialog(null, "Il faut Entre soit A pour dire Absent ou P pour dire Présent");
     }
    }

    @FXML
    private void UpdateTablee(ActionEvent event) {
    }
        List<String> listclass ;   ;
        String cls  ;
        ObservableList<Wrapper2> list = FXCollections.observableArrayList();
        ObservableList<Wrapper2> listt = FXCollections.observableArrayList();
        ObservableList<Wrapper2> notes = FXCollections.observableArrayList();
        int index = -1 ; 

    public void LoadClasses(){

        listclass = EnseignantCRUD.getInstance().displayClass(Integer.parseInt(ConnectedUser.getId()));
        cb.getItems().addAll(listclass);
        System.out.println(listclass.toString());
    }
   
     @FXML
     public void handlemakeassi(){

         EnseignantCRUD.getInstance().makeassi(cb.getValue(),ConnectedUser,datef);
        UpdateTableee()    ;
     }
    
     @FXML
         public void UpdateTableee() {
              idf.setCellValueFactory(new PropertyValueFactory<Wrapper2 , Integer>("id_user"));
              nomf.setCellValueFactory(new PropertyValueFactory<Wrapper2 , String>("nom"));
              prenomf.setCellValueFactory(new PropertyValueFactory<Wrapper2 , String>("prenom"));           
              Assif.setCellValueFactory(new PropertyValueFactory<Wrapper2 , String>("assi"));
              System.out.println(cb.getValue());
              listt = EnseignantCRUD.getInstance().displayAlll2(cb.getValue(),ConnectedUser,datef); 
              
              notetable.setItems(listt);

            
              
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

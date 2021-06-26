/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;


import pi.entities.Enseignant;
import pi.entities.Wrapper1;
import pi.entities.Etudiant;
import pi.entities.Note;
import pi.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;

import pi.services.EnseignantCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */


public class guiGestionNoteController implements Initializable {

   
      @FXML
    private Button validerb;
    @FXML
    private Label ensname;

  
    @FXML
    private TableView<Wrapper1> notetable;

    @FXML
    private TableColumn<Wrapper1, Integer> idf;

    @FXML
    private TableColumn<Wrapper1, String> nomf;

    @FXML
    private TableColumn<Wrapper1, String> prenomf;

    @FXML
    private TableColumn<Wrapper1, Float> notef;
    
   Enseignant ConnectedUser = GuiloginController.e ; 
   
         List<String> listclass ;   ;
     String cls  ;
        ObservableList<Wrapper1> list = FXCollections.observableArrayList();
        ObservableList<Wrapper1> listt = FXCollections.observableArrayList();
        ObservableList<Wrapper1> notes = FXCollections.observableArrayList();
        int index = -1 ; 
    
    @FXML
    private Label label;
    @FXML
    private TextField notif;
    @FXML
    private TextField kkk;
    @FXML
    private ComboBox<String> cb;
    
   public static boolean isNumeric(String str) { 
  try {  
    Double.parseDouble(str);  
    return true;
  } catch(NumberFormatException e){  
      
    return false;  
  }  
}

//        public class listData {
//    
//    private ObservableList<Utilisateur> Utilisateur=FXCollections.observableArrayList();
//    private ObservableList<Utilisateur> note=FXCollections.observableArrayList();
//
//    public listData() {
//        
//        
//        EnseignantCRUD pdao=EnseignantCRUD.getInstance();
//        Utilisateur= pdao.displayAll();
//        System.out.println(Utilisateur);
//    }
//    
//    public ObservableList<Utilisateur> getadmin(){
//        return Utilisateur;
//    }
//
//    
//}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notif.setDisable(true);
        validerb.setDisable(true);
        ensname.setText(ConnectedUser.getNom()+" "+ConnectedUser.getPrenom());
        LoadClasses();
        UpdateTablee();
    }    
    
//     public void UpdateTable(){
//                idf.setCellValueFactory(new PropertyValueFactory<Utilisateur , Integer>("id"));
//                        nomf.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("nom"));
//              prenomf.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("prenom"));
//
//              list = EnseignantCRUD.getInstance().displayAll(); 
//              notetable.setItems(list);
//
//    }
    @FXML
         public void UpdateTablee() {
              idf.setCellValueFactory(new PropertyValueFactory<Wrapper1 , Integer>("id_user"));
              nomf.setCellValueFactory(new PropertyValueFactory<Wrapper1 , String>("nom"));
              prenomf.setCellValueFactory(new PropertyValueFactory<Wrapper1 , String>("prenom"));              
              notef.setCellValueFactory(new PropertyValueFactory<Wrapper1 , Float>("note"));
              System.out.println(cb.getValue());
              listt = EnseignantCRUD.getInstance().displayAlll(cb.getValue(),ConnectedUser); 
              
              notetable.setItems(listt);

            
              
    }
    @FXML
    void getSelected (MouseEvent event){
    index = notetable.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    notif.setDisable(false);
    validerb.setDisable(false);
    notif.setText(notef.getCellData(index).toString());
    kkk.setText(idf.getCellData(index).toString());

    
    }
    
   private void modifier(ActionEvent event) throws IOException {
                   
        
             
              //a.setNote(Float.parseFloat(n));
              
              //loadDataFromDatabase();
             
             
    }

    @FXML
    private void modi(ActionEvent event) {
        
         String n = notif.getText();
         String h = kkk.getText();
                  if ((Float.parseFloat(n)>=0)&&(Float.parseFloat(n)<=20)&&(isNumeric(n)))
                  { 
                      EnseignantCRUD ad = new EnseignantCRUD();
              //Wrapper1 a = new Wrapper1 ();
              Note o = new Note() ; 
            o.setNote(Float.parseFloat(n));
             ad.Edit(o,h,ConnectedUser);
             System.out.println(o.getNote());
             UpdateTablee();}
                  else {
                        JOptionPane.showMessageDialog(null, "Le champs note doit contenir une valeur numerique entre 0 et 20 ");

                  }
    }
    
    public void LoadClasses(){

        listclass = EnseignantCRUD.getInstance().displayClass(Integer.parseInt(ConnectedUser.getId()));
        cb.getItems().addAll(listclass);
        System.out.println(listclass.toString());
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

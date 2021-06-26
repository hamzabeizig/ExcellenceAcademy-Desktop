/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;



import pi.entities.Matiere;
import pi.services.DepartementCRUD;
import pi.services.MatiereCRUD;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class MatiereController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private Button btn_eve;
    @FXML
    private Button btn_ut;
    @FXML
    private Button btn_emp;
    @FXML
    private Button btn_res;
    @FXML
    private Button btn_st;
    @FXML
    private Button btn_mat;
    @FXML
    private Button btn_dep;
    @FXML
    private Button btn_dec;
    @FXML
    private TextField txt_nomM;
    @FXML
    private TextField txt_coef;
    @FXML
    private TextField txt_vol;
    @FXML
    private Button btnajMat;
    @FXML
    private TableColumn<Matiere, Integer> cl_id;
    @FXML
    private TableColumn<Matiere, String> cl_nom;
    @FXML
    private TableColumn<Matiere, Float> cl_coeff;
    @FXML
    private TableColumn<Matiere, String> cl_V;
    @FXML
    private TableView<Matiere> tv;
      MatiereCRUD m = new MatiereCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherMat();
       
    }  
    @FXML
    private void AjouterMat(ActionEvent event) {
        String zero = "";

  String rnom = txt_nomM.getText();
  String rcoef= txt_coef.getText();
  String rvol =txt_vol.getText();
  
       
        Matiere m = new Matiere(1,rnom,rcoef,rvol);
          MatiereCRUD mc =new MatiereCRUD();
        mc.ajoutMatiere(m);
        afficherMat();
        txt_nomM.setText(zero);
        txt_coef.setText(zero);
        txt_vol.setText(zero);
        

       
         Alert a = new  Alert(Alert.AlertType.INFORMATION);
        a.setTitle(" Excellence Academy ");
        a.setHeaderText("Ajout Matiere ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" Matiere "+txt_nomM.getText()+" ajouté avec succés ");
        a.showAndWait();
       
            }
    @FXML
    private void suppressionMat(ActionEvent event) {
         Matiere m = tv.getSelectionModel().getSelectedItem();
        int rid = m.getId();
        Matiere me =new Matiere(rid,"","","");
        
        MatiereCRUD mc =new MatiereCRUD();
        mc.supprimerMatiere(m);
        afficherMat();
      txt_nomM.setText("");
         Alert a = new  Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Excellence Academy");
        a.setHeaderText("Suppression Matiere ");
        Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" Matiere "+txt_nomM.getText()+" supprimé avec succés ");
        a.showAndWait();
        
    }
     public void evenement2() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("adminEvenement.fxml");
     
} 
      public void afficherMat(){
       
        try {
            
            ObservableList<Matiere> ls1=m.afficherMatiere();
            
            cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cl_coeff.setCellValueFactory(new PropertyValueFactory<>("coeff"));
             cl_V.setCellValueFactory(new PropertyValueFactory<>("volume_h"));
            
            
            tv.setItems(ls1);
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      @FXML
      public void Departement1() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("Departement.fxml");
     
} 
      @FXML
       public void Utilisateurs1() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("Gestion_admin.fxml");
     
} 
        @FXML
    private void deconnecter3 (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }
     @FXML
    private void Stage (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
}

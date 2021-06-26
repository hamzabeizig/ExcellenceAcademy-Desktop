/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

/**
 *
 * @author pc
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import pi.entities.Departement;
import pi.entities.Reunion;
import pi.entities.Stages;
import pi.entities.Utilisateur;
import pi.services.DepartementCRUD;
public class DepartementController implements Initializable{
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Button btn_ut;
    @FXML
    private Button btn_emp;
    @FXML
    private Button btn_res;
    @FXML
    private Button btn_eve;
    @FXML
    private Button btn_st;
    @FXML
    private Button btn_dec;
    @FXML
    private Button btn_dep;
    @FXML
    private Tab tab1;
    @FXML
    private TextField txt_nomd;
    @FXML
    private TableView<Departement> tv1;
    @FXML
    private TableColumn<Departement, Integer> cl_id;
    @FXML
    private TableColumn<Departement, String> cl_nom;
    @FXML
    private Button btnA;
    @FXML
    private Button btnS;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
     DepartementCRUD d = new DepartementCRUD();
     String zero="";
    @FXML
    private ComboBox<String> cmbx_dep;
    @FXML
    private ComboBox<String> cmbx_ens;
    @FXML
    private TableColumn<Utilisateur, Integer> cl_id1;
    @FXML
    private TableColumn<Utilisateur, String> cl_nom1;
    @FXML
    private TableColumn<Utilisateur, String> cl_pre1;
    @FXML
    private Button btnaff;
    @FXML
    private TableView<Utilisateur> tv2;
    @FXML
    private RadioButton rd1;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private RadioButton rd2;
    @FXML
    private ComboBox<String> cmbx_ens1;
    @FXML
    private ToggleGroup Mygroup;
    @FXML
    private ComboBox<String> cmbx_dep1;
      private Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs ; 
    @FXML
    private TableColumn<Departement, String> cl_chef;
    @FXML
    private Button btnsupp;
    @FXML
    private ComboBox<String> cmbx_dep2;
    @FXML
    private ComboBox<String> cmbx_mat;
    @FXML
    private DatePicker pick_dat;
    @FXML
    private TextArea txt_obj;
    @FXML
    private Button btn_prog;
    @FXML
    private TableView<Reunion> tv3;
    @FXML
    private Button btn_supp2;
    @FXML
    private TableColumn<Reunion, Integer> cl_id2;
    @FXML
    private TableColumn<Reunion, String > cl_date;
    @FXML
    private TableColumn<Reunion, String> cl_mat;
    @FXML
    private TableColumn<Reunion, String> cl_dep3;
    @FXML
    private Label lbl11;
    private ComboBox<String> cmbx_dep3;
    @FXML
    private ComboBox<String> cmbx_ens2;
    @FXML
    private Button btn_aff;
    @FXML
    private ComboBox<String> cmbx_reu;
    @FXML
    private TextField txt_hor;
    @FXML
    private TableColumn<Reunion, String> cl_hor;
    @FXML
    private Button btn_mat;
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherdep();

        afficherdep1();
        afficherens();
        affichermati();
        afficherreu();
        afficherobjre();
        
//     lbl1.setVisible(false);
        lbl2.setVisible(false);
//        cmbx_ens.setVisible(false);
        cmbx_ens1.setVisible(false);
    }    

    @FXML
    private void AjouterDep(ActionEvent event) {

  String rnom = txt_nomd.getText();
       
        Departement d = new Departement(1,rnom,"");
        DepartementCRUD dec =new DepartementCRUD();
        dec.ajoutDepartement(d);
        afficherdep();
//        txt_nomd.setText(zero);
        updatedep1();
         Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle(" Excellence Academy ");
        a.setHeaderText("Ajout Departement ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" Departement "+txt_nomd.getText()+" ajouté avec succés ");
        a.showAndWait();
       
            }

    @FXML
    private void suppressiondep(ActionEvent event) {
         Departement de = tv1.getSelectionModel().getSelectedItem();
        String rnom = de.getNom();
        Departement d =new Departement(1,rnom,"");
        
        DepartementCRUD dec =new DepartementCRUD();
        dec.supprimerDepartement(d);
        afficherdep();
        txt_nomd.setText(zero);
        updatedep1();
         Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle("Excellence Academy");
        a.setHeaderText("Suppression Departement ");
        Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" Departement "+rnom+" supprimé avec succés ");
        a.showAndWait();
        
    }
    
     public void afficherdep(){
       
        try {
            
            ObservableList<Departement> ls1=d.afficherdepartement();
            
            cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cl_chef.setCellValueFactory(new PropertyValueFactory<>("nom_chef"));
            
            tv1.setItems(ls1);
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void evenement1() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("adminEvenement.fxml");
     
} 
    
 public void affichermati(){
        try {
            List<String> nommat = d.affichermat();
            cmbx_mat.getItems().addAll(nommat);
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
     public void afficherdep1()
    {
    try {
            List<String> idens = d.affichernomdep();
            cmbx_dep.getItems().addAll(idens);
            cmbx_dep1.getItems().addAll(idens);
            cmbx_dep2.getItems().addAll(idens);
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void updatedep1(){
        try {
            cmbx_dep.getItems().clear();
            cmbx_dep1.getItems().clear();
            cmbx_dep2.getItems().clear();
            List<String> idens1= d.affichernomdep();
            cmbx_dep.getItems().addAll(idens1);
            cmbx_dep1.getItems().addAll(idens1);
            cmbx_dep2.getItems().addAll(idens1);
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
      public void afficherens()
    {
    try {
            List<String> ens = d.afficherusrnmens();
            cmbx_ens.getItems().addAll(ens);
            cmbx_ens1.getItems().addAll(ens);
            cmbx_ens2.getItems().addAll(ens);
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
       public void afichcombo(ActionEvent event) throws SQLException{

aficherens();
        
    }
       public void aficherens(){
        try {
            ObservableList<Utilisateur> ensinf1=d.enseignants(cmbx_dep1.getValue());
            System.out.println(ensinf1);
            cl_id1.setCellValueFactory(new PropertyValueFactory<>("id_user"));


cl_nom1.setCellValueFactory(new  PropertyValueFactory<>("nom"));
cl_pre1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
tv2.setItems(ensinf1);
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    @FXML
        public void radioSelect (ActionEvent event){
           
        String o = "";
       if (rd1.isSelected()){
           lbl2.setVisible(true);
           cmbx_ens1.setVisible(true);
           lbl1.setVisible(false);
           cmbx_ens.setVisible(false);
           
       }
       else if ( rd2.isSelected()){
           lbl1.setVisible(true);
           cmbx_ens.setVisible(true);
           lbl2.setVisible(false);
           cmbx_ens1.setVisible(false);
       }
        
    
        }
 @ FXML 
   private void affecter (ActionEvent event ){
       if (rd1.isSelected())
          
       {
            d.affecterchef(cmbx_ens1.getValue(),cmbx_dep.getValue());
             Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle(" Excellence Academy ");
        a.setHeaderText("Affectation chef ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" "+cmbx_ens1.getValue()+" affecté comme chef pour le departement "+cmbx_dep.getValue()+"");
        a.showAndWait();
            afficherdep();
            
       }
       else if (rd2.isSelected())
               {
                   d.affecterens(cmbx_ens.getValue(),cmbx_dep.getValue());
                    Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle(" Excellence Academy ");
        a.setHeaderText("Affectation Enseignant ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" "+cmbx_ens.getValue()+" affecté dans le departement "+cmbx_dep.getValue()+"");
        a.showAndWait();
                 
           
               }
        }
   @FXML 
   private void supprimer ( ActionEvent event){
       if(rd1.isSelected())
       {
           d.supprimerchef(cmbx_dep.getValue());
            Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle(" Excellence Academy ");
        a.setHeaderText("Suppression Chef  ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" "+cmbx_ens1.getValue()+" n'est pas encore le chef du departement "+cmbx_dep.getValue()+"");
        a.showAndWait();
           afficherdep();
       }
       else if ( rd2.isSelected())
       {
           d.supprimerens(cmbx_ens.getValue());
            Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle(" Excellence Academy ");
        a.setHeaderText("Suppression Enseignant ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" "+cmbx_ens.getValue()+" n'appartient plus au departement  "+cmbx_dep.getValue()+"");
        a.showAndWait();
           
       }
   }
   @FXML
   private void programmer (ActionEvent event ){
       String zero = "";
       String rdep =cmbx_dep2.getValue();
       String rmat=cmbx_mat.getValue();
       String robj =txt_obj.getText();
       LocalDate  rdate = pick_dat.getValue();
       String rhor = txt_hor.getText();
       
        Reunion r = new Reunion(1,rdate,rdep,rmat,robj,rhor);
        DepartementCRUD dec =new DepartementCRUD();
        dec.ProgrammerReu(r);
        afficherreu();
        txt_obj.setText(zero);
         Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle(" Excellence Academy ");
        a.setHeaderText("Programation Reunion  ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" Reunion programmé avec succée");
        a.showAndWait();
   }
   @FXML
   private void supprimer1 ( ActionEvent event){
        Reunion re = tv3.getSelectionModel().getSelectedItem();
        int rid = re.getId();
         LocalDate date =re.getDate();
        
        Reunion r =new Reunion(rid,date,"","","","");
        
        DepartementCRUD dec =new DepartementCRUD();
        dec.SupprimerReu(r);
        afficherreu();
        Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle("Excellence Academy ");
        a.setHeaderText("Suppression Reunion  ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText(" Reunion supprimée avec succée");
        a.showAndWait();
       
   }
   
  public void afficherreu(){
        try {
            ObservableList<Reunion> ls=d.afficherREu();
            
            cl_id2.setCellValueFactory(new PropertyValueFactory<>("id"));
            cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            cl_dep3.setCellValueFactory(new PropertyValueFactory<>("departement"));
              cl_mat.setCellValueFactory(new PropertyValueFactory<>("matiere"));
              cl_hor.setCellValueFactory(new PropertyValueFactory<>("horaire"));
            
            tv3.setItems(ls);
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
   public void afficherobjre(){
        try {
            List<String> idens = d.afficherobjreu();
           
            cmbx_reu.getItems().addAll(idens);
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   @FXML
   private void mailreunion() throws SQLException{
       
       d.mailreunion(d.horairereu(cmbx_reu.getValue()),cmbx_reu.getValue(), cmbx_ens2.getValue(), d.datereu(cmbx_reu.getValue()));
 Alert a = new  Alert(AlertType.INFORMATION);
        a.setTitle(" Excellence Academy ");
        a.setHeaderText("Envoie Email ");
         Stage stage =  (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        a.setContentText("Email envoyé avec succée");
        a.showAndWait();  }
   public void updatereu(){
        try {
            cmbx_reu.getItems().clear();
            List<String> ll = new ArrayList<>();
            ll=d.afficherobjreu();
            cmbx_reu.getItems().addAll(ll);
        } catch (SQLException ex) {
            Logger.getLogger(DepartementController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    @FXML
       public void Utilisateurs() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("Gestion_admin.fxml");
     
} 
        @FXML
       public void Matieres() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("Matiere.fxml");
     
} 
        @FXML
    private void deconnecter2(ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }
     @FXML
    private void Stage (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
 
}
    

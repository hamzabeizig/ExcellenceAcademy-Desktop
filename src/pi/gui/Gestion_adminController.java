
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pi.entities.Utilisateur;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pi.entities.Admin;
import pi.services.UtilisateurCRUD;

/**
 *
 * @author WIKI
 */
public class Gestion_adminController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField idf;
    @FXML
    private TextField nomf;
    @FXML
    private TextField pref;
    @FXML
    private Button btnedit;
    @FXML
    private TableView<Utilisateur> tva;
    @FXML
    private TableColumn<Utilisateur, String> cid;
    @FXML
    private TableColumn<Utilisateur, String> cnom;
    @FXML
    private TableColumn<Utilisateur, String> cpre;
    @FXML
    private TableColumn<Utilisateur, String> unc;
    @FXML
    private TableColumn<Utilisateur, String> mailc;
    @FXML
    private TableColumn<Utilisateur, String> rolec;
    @FXML
    private TableColumn<Utilisateur, String> ddnc;
    @FXML
    private TableColumn<Utilisateur, String> mdpc;
    
    UtilisateurCRUD s = new UtilisateurCRUD();
    
    public ObservableList<Utilisateur> data =FXCollections.observableArrayList();
    @FXML
    private Button btndel;
    
    final ObservableList options = FXCollections.observableArrayList();
    
    private final listData listdata = new listData();
    @FXML
    private TextField ltf;
    @FXML
    private TextField emtf;
    @FXML
    private DatePicker ddntf;
    @FXML
    private ComboBox<String> cbr;
    
    
    
    private ObservableList<Utilisateur> admin=FXCollections.observableArrayList();
    @FXML
    private TextField searchtf;
    @FXML
    private Button chbtn;
    @FXML
    private Label lol;
    @FXML
    private ComboBox<String> cbt;
    @FXML
    private Button nnnt;
    @FXML
    private Label rqtf;
   
    private Button ajmo;
    @FXML
    private Button btnad;
    @FXML
    private TextField mdptf;
    @FXML
    private Button deconbtn;
    @FXML
    private Label dd;

    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        
        if (!searchtf.getText().trim().isEmpty())
        {
            int i  ;
            i = Integer.parseInt(searchtf.getText());
            admin = UtilisateurCRUD.getInstance().che(i);
            tva.setItems(admin);
            rqtf.setVisible(false);
        }
        
        else if (searchtf.getText().trim().isEmpty())
            {      
            loadDataFromDatabase();
            }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Oops ! Un erreur c'est produit !");
            alert.setContentText("Verifier Vos Paramètres!");
            alert.showAndWait();

        }
                   
    }
    @FXML 
    private void departement (ActionEvent event) throws IOException{
        Pi l = new Pi();
     l.changeScene("Departement.fxml");
    }
     public void evenement() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("adminEvenement.fxml");
     
} 

    @FXML
    private void tri(ActionEvent event) {
        
        String o = cbt.getValue();
        UtilisateurCRUD h = new UtilisateurCRUD();
        
        if(null == o)
        {
            admin = UtilisateurCRUD.getInstance().displayAll();
            tva.setItems(admin);    
        }
        else switch (o) {
            case "Nom":
                admin = UtilisateurCRUD.getInstance().displayAllN();      
                tva.setItems(admin);
                break;
            case "Prenom":
                admin = UtilisateurCRUD.getInstance().displayAllP();
                tva.setItems(admin);
                break;
            case "Role":
                admin = UtilisateurCRUD.getInstance().displayAllR();
                tva.setItems(admin);
                break;
            default:
                admin = UtilisateurCRUD.getInstance().displayAllD();
                tva.setItems(admin);
                break;
        }
        
       
    }

    @FXML
    private void nett(ActionEvent event) throws SQLException {
       UtilisateurCRUD e = new UtilisateurCRUD();
       e.clean();
        //loadDataFromDatabase();
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }


  
    
    public class listData {
    
    private ObservableList<Utilisateur> Utilisateur=FXCollections.observableArrayList();

    public listData() {
        
        
        UtilisateurCRUD pdao=UtilisateurCRUD.getInstance();
        Utilisateur= pdao.displayAll();

    }
    
    public ObservableList<Utilisateur> getadmin(){
        return admin;
    }

    
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        loadDataFromDatabase();
        cbt.getItems().addAll("Nom","Prenom","Role","Date de naissance");
        cbr.getItems().addAll("Enseignant","Administrateur","Etudiant");
        cbr.getSelectionModel().select("Enseignant");
        cbr.setPromptText("Enseignant");
        
        loadDataFromDatabase();

            tva.setOnMouseClicked((MouseEvent event) -> {
            idf.setText(String.valueOf(listdata.getadmin().get(tva.getSelectionModel().getSelectedIndex()).getId()));
            nomf.setText(listdata.getadmin().get(tva.getSelectionModel().getSelectedIndex()).getNom());
            pref.setText(listdata.getadmin().get(tva.getSelectionModel().getSelectedIndex()).getPrenom());
            ltf.setText(listdata.getadmin().get(tva.getSelectionModel().getSelectedIndex()).getLogin());
            emtf.setText(listdata.getadmin().get(tva.getSelectionModel().getSelectedIndex()).getMail());
            mdptf.setText(listdata.getadmin().get(tva.getSelectionModel().getSelectedIndex()).getMdp());
             
        });
    } 
    

    @FXML
    private void dsa(ActionEvent event5) throws SQLException, IOException {
          
              
              java.sql.Date dn = java.sql.Date.valueOf(ddntf.getValue());        
              String ida = idf.getText();
              String nn = nomf.getText();
              String pp = pref.getText();
              String log = ltf.getText();  
              String r = cbr.getValue();
              String mail = emtf.getText();
              String mdp = mdptf.getText();
           
             
         
              if(ltf.getText().trim().isEmpty() || nomf.getText().trim().isEmpty() || pref.getText().trim().isEmpty() || emtf.getText().trim().isEmpty() || mdptf.getText().trim().isEmpty())
              {
                          Alert alert = new Alert(AlertType.INFORMATION);
                          alert.setTitle("Information Dialog");
                          alert.setHeaderText("Oops ! Un erreur c'est produit !");
                          alert.setContentText("Champs vide !");
                          alert.showAndWait();      
              }
              else if (nn.matches("[0-9]") || pp.matches("[0-9]"))
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, Chaine de characteres seulement !");
                  alert.showAndWait();
              }
               else if (mdp.length() < 8)
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, au moins 8 characteres !");
                  alert.showAndWait();
              }   
               else if (emtf.getText().matches("[@]"))
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, mail non valide !");
                  alert.showAndWait();
              }            
              else 
              {
                  clear();
                  UtilisateurCRUD ad = new UtilisateurCRUD();
                  Utilisateur a = new Utilisateur (ida,log,nn,pp,mail,dn,r,mdp);
                  ad.ajouter(a);
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Ajout d'un utilisateur");
                  alert.setContentText("Utilisateur ajoutèe !");
                  alert.showAndWait();
              }
          
              loadDataFromDatabase();
    }   
    @FXML
     private void Matiere() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("Matiere.fxml");}

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, IOException {
              UtilisateurCRUD ad = new UtilisateurCRUD();
              String h = idf.getText();
              int k =Integer.parseInt(h) ;
              ad.delete(k);
              clear();
              loadDataFromDatabase();
              rqtf.setText("Util. Supprimeè !");
              
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
            
              
              java.sql.Date dn = java.sql.Date.valueOf(ddntf.getValue());        
              String ida = idf.getText();
              String nn = nomf.getText();
              String pp = pref.getText();
              String log = ltf.getText();  
              String r = cbr.getValue();
              String mail = emtf.getText();
              String mdp = mdptf.getText();
              
              
               if(ltf.getText().trim().isEmpty() || nomf.getText().trim().isEmpty() || pref.getText().trim().isEmpty() || emtf.getText().trim().isEmpty() || mdptf.getText().trim().isEmpty())
              {
                          Alert alert = new Alert(AlertType.INFORMATION);
                          alert.setTitle("Information Dialog");
                          alert.setHeaderText("Oops ! Un erreur c'est produit !");
                          alert.setContentText("Champs vide !");
                          alert.showAndWait();      
              }
              else if (nn.contains("[0-9]") || pp.matches("[0-9]"))
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, Chaine de characteres seulement !");
                  alert.showAndWait();
              }
               else if (mdp.length() < 8)
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, au moins 8 characteres !");
                  alert.showAndWait();
              }   
               else if (emtf.getText().matches("[a-z]"))
              {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Dialog");
                  alert.setHeaderText("Oops ! Un erreur c'est produit !");
                  alert.setContentText("Erreur, mail non valide !");
                  alert.showAndWait();
              }            
              else {
              UtilisateurCRUD ad = new UtilisateurCRUD();
              Utilisateur a = new Utilisateur (ida,log,nn,pp,mail,dn,r,"123456789");
              ad.update(a);
              clear();
              loadDataFromDatabase();
               }
             
             
    }

    private void gtedt(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestion_admin.fxml"));
    Parent root = (Parent) fxmlLoader.load();
    Scene scene = new Scene(root, 600, 65);
    
    }
    
     private void loadDataFromDatabase() {
              
        cid.setCellValueFactory(new PropertyValueFactory<>("Id"));
        unc.setCellValueFactory(new PropertyValueFactory<>("Login"));
        mailc.setCellValueFactory(new PropertyValueFactory<>("mail"));
        rolec.setCellValueFactory(new PropertyValueFactory<>("role"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        cpre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ddnc.setCellValueFactory(new PropertyValueFactory<>("ddn"));
        mdpc.setCellValueFactory(new PropertyValueFactory<>("mdp")); 
        admin = UtilisateurCRUD.getInstance().displayAll();
        tva.setItems(admin);
    }
     
     public void clear() {
         idf.clear();
         nomf.clear();
         pref.clear();
         ltf.clear();
         emtf.clear();
         mdptf.clear();
        
     }
     
     String transferMessage(String message) {
         dd.setText(message);
         return null;   
     }
      @FXML
    private void Stage (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
    
}
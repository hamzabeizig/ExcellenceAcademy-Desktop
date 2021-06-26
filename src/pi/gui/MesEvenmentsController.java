/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pi.entities.Evenement;
import pi.services.EvenementCRUD;
import pi.tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mejri Wajih
 */
public class MesEvenmentsController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Button btn_org;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_ut;
    @FXML
    private Button btn_emp;
    @FXML
    private Button btn_dep;
    @FXML
    private Button btn_res;
    @FXML
    private Button btn_ev;
    @FXML
    private Button btn_stg;
    @FXML
    private Button btn_dec;
    @FXML
    private TableView<Evenement> tv;
    @FXML
    private TableColumn<Evenement, String> nom_ev;
    @FXML
    private TableColumn<Evenement, java.sql.Date> date_ev;
    @FXML
    private TableColumn<Evenement, String> nbp;
    @FXML
    private TableColumn<Evenement, String> desc;
    @FXML
    private TextField lll;
    @FXML
    private Button btn_mod;

        EvenementCRUD se = new EvenementCRUD();
    
    private Statement ste;
    private Connection con;
    private ObservableList<Evenement> evenement=FXCollections.observableArrayList();
    @FXML
    private Button btn_an;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               Aff();

    }
    
    
         public void Aff(){
                        try {
            con = MyConnection.getInstance().getConnection();
            ste = con.createStatement();
            evenement.clear();

            ResultSet res = ste.executeQuery("select * from evenement WHERE id_user=1 ");
            while(res.next()){
                Evenement e=new Evenement(res.getString(1),res.getString(2),res.getDate(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8));
           
                evenement.add(e);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
        nom_ev.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom"));
        date_ev.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));
        nbp.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nbr_place"));
        desc.setCellValueFactory(new PropertyValueFactory<Evenement,String>("description"));
        tv.setItems(evenement);


    }
    @FXML
    private void formulaire(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrgansierEvenement.fxml"));
        Parent root = loader.load();
        
        OrgansierEvenementController apc =loader.getController();
        apc.disablemodif();
        btn_org.getScene().setRoot(root);
    }

    @FXML
    private void formulaire1(ActionEvent event) throws SQLException {
            tv.setItems(evenement);

             ObservableList<Evenement> allevents,Singleevent ;
             allevents=tv.getItems();
             Singleevent=tv.getSelectionModel().getSelectedItems();
             Evenement A = Singleevent.get(0);
             se.deletepart(A.getId());
             se.delete(A.getId());
             Singleevent.forEach(allevents::remove);
             Aff();
  }



    @FXML
    private void formulaire2(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("OrgansierEvenement.fxml"));
        Parent root = loader.load();
        OrgansierEvenementController apc = loader.getController();
        Evenement tab_Eventselected = tv.getSelectionModel().getSelectedItem();

        apc.showInformation(tab_Eventselected.getId(),tab_Eventselected.getNom(),tab_Eventselected.getDate(),tab_Eventselected.getResponsable(),tab_Eventselected.getDescription(),tab_Eventselected.getNbr_place());
        btn_mod.getScene().setRoot(root);
    }

    @FXML
    private void retour(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            FXMLDocumentController apc = loader.getController();
            btn_an.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(OrgansierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void Stage (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
    
}
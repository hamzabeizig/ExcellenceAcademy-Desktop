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
 * @author asus
 */
public class EvenmentParticiperController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private Button btn_org;
    @FXML
    private Button btn_res;
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
    private Button btn_desins;
    @FXML
    private Button btn_an;

    
    EvenementCRUD se = new EvenementCRUD();

    private Statement ste;
    private Connection con;
    private ObservableList<Evenement> evenement=FXCollections.observableArrayList();
    @FXML
    private Button btn_prfl;
    @FXML
    private Button btn_Emp;
    @FXML
    private Button btn_mat;
    @FXML
    private Button btn_cr;
    @FXML
    private Button btn_ass;

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
            ResultSet res = ste.executeQuery("select * from `evenement`,`participer` WHERE `participer`.`idevent`=`evenement`.`id_evenement` AND `participer`.`iduser`=1 ");
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
    private void intermesev(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MesEvenments.fxml"));
        Parent root = loader.load();
        MesEvenmentsController apc =loader.getController();
        btn_org.getScene().setRoot(root);

    }

    @FXML
    private void desinscrireevent(MouseEvent event) throws SQLException {
                      Evenement tab_Eventselected = tv.getSelectionModel().getSelectedItem();
      String tmp = tab_Eventselected.getNbr_place();
      int i=Integer.parseInt(tmp);  

          i++;
          tab_Eventselected.setNbr_place(Integer.toString(i));
          se.departiciper(tab_Eventselected.getId(), tab_Eventselected.getId_user());
          se.update(tab_Eventselected);
          Aff();
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
    private void checkinsc(MouseEvent event) {
    }
     @FXML
    private void Stage (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
    @FXML
    private void Emplois (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
@FXML
    private void profil (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("Profile.fxml");
    }
@FXML
    private void Resultat (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
@FXML
    private void Matrieres (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("Matiere.fxml");
    }
@FXML
    private void Cours (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
@FXML
    private void Assiduité (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
@FXML
    private void Deconnecter (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }


    
}
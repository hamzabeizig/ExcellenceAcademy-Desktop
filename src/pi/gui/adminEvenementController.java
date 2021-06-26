/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

/**
 *
 * @author 21655
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pi.entities.Evenement;
import pi.entities.SendMail;
import pi.entities.Utilisateur;
import pi.services.EvenementCRUD;
import pi.tools.MyConnection;
public class adminEvenementController implements Initializable{
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
    private TableColumn<Evenement, String> etat;
    @FXML
    private Button btnapp;
    @FXML
    private Button btndesap;
    
    EvenementCRUD se = new EvenementCRUD();
    
    private Statement ste;
    private Connection con;
    private ObservableList<Evenement> evenement=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Evenement, String> iduser;
    @FXML
    private TableColumn<Evenement, String> responsable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            Aff();
    }
     public void Aff(){
                        try {
            con = MyConnection.getInstance().getConnection();
            ste = con.createStatement();
            evenement.clear();

            ResultSet res = ste.executeQuery("select * from evenement");
            while(res.next()){
                Evenement e=new Evenement(res.getString(1),res.getString("nom_evenement"),res.getDate("date_evenement"),res.getString("responsable"),res.getString("description"),res.getString("id_user"),res.getString("nbr_place"),res.getString("etat"));
           
                evenement.add(e);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
        nom_ev.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom"));
        date_ev.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));
        nbp.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nbr_place"));
        desc.setCellValueFactory(new PropertyValueFactory<Evenement,String>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<Evenement,String>("etat"));
        responsable.setCellValueFactory(new PropertyValueFactory<Evenement,String>("responsable"));
        iduser.setCellValueFactory(new PropertyValueFactory<Evenement,String>("id_user"));
        tv.setItems(evenement);


    }
    @FXML
    private void checkinsc(MouseEvent event) {
       if(tv.getSelectionModel().getSelectedItem().getEtat().equals("Non aprouvée"))
                {
                    btnapp.setDisable(false);
                    btndesap.setDisable(true);
                }
        else
                {
                    btnapp.setDisable(true);
                    btndesap.setDisable(false);
                }

    }

    @FXML
    private void approuv(ActionEvent event) throws SQLException {
      Evenement tab_Eventselected = tv.getSelectionModel().getSelectedItem();
      tab_Eventselected.setEtat("aprouvée");
      Utilisateur user = se.getmail(tab_Eventselected.getId_user());
      SendMail.sendMail(user.getMail1(), "Evenement Approuvé", "Votre evenement a ete approuvée");

        System.out.println(user.getMail1());
      se.updateapp(tab_Eventselected);
      Aff();

    }

    @FXML
    private void desaprouv(ActionEvent event) throws SQLException {
      Evenement tab_Eventselected = tv.getSelectionModel().getSelectedItem();
      tab_Eventselected.setEtat("Non aprouvée");
      se.updateapp(tab_Eventselected);
      Aff();
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
    private void departement (ActionEvent event) throws IOException{
        Pi l = new Pi();
     l.changeScene("Departement.fxml");
    }
     @FXML 
    private void authentification (ActionEvent event) throws IOException{
        Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }
     @FXML
    private void Stage (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
    
}


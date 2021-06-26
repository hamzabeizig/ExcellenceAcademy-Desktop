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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
import pi.entities.Evenement;
import pi.entities.Participer;
import pi.services.EvenementCRUD;
import pi.tools.MyConnection;
public class FXMLDocumentController implements Initializable{
    @FXML
    private Button btn_emp;
    @FXML
    private Button btn_res;
    @FXML
    private Button btn_stg;
    @FXML
    private TableColumn<Evenement, String> nom_ev;
    @FXML
    private TableColumn<Evenement, java.sql.Date> date_ev;
    @FXML
    private TableColumn<Evenement, String> nbp;
    @FXML
    private TableColumn<Evenement, String> desc;
    private Button btn_mod;
    @FXML
    private Button btn_insc;
    @FXML
  private TableView<Evenement> tv;
    

    private ObservableList<Evenement> evenement=FXCollections.observableArrayList();

    EvenementCRUD se = new EvenementCRUD();

    private Statement ste;
    private Connection con;
    @FXML
    private Label label1;
    @FXML
    private Button btn_org;
    @FXML
    private Button btn_desins;
    @FXML
    private Button btn_mesparticip;
    @FXML
    private TextField recherche;
    @FXML
    private Button btn_profil;
    @FXML
    private Button btn_mat;
    @FXML
    private Button btn_cr;
    @FXML
    private Button btn_ass;
    @FXML
    private Button btn_de;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         Aff();
       RechercheAV();
    }
      public void Aff(){
                        try {
            con = MyConnection.getInstance().getConnection();
            ste = con.createStatement();
            evenement.clear();

            ResultSet res = ste.executeQuery("select * from evenement WHERE etat='aprouvée'");
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
    private void desinscrireevent(MouseEvent event) throws SQLException {
              Evenement tab_Eventselected = tv.getSelectionModel().getSelectedItem();
      String tmp = tab_Eventselected.getNbr_place();
      int i=Integer.parseInt(tmp);  

          i++;
          tab_Eventselected.setNbr_place(Integer.toString(i));
          se.departiciper(tab_Eventselected.getId(), tab_Eventselected.getId_user());
          se.update(tab_Eventselected);
          Aff();
          RechercheAV();

          Notifications notificationBuilder = Notifications.create()               
        .title("Desinscrie").text("Vous avez desinscrie").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();

    } 
    @FXML
    private void sinscrireevent(MouseEvent event) throws SQLException {
      Evenement tab_Eventselected = tv.getSelectionModel().getSelectedItem();
      String tmp = tab_Eventselected.getNbr_place();
      int i=Integer.parseInt(tmp);  
      if(i==0)
      {
          
      }
      else
      {
          i--;
          tab_Eventselected.setNbr_place(Integer.toString(i));
          se.participer(tab_Eventselected.getId(), tab_Eventselected.getId_user());
          se.update(tab_Eventselected);
          Aff();
          RechercheAV();
                  
          Notifications notificationBuilder = Notifications.create()               
        .title("Inscrie").text("Vous ete inscrit").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();

      }


    }

    @FXML
    private void checkinsc(MouseEvent event) {
              Evenement tab_Eventselected = tv.getSelectionModel().getSelectedItem();

          Participer part=se.getById(tab_Eventselected.getId(), "1");
          if(part==null)
          {
          if(tv.getSelectionModel().getSelectedItem().getNbr_place().equals("0"))
                {
                    btn_insc.setDisable(true);
                    btn_desins.setDisable(true);
                }
        else
                {
                    btn_insc.setDisable(false);
                    btn_desins.setDisable(true);
                }
                              
                
          }
          else
          {
                    btn_insc.setDisable(true);
                    btn_desins.setDisable(false);
          }

    }

    @FXML
    private void intermesev(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesEvenments.fxml"));
        Parent root = loader.load();
        MesEvenmentsController apc =loader.getController();
        btn_org.getScene().setRoot(root);
    }
       @FXML
    private void deconnecter2(ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }

    @FXML
    private void interparticip(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenmentParticiper.fxml"));
        Parent root = loader.load();
        EvenmentParticiperController apc =loader.getController();
        btn_org.getScene().setRoot(root);
    }


          public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evenement> filteredData = new FilteredList<>(evenement, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(event -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(event.getDescription()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Evenement> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tv.setItems(sortedData);
    }
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
    private void Deconnecter (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }

    @FXML
    private void stage(ActionEvent event) {
    }

   
    
}

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pi.entities.*;
import pi.services.UtilisateurCRUD;
import pi.services.stagecrud;
import pi.tools.*;
 




public class FXMLDocumentController1 implements Initializable {
	

    
    
    @FXML private TableView<Stages> table;
    @FXML private TableColumn<Stages,String> societe;
    @FXML private TableColumn<Stages,String> email_societe;
    @FXML private TableColumn<Stages,String> pays;
    @FXML private TableColumn<Stages,Date> date_debut;
    @FXML private TableColumn<Stages,Date> date_fin;
    @FXML private TableColumn<Stages,String> type_stage;
    stagecrud sc = new  stagecrud();
    
    public ObservableList<Stages> data =FXCollections.observableArrayList();
    @FXML
    private Button btn_prf;
    @FXML
    private Button btn_emp;
    @FXML
    private Button btn_res;
    @FXML
    private Button btn_ev;
    @FXML
    private Button btn_mat;
    @FXML
    private Button btn_cr;
    @FXML
    private Button btn_ass;
    @FXML
    private Button btn_dec;
    @FXML
    private TextField txt ;
    
    public String login ;
    
      
	public void quitter(ActionEvent event) { 
		System.exit(0);
	}
	@Override
	public void initialize (URL url, ResourceBundle rb ) {
            txt.setVisible(false);
	      try {
	            Connection con;
	            con =MyConnectionn.connect();
	            String sql="Select * from stage" ; 
	            PreparedStatement stat =((java.sql.Connection) con).prepareStatement(sql); 
	            ResultSet rs= stat.executeQuery();
	            while(rs.next())
	            {
	            	data.add(new Stages(rs.getString(2), rs.getString(3) , rs.getString(4) , rs.getDate(5).toLocalDate() , rs.getDate(6).toLocalDate(),rs.getString(7) ));
	            }
	            con.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        societe.setCellValueFactory(new PropertyValueFactory<Stages,String>("societe"));
	        email_societe.setCellValueFactory(new PropertyValueFactory<Stages,String>("email_societe"));
	        pays.setCellValueFactory(new PropertyValueFactory<Stages,String>("pays"));
	        date_debut.setCellValueFactory(new PropertyValueFactory<Stages,Date>("date_debut"));
	        date_fin.setCellValueFactory(new PropertyValueFactory<Stages,Date>("date_fin"));
	        type_stage.setCellValueFactory(new PropertyValueFactory<Stages,String>("type_stage"));
	        table.setItems(data);
		
	}
	

        
        @FXML
	
        
        
        public void afficherfor() throws IOException{
        Stage stage=new Stage() ;
		
		FXMLLoader loader = new FXMLLoader() ; 
		loader.setLocation(getClass().getResource("postulation.fxml"));
		Parent root=loader.load();
		Scene scene =new Scene(root);
		PostulationStage controller = loader.getController();
        if (table.getSelectionModel().getSelectedItem()==null) {
    		
    		Alert alert=new Alert(AlertType.INFORMATION) ;
    		alert.setTitle("Excellence Academy");
    		alert.setHeaderText(null);
    		alert.setResizable(false);
    		stage = (Stage) alert.getDialogPane().getScene().getWindow();
    		stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
    		alert.setContentText("Prière de selectionner un stage a postuler");
    		alert.showAndWait();
    		
    	}else {
    		System.out.println(table.getSelectionModel().getSelectedItem().getEmail_societe());
		controller.InitData(table.getSelectionModel().getSelectedItem());
		stage.setResizable(false);
		stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
		stage.setTitle("Exellence Academy");

		stage.setScene(scene) ;
		stage.show();
    	}
		   }
		
		   
	
       void transferMessage(String message) {
      
           txt.setText(message);
       
    }
       
        
    @FXML
	void demander() throws SQLException {
	             	
            
		   convention cvt = new convention( "Amri Wael",  "Amri",  "Wael",  "mohamedkhalil.guedria@esprit.tn",  3) ;
		   stagecrud stc= new stagecrud() ; 
           stc.demander(cvt);		
		
	}
        public List<String> getutil() throws SQLException{
ObservableList<Utilisateur> ls1=sc.loginut(txt.getText());   
List<String> ls = new ArrayList<String>();
        String username = ls1.get(1).getLogin();
        String nom = ls1.get(1).getNom();
        String prenom =ls1.get(1).getPrenom();
        String email = ls1.get(1).getMail();
        String id_user = ls1.get(1).getId();
        ls.add(username);
        ls.add(nom);
        ls.add(prenom);
        ls.add(email);
        ls.add(id_user);
        return ls ;
        }
        @FXML
           private void Evenement (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument.fxml");
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


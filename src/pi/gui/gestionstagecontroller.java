package pi.gui;



import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pi.entities.*;
import pi.services.stagecrud;
import pi.tools.*;

public class gestionstagecontroller implements Initializable {
	
	@FXML public  TableView<Stages> table;
    @FXML private TableColumn<Stages,Integer> id;
    @FXML private TableColumn<Stages,String> societe;
    @FXML private TableColumn<Stages,String> email_societe;
    @FXML private TableColumn<Stages,String> pays;
    @FXML private TableColumn<Stages,Date> date_debut; 
    @FXML private TableColumn<Stages,Date> date_fin; 
    @FXML private TableColumn<Stages,String> type_stage;
    
    
    public ObservableList<Stages> data =FXCollections.observableArrayList();
    
  
   
 
   
 
   
   


	@Override
	public void initialize (URL url, ResourceBundle rb ) {
	      try {
	            Connection con;
	            con =MyConnectionn.connect();
	            String sql="Select * from stage" ; 
	            PreparedStatement stat =((java.sql.Connection) con).prepareStatement(sql); 
	            ResultSet rs= stat.executeQuery();
	            while(rs.next())
	            {
		        	data.add(new Stages(rs.getInt(1),rs.getString(2), rs.getString(3) ,rs.getString(4), rs.getDate(5).toLocalDate() , rs.getDate(6).toLocalDate() , rs.getString(7) ));
	            }
	            con.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        id.setCellValueFactory(new PropertyValueFactory<Stages,Integer>("id"));
	        societe.setCellValueFactory(new PropertyValueFactory<Stages,String>("societe"));
	        email_societe.setCellValueFactory(new PropertyValueFactory<Stages,String>("email_societe"));
	        pays.setCellValueFactory(new PropertyValueFactory<Stages,String>("pays"));
	        date_debut.setCellValueFactory(new PropertyValueFactory<Stages,Date>("date_debut"));
	        date_fin.setCellValueFactory(new PropertyValueFactory<Stages,Date>("date_fin"));
	        type_stage.setCellValueFactory(new PropertyValueFactory<Stages,String>("type_stage"));
	        table.setItems(data);
		
	}
	
	
	
	public void afficherform() throws IOException{
		   
		Stage stage=new Stage() ;
		Parent root=FXMLLoader.load(getClass().getResource("ajout.fxml"));
		Scene scene =new Scene(root);
		stage.setResizable(false);
		stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
		stage.setTitle("Exellence Academy");

		stage.setScene(scene) ;
		stage.show();
		   }
	
	
	public void afficherform2() throws IOException{
		   
		Stage stage=new Stage() ;
		
		FXMLLoader loader = new FXMLLoader() ; 
		loader.setLocation(getClass().getResource("modification.fxml"));
		Parent root=loader.load();
		Scene scene =new Scene(root);
		stagecontrollermodif2 controller = loader.getController();
        if (table.getSelectionModel().getSelectedItem()==null) {
    		
    		Alert alert=new Alert(AlertType.INFORMATION) ;
    		alert.setTitle("Exellence Academy");
    		alert.setHeaderText(null);
    		alert.setResizable(false);
    		stage = (Stage) alert.getDialogPane().getScene().getWindow();
    		stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
    		alert.setContentText("Pri�re de selectionner un stage a modifier");
    		alert.showAndWait();
    		
    	}else {
		controller.InitData(table.getSelectionModel().getSelectedItem());
		stage.setResizable(false);
		stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
		stage.setTitle("Excellence Academy");

		stage.setScene(scene) ;
		stage.show();
    	}
		   }
	
	
	public void actualiser() throws SQLException {
		
	        table.getItems().clear();
		
		try {
	        Connection con;
	        con =MyConnectionn.connect();
	        String sql="SELECT * FROM stage" ; 
	        PreparedStatement stat =((java.sql.Connection) con).prepareStatement(sql); 
	        ResultSet rs= stat.executeQuery();
	        while(rs.next())
	        {
	        	data.add(new Stages(rs.getInt(1),rs.getString(2), rs.getString(3) ,rs.getString(4), rs.getDate(5).toLocalDate() , rs.getDate(6).toLocalDate() , rs.getString(7) ));	        }
	        con.close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
        id.setCellValueFactory(new PropertyValueFactory<Stages,Integer>("id"));
        email_societe.setCellValueFactory(new PropertyValueFactory<Stages,String>("email_societe"));
	    societe.setCellValueFactory(new PropertyValueFactory<Stages,String>("societe"));
	    pays.setCellValueFactory(new PropertyValueFactory<Stages,String>("pays"));
	    date_debut.setCellValueFactory(new PropertyValueFactory<Stages,Date>("date_debut"));
	    date_fin.setCellValueFactory(new PropertyValueFactory<Stages,Date>("date_fin"));
	    type_stage.setCellValueFactory(new PropertyValueFactory<Stages,String>("type_stage"));
	    table.setItems(data);
		 
		
	} 	
	
	
	public void delete(ActionEvent event) throws SQLException {
		
		Stages item = table.getSelectionModel().getSelectedItem() ; 
		stagecrud stc= new stagecrud() ;
		table.getItems().removeAll(table.getSelectionModel().getSelectedItem()) ;
		stc.delete(item);
	}
		
		
	
	
	public void afficherform3() throws IOException{
		   
		Stage stage=new Stage() ;
		Parent root=FXMLLoader.load(getClass().getResource("gestionconventions.fxml"));
		Scene scene =new Scene(root);
		stage.setResizable(false);
		stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
		stage.setTitle("Exellence Academy");

		stage.setScene(scene) ;
		stage.show();
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
    private void departement(ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("Departement.fxml");
    }
     @FXML
     public void evenement1() throws IOException{
 
     Pi l = new Pi();
     l.changeScene("adminEvenement.fxml");
     
} 
    }
	



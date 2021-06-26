package pi.gui;



import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pi.entities.*;
import pi.services.stagecrud;
import pi.tools.*;

public class gestionconventioncontroller implements Initializable {
	
	@FXML public  TableView<convention> table;
    @FXML private TableColumn<convention,Integer> id_conv;
    @FXML private TableColumn<convention,String> user_name;
    @FXML private TableColumn<convention,String> nom;
    @FXML private TableColumn<convention,String> prenom;
    @FXML private TableColumn<convention,String> email;
    @FXML private TableColumn<convention,Integer> id_user;
    
    
    public ObservableList<convention> data =FXCollections.observableArrayList();
    
  
   
 
   
 
   
   


	@Override
	public void initialize (URL url, ResourceBundle rb ) {
	      try {
	            Connection con;
	            con =MyConnectionn.connect();
	            String sql="Select * from dem_conv where etat_demande='en attente'" ; 
	            PreparedStatement stat =((java.sql.Connection) con).prepareStatement(sql); 
	            ResultSet rs= stat.executeQuery();
	            while(rs.next())
	            {
		        	data.add(new convention(rs.getInt(1),rs.getString(2), rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getInt(7) ));
	            }
	            con.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        id_conv.setCellValueFactory(new PropertyValueFactory<convention,Integer>("id_conv"));
	        user_name.setCellValueFactory(new PropertyValueFactory<convention,String>("user_name"));
	        nom.setCellValueFactory(new PropertyValueFactory<convention,String>("nom"));
	        prenom.setCellValueFactory(new PropertyValueFactory<convention,String>("prenom"));
	        email.setCellValueFactory(new PropertyValueFactory<convention,String>("email"));
	        id_user.setCellValueFactory(new PropertyValueFactory<convention,Integer>("id_user"));
	        table.setItems(data);
		
	}
	
	
	
	
	public void accepter(ActionEvent event) throws SQLException {
		if(table.getSelectionModel().getSelectedItem()!=null) {
		int id=table.getSelectionModel().getSelectedItem().getId_user();
		
		System.out.println(id) ; 
		Connection con;
        con =MyConnectionn.connect();
		PreparedStatement pre = con.prepareStatement("Update dem_conv SET etat_demande='accepté' where id_user=?");
		pre.setInt(1, id);
        pre.executeUpdate();
    	stagecrud stc= new stagecrud() ; 
    	try {
			stc.sendMailaccept(table.getSelectionModel().getSelectedItem().getEmail() , table.getSelectionModel().getSelectedItem());
			table.getItems().removeAll(table.getSelectionModel().getSelectedItem()) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			Alert alert=new Alert(AlertType.INFORMATION) ;
			alert.setTitle("Excellence Academy");
			alert.setHeaderText(null);
			alert.setResizable(false);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
			alert.setContentText("Prière de selectionner une demande");
			alert.showAndWait();
			
			
		}
		
		
	}
	
	
	
	public void refuser(ActionEvent event) throws SQLException {
    	stagecrud stc= new stagecrud() ; 

		if(table.getSelectionModel().getSelectedItem()!=null) {
		int id=table.getSelectionModel().getSelectedItem().getId_user();
		try {
			stagecrud.sendMailrefuser(table.getSelectionModel().getSelectedItem().getEmail() , table.getSelectionModel().getSelectedItem());
			table.getItems().removeAll(table.getSelectionModel().getSelectedItem()) ;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(id) ; 
		Connection con;
        con =MyConnectionn.connect();
		PreparedStatement pre = con.prepareStatement("Update dem_conv SET etat_demande='refusé' where id_user=?");
		pre.setInt(1, id);
        pre.executeUpdate();
		}else {
			Alert alert=new Alert(AlertType.INFORMATION) ;
			alert.setTitle("Excellence Academy");
			alert.setHeaderText(null);
			alert.setResizable(false);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
			alert.setContentText("Prière de selectionner une demande");
			alert.showAndWait();
			
		}
		
	}
	
	
	
	
	
	
	
	
	
}


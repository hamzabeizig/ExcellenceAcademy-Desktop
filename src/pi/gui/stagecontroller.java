package pi.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pi.entities.Evenement;
import pi.entities.Stages;
import pi.services.stagecrud;
import pi.tools.MyConnectionn;

/**
 *
 * @author ASUS
 */
public class stagecontroller implements Initializable {

    private static final ResourceBundle ResourceBundle = null;
	@FXML
    private TextField txts;
	@FXML
	private TextField txte;
    @FXML
    private TextField txtp;
    @FXML
    private DatePicker dateP1;
    @FXML
    private DatePicker dateP2;
    @FXML
    private TextField  txtty;
    
    
    

    
    public static boolean isValid(String email)
    {
    	if( email!=null && email.trim().length()>0 )
    		return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
    	return false;
    }

    

       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
    	
    	
		
       
    }
    
    
      
      

    @FXML
    public void ajouter(ActionEvent event) throws SQLException {
	if(txts.getText()!="" &&  txte.getText()!="" && txtp.getText()!="" && dateP1.getValue()!=null && dateP2.getValue()!=null && txtty.getText()!=""  ){
	if(isValid(txte.getText())) {
    String soc =txts.getText();
	String esoc =txte.getText();	
	String pay=txtp.getText();
	LocalDate dated=dateP1.getValue();
	LocalDate datef=dateP2.getValue();
	if(dated.isBefore(datef)) {
	String typs=txtty.getText();
	Stages s =new Stages(soc,esoc,pay,dated,datef,typs);
	stagecrud stc= new stagecrud() ; 
	stc.Ajouter(s);
	txts.clear();
	txte.clear();
	txtp.clear();
	dateP1.setValue(null);
	dateP2.setValue(null);
	txtty.clear();
	}else {
		Alert alert=new Alert(AlertType.ERROR) ;
		alert.setTitle("Exellence Academy");
		alert.setHeaderText(null);
		alert.setResizable(false);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
		alert.setContentText("Prière de vérifier la date");
		alert.showAndWait();			
	}
	}else {
		
		Alert alert=new Alert(AlertType.ERROR) ;
		alert.setTitle("Exellence Academy");
		alert.setHeaderText(null);
		alert.setResizable(false);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
		alert.setContentText("Prière de saisir une adresse email valide");
		alert.showAndWait();
	
		
		
	}
	
			
	}else {
		
		 Alert alert=new Alert(AlertType.ERROR) ;
			alert.setTitle("Exellence Academy");
			alert.setHeaderText(null);
			alert.setResizable(false);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
			alert.setContentText("Prière de remplir tous les champs");
			alert.showAndWait();
		
	}
	 
	
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
    private void Evenement (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument.fxml");
    }
@FXML
    private void Deconnecter (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("authentification.fxml");
    }
	
	
}



    
	
   
    
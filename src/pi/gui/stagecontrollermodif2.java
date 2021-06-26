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
public class stagecontrollermodif2 implements Initializable {

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
    
    private Stages selectedstage ; 
    
   
    
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

    


    public void InitData(Stages stg) {
    	
    	selectedstage = stg ;
    	txts.setText(selectedstage.getSociete());
    	System.out.println(selectedstage.getEmail_societe()) ; 
    	txte.setText(selectedstage.getEmail_societe());
    	txtp.setText(selectedstage.getPays());
    	dateP1.setValue(selectedstage.getDate_debut());
    	dateP2.setValue(selectedstage.getDate_fin());
    	txtty.setText(selectedstage.getType_stage());
    	
    	
    	
    }

       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
    	
    	
    	
    	
		
       
    }
    
    
      
        
    public static boolean isValid(String email)
    {
    	if( email!=null && email.trim().length()>0 )
    		return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
    	return false;
    }      
        
      
        

   


   
      

   
//    @FXML
//    private void modifier(ActionEvent event) throws IOException {
//            
//       System.out.println("crud.Crud.Gestion_admidsdqsdqnsController.modifier()");
//        
//        
//         
//              java.sql.Date dn = java.sql.Date.valueOf(ddntf.getValue());        
//              String ida = idf.getText();
//              String nn = nomf.getText();
//              String pp = pref.getText();
//              String log = ltf.getText();  
//              String r = cbr.getValue();
//              String mail = emtf.getText();
//      
//              adminCRUD ad = new adminCRUD();
//              admin a = new admin (ida,log,nn,pp,mail,dn,r);
//              ad.update(a);
//             
//              main n = new main();
//              n.changeScene("Gestion_admins.fxml");
//    }
//
//    @FXML
//    private void gtedt(ActionEvent event) throws IOException {
//    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GestionEDT.fxml"));
//    Parent root = (Parent) fxmlLoader.load();
//    Scene scene = new Scene(root, 600, 65);
//    
//    }
//    
  
@FXML



public void modifier(ActionEvent event) throws SQLException {
	if(txts.getText()!="" &&  txte.getText()!="" && txtp.getText()!="" && dateP1.getValue()!=null && dateP2.getValue()!=null && txtty.getText()!=""  ){
		if(isValid(txte.getText())) {
	String soc =txts.getText();
	String esoc =txte.getText();
	String pay=txtp.getText();
	LocalDate dated=dateP1.getValue();
	LocalDate datef=dateP2.getValue();
	if(dated.isBefore(datef)) {
	String typs=txtty.getText();
	stagecrud stc= new stagecrud() ; 
	Stages stg=new Stages() ; 
	stg.setSociete(soc);
	stg.setEmail_societe(esoc);
	stg.setPays(pay);
	stg.setDate_debut(dated);
	stg.setDate_fin(datef);
	stg.setType_stage(typs);
	stg.setId(selectedstage.getId());
    stc.modifier(stg);
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



    
	
   
    
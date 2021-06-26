package pi.gui;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import pi.entities.Stages;
import pi.entities.convention;
import pi.services.stagecrud;


public class PostulationStage {

	@FXML
	private Label labsing ;
	@FXML
	private TextField txts ;
	@FXML
	private TextField txte ;
	@FXML
	private TextField txtp ;
	@FXML
	private DatePicker datep1 ;
	@FXML
	private DatePicker datep2 ;
	@FXML
	private TextField txtty ;
	
	@FXML
	private Label lab ; 
	public FileChooser fo=new FileChooser();
	public File cv = null;
	public File ldm = null;
    private Stages selectedstage ; 


	
	
	public void initialize (URL url, ResourceBundle rb ) {
			
	}
	
	public void postuler () {
    	stagecrud stc= new stagecrud() ; 
        try {
			stc.sendMailpostulation(selectedstage.getEmail_societe(), cv, ldm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert=new Alert(AlertType.INFORMATION) ;
		alert.setTitle("Excellence Academy");
		alert.setHeaderText(null);
		alert.setResizable(false);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("/gui/pics/iconelogo.png")); 
		alert.setContentText("Votre demande est en cours de traitement");
		alert.showAndWait();
		
		
	}
	
	
	
    public void InitData(Stages stg) {
    	
	selectedstage = stg ;
	txts.setText(selectedstage.getSociete());
	txtp.setText(selectedstage.getPays());
	txtty.setText(selectedstage.getType_stage());
	System.out.println(selectedstage.getEmail_societe());
	txte.setText(selectedstage.getEmail_societe());
	datep1.setValue(selectedstage.getDate_debut());
	datep2.setValue(selectedstage.getDate_fin());
	datep1.setDisable(true);
    datep2.setDisable(true);
	txts.setEditable(false);
	txts.setDisable(true);
	txte.setDisable(true);
	txtp.setDisable(true);
	txtty.setDisable(true);
	txtp.setEditable(false);
	datep1.setEditable(false);
	datep2.setEditable(false);
	txtty.setEditable(false);	
    	
    }
	
	public void singlefilechooser(ActionEvent event) {
		
		  cv = fo.showOpenDialog(null);
		fo.getExtensionFilters().add(new ExtensionFilter("PDF Files","*.pdf")) ;
		
		if(cv!=null) {
			
			labsing.setText(cv.getName());
		}
	}
	 public void singlefilechooser1(ActionEvent event) {
			
			FileChooser fo=new FileChooser();
			fo.getExtensionFilters().add(new ExtensionFilter("PDF Files","*.pdf")) ;
			 ldm = fo.showOpenDialog(null);
			
			if(ldm!=null) {
				
				lab.setText(ldm.getName());
			}
		
		
	}
}

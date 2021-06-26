/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

/**
 *
 * @author 21655
 */import pi.gui.FXMLDocumentController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pi.entities.Evenement;
import pi.services.EvenementCRUD;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class OrgansierEvenementController implements Initializable {

    @FXML
    private Label label1;

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
    private Label nom_ev;

    @FXML
    private Label date_ev;

    @FXML
    private Label resp;

    @FXML
    private Label desc;

    private TextField txtN;

    @FXML
    private TextField respo;

    @FXML
    private TextField descr;

    @FXML
    private DatePicker dateE;

    @FXML
    private Label nbp;

    @FXML
    private TextField nbr;

    @FXML
    private Button btn_va;

    @FXML
    private Button btn_an;

    @FXML
    private Button btn_va1;
    @FXML
    private TextField nomtf;
    @FXML
    private Label idlabel;
    @FXML
    private Label msg;
    
   
    
 

  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
  public void retour(ActionEvent event) {
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
    private void ajout(ActionEvent event) throws SQLException, IOException {
                if(verif(nomtf)&&verif(respo)&&verif(descr))
                {
              java.sql.Date dn = java.sql.Date.valueOf(dateE.getValue()); 
              String nom = nomtf.getText();
              String resposable = respo.getText();
              String description = descr.getText();  
              String nbplace = nbr.getText();
             
         
              EvenementCRUD ad = new EvenementCRUD();
              Evenement a = new Evenement ("",nom,dn,resposable,description,"1",nbplace,"Non aprouvée");
              ad.Organiser(a);
              msg.setText("Ajoutée avec succes");
              nomtf.setText("");
              respo.setText("");
              descr.setText("");
              nbr.setText("");
              }
                else
                {
                 msg.setText("Verifier les champs");
                }
             
    }   

    @FXML
    private void modif(ActionEvent event) throws SQLException {
                if(verif(nomtf)&&verif(respo)&&verif(descr))
                {
                     DatePicker datsort=(DatePicker) dateE;
                String date= (String) datsort.getValue().toString();
                date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);                
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

              String nom = nomtf.getText();
              String resposable = respo.getText();
              String description = descr.getText();  
              String nbplace = nbr.getText();
             
            
         
              EvenementCRUD ad = new EvenementCRUD();
              Evenement a = new Evenement (idlabel.getText(),nom,sqlDate,resposable,description,"1",nbplace,"");
              ad.update(a);
              
              msg.setText("Modifiée avec succes");
              nomtf.setText("");
              respo.setText("");
              descr.setText("");
              nbr.setText("");
                }
                else
                {
                 msg.setText("Verifier les champs");
                }
                    
        
               
             
    }
    
    public static final LocalDate LOCAL_DATE (String dateString){
        System.out.println(dateString);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}

       public void showInformation(String id,String nom,Date dateevent,String respos,String description,String nbplace)
    {
        idlabel.setText(id);
                String date= (String) dateevent.toString();
                date = date.substring(8)+'/'+date.substring(5,7)+'/'+date.substring(0,4);                


        dateE.setValue(LOCAL_DATE(date));

        nomtf.setText(nom);
        respo.setText(respos);
        descr.setText(description);
        nbr.setText(nbplace);
        btn_va.setDisable(true);
    }
              public void disablemodif()
    {
         btn_va1.setDisable(true);
    }

           private boolean verif(TextField t){
               return t.getLength()>3 && !t.getText().isEmpty();
                
            } 
            @FXML
    private void Stage (ActionEvent event) throws IOException {
     Pi l = new Pi();
     l.changeScene("FXMLDocument2.fxml");
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pi.entities.Classe;
import pi.entities.Enseignant;
import pi.entities.Matiere;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.services.EnseignantAdminCRUD;
import pi.services.ClasseAdminCRUD;
import pi.services.EnseignantCRUD;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class guiGestionEmploiAdmin implements Initializable {
  Document d; 

    @FXML
    private Label label;
    
    @FXML
    private ChoiceBox<String> m11;
    @FXML
    private ChoiceBox<String> m12;
    @FXML
    private ChoiceBox<String> m21;
    @FXML
    private ChoiceBox<String> m31;
    @FXML
    private ChoiceBox<String> m41;
    @FXML
    private ChoiceBox<String> m51;
    @FXML
    private ChoiceBox<String> m61;
    @FXML
    private ChoiceBox<String> m22;
    @FXML
    private ChoiceBox<String> m32;
    @FXML
    private ChoiceBox<String> m42;
    @FXML
    private ChoiceBox<String> m52;


    @FXML
    private Text t1;
    @FXML
    private Text t2;
    @FXML
    private Text t3;
    @FXML
    private Text t4;
    @FXML
    private Text t5;
    @FXML
    private Text t6;
    @FXML
    private Text t7;
    @FXML
    private Text t8;
    @FXML
    private Text t9;
    @FXML
    private Text t10;
    @FXML
    private Text t11;
    @FXML
    private ComboBox<String> drop_class;
    @FXML
    private Button btnpdg;
    ObservableList<String> listcl = FXCollections.observableArrayList();
    ObservableList<String> listm = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
//        List<String> listclass ;  
//        public void LoadClasses(){
//        listclass = EnseignantCRUD.getInstance().displayClass(Integer.parseInt(ConnectedUser.getId()));
//        cb.getItems().addAll(listclass);
//        System.out.println(listclass.toString());
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EnseignantAdminCRUD clCRUD = new EnseignantAdminCRUD();
        ClasseAdminCRUD mCRUD = new ClasseAdminCRUD();
        listcl=clCRUD.displayAlll();
        listm=mCRUD.displayAlll();

        
         for(String j : listm){
                 m11.getItems().add(j);
                 m12.getItems().add(j);
                 m31.getItems().add(j);
                 m41.getItems().add(j);
                 m51.getItems().add(j);
                 m61.getItems().add(j);
                 m22.getItems().add(j);
                 m32.getItems().add(j);
                 m42.getItems().add(j);
                 m52.getItems().add(j);
                 m21.getItems().add(j);
                 
             }
        
       for(String i : listcl){
                 drop_class.getItems().add(i);
             }
         //    drop_class.getSelectionModel().selectFirst();
             
             m11.setOnAction((e)->{
                 final String clll=m11.getValue();
                 t1.setText(clll);
             });
               m12.setOnAction((e)->{
                 final String clll=m12.getValue();
                 t2.setText(clll);
             });
                m21.setOnAction((e)->{
                 final String clll=m21.getValue();
                 t3.setText(clll);
             });
               m22.setOnAction((e)->{
                 final String clll=m22.getValue();
                 t4.setText(clll);
             });
                 m31.setOnAction((e)->{
                 final String clll=m31.getValue();
                 t5.setText(clll);
             });
               m32.setOnAction((e)->{
                 final String clll=m32.getValue();
                 t6.setText(clll);
             });
                  m41.setOnAction((e)->{
                 final String clll=m41.getValue();
                 t7.setText(clll);
             });
               m42.setOnAction((e)->{
                 final String clll=m42.getValue();
                 t8.setText(clll);
             });
                      m51.setOnAction((e)->{
                 final String clll=m51.getValue();
                 t9.setText(clll);
             });
               m52.setOnAction((e)->{
                 final String clll=m52.getValue();
                 t10.setText(clll);
             });
                      m61.setOnAction((e)->{
                 final String clll=m61.getValue();
                 t11.setText(clll);
             });
             
             
             
             
    }    
    


    @FXML
    private void generatepdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        Document document = new Document();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        PdfWriter.getInstance(document, new FileOutputStream("emploi du temps "+drop_class.getValue()+".pdf"));
        document.open();
        document.add(new Paragraph("Emploi du temps ",FontFactory.getFont(FontFactory.COURIER_BOLD,20,BaseColor.DARK_GRAY)));
        String b =drop_class.getValue();
       document.add(new Paragraph("Enseignant :      "+drop_class.getValue()));
                        
                        document.add(new Paragraph("Date : "+formatter.format(date)+"                                                                     Annee universitaire: 2021/2022"));
                        document.add(new Paragraph("  "));
                        document.add(new Paragraph("  "));
                        PdfPTable table = new PdfPTable(6);
                        table.setWidths(new int[]{50,70,70,5,70,70});
                        table.addCell("       ");
                        table.addCell("09H:00 - 10H:30");
                        table.addCell("10H:45 - 12H:15");
                        table.addCell("  ");
                        table.addCell("13H:30 - 15H:00");
                        table.addCell("15H:15 - 16H:45");

                            table.addCell("Lundi");
                            PdfPCell cell1=new PdfPCell(new Paragraph(m11.getValue()));
                            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell1.setColspan(2);
                            table.addCell(cell1);
                            
                            table.addCell("  ");
                            PdfPCell cell2=new PdfPCell(new Paragraph(m12.getValue()));
                            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell2.setColspan(2);
                            table.addCell(cell2);
                            
                            table.addCell("Mardi");
                            PdfPCell cell3=new PdfPCell(new Paragraph(m21.getValue()));
                            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell3.setColspan(2);
                            table.addCell(cell3);
                            
                            table.addCell("  ");
                            PdfPCell cell4=new PdfPCell(new Paragraph(m22.getValue()));
                            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell4.setColspan(2);
                            table.addCell(cell4);
                            
                            /////////////
                            table.addCell("Mercredi");
                            PdfPCell cell5=new PdfPCell(new Paragraph(m31.getValue()));
                            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell5.setColspan(2);
                            table.addCell(cell5);
                            
                            table.addCell("  ");
                            PdfPCell cell6=new PdfPCell(new Paragraph(m32.getValue()));
                            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell6.setColspan(2);
                            table.addCell(cell6);
                            
                            ///////////
                            table.addCell("jeudi");
                            PdfPCell cell7=new PdfPCell(new Paragraph(m41.getValue()));
                            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell7.setColspan(2);
                            table.addCell(cell7);
                            
                            table.addCell("  ");
                            PdfPCell cell8=new PdfPCell(new Paragraph(m42.getValue()));
                            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell8.setColspan(2);
                            table.addCell(cell8);
                            ///////////
                            table.addCell("Vendredi");
                            PdfPCell cell9=new PdfPCell(new Paragraph(m51.getValue()));
                            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell9.setColspan(2);
                            table.addCell(cell9);
                            
                            table.addCell("  ");
                            PdfPCell cell10=new PdfPCell(new Paragraph(m52.getValue()));
                            cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell10.setColspan(2);
                            table.addCell(cell10);
                           ///////////
                            table.addCell("Samedi");
                            PdfPCell cell99=new PdfPCell(new Paragraph(m61.getValue()));
                            cell99.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell99.setColspan(2);
                            table.addCell(cell99);
                            
                            table.addCell("  ");
                            PdfPCell cell999=new PdfPCell(new Paragraph("              "));
                            cell999.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell999.setColspan(2);
                            table.addCell(cell999);
                            
                            table.addCell("  ");
                             table.addCell("  ");
                            
                            
                        document.add(table);
                        
                        DecimalFormat df = new DecimalFormat("########.00");
                        
          
        document.close();
                            handleopenfile(event);
        JOptionPane.showMessageDialog(null, "L'emploi du temps a été bien telehargé");
    }
    
         public void handleopenfile(final ActionEvent e) throws SQLException {
             SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
             Date semaine = new Date(System.currentTimeMillis());          
             Stage window = (Stage)((Node)e.getSource()).getScene().getWindow() ; 
                    File file = new File("emploi du temps "+drop_class.getValue()+".pdf");
                    if (file != null) {
                        uploadfile(file,semaine);
  
                    }
                }
       
         
         
         
         private void uploadfile(File file ,Date semaine) {
            
        try {
            EnseignantCRUD.getInstance().uploadfileemp(file,semaine);
        } catch (Exception ex) {
            Logger.getLogger(
                guiGestionCoursController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
           

         
    }
    
    
@FXML
    void handlegofirst(ActionEvent event) {
 try {
            
            EnseignantCRUD prc = new EnseignantCRUD();  
            prc.gofirst(event); 
 }
            
        catch (Exception ex) 
        {
            Logger.getLogger(guiGestionAssiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

    


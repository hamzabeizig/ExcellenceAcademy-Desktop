/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;

import com.itextpdf.text.Document;
import pi.entities.Assiduite;
import pi.entities.Classe;
import pi.entities.Enseignant;
import pi.entities.Note;
import pi.entities.Wrapper1;
import pi.entities.Utilisateur;
import pi.entities.Wrapper2;
import pi.entities.Wrapper3;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import pi.gui.Pi;
import pi.tools.MyConnection;
/**
 *
 * @author ASUS
 */


public class EnseignantCRUD {
    
       private static EnseignantCRUD instance;
        Connection cnx;
        Statement ste;
        ResultSet rs;
                             private Desktop desktop = Desktop.getDesktop();
                             

    private PreparedStatement ste1;

    public EnseignantCRUD () {
        cnx = MyConnection.getInstance().getConnection();
         try {
             ste = cnx.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
     public static EnseignantCRUD getInstance(){
        if(instance==null) 
           instance=new EnseignantCRUD();
           return instance;
    }
     
    
    public void Connect(TextField t1,TextField t2,ActionEvent event) throws IOException{
        String req ="select * from user where user_name='"+t1.getText()+"' and Mdp='"+t2.getText()+"'and (UPPER(role)='ENSEIGNANT' or UPPER(role)='ADMIN' )";
        //String req2 ="select * from user where user_name";
        
        try {
           
            rs=ste.executeQuery(req);
            if (rs.next())
                       {  if ((rs.getString("role").equals("Enseignant")) || (rs.getString("role").equals("ENSEIGNANT")) )
                       {  
                          // JOptionPane.showMessageDialog(null,"Bienvenue à votre espace enseignant");

//                            l.changeScene("guiGestionProfil.fxml");
                Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionCours.fxml"));
                Scene scene2 = new Scene(root2);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
                       } 
                       
                        if ((rs.getString("role").equals("Administrateur")) || (rs.getString("role").equals("ADMIN")) )  
                       {  JOptionPane.showMessageDialog(null,"Bienvenue à votre espace gestion des enseignants"); 
                             Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionEmploiAdmin.fxml"));
                Scene scene2 = new Scene(root2);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
                       }
                       
                       }
            else 
                       {
                                   JOptionPane.showMessageDialog(null, "Verifier votre mot de passe ou bien votre nom d'utlisateur ");  
         
                       }
           
        
        } 
        catch (SQLException ex) {
            System.out.println("Verifier vos coordonées");
            JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
            
        }
        
        
        
      
    }
    
      public Enseignant getSessionData(Enseignant p,ActionEvent e) throws IOException{
        String req ="select * from user where user_name='"+p.getLogin()+"'";
        try {
           
            rs=ste.executeQuery(req);
           if (rs.next())
                       {    
                      p.setId(rs.getString(1)); 
                      p.setLogin(rs.getString(2));
                      p.setNom(rs.getString(3)); 
                      p.setPrenom(rs.getString(4)); 
                      p.setMail(rs.getString(5)); 
                      p.setDdn(rs.getDate(6)); 
                      p.setRole(rs.getString(7)); 
                      p.setPassword(rs.getString(8)); 

                       }
          
                         System.out.println(p.toString());

        } 
        catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
            
        }

        return p ; 
    }
      
       public Enseignant getSessionData2(Enseignant p,ActionEvent e) throws IOException{
        
      String req2 ="select * from enseignant where id_user='"+p.getId()+"'";
      
         try {
           
            rs=ste.executeQuery(req2);
           if (rs.next())
                       {       

                      p.setId_matiere(rs.getString(1)); 
                      p.setNom_departement(rs.getString(2));
                      p.setId_emp(rs.getString(4));
                       }
             System.out.println(p.toString());
          
        } 
        catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
            
        }
         

        return p ; 
    }
     
//     public ObservableList<Object> displayAll() 
//     {
//        String req="select * from user where role = 'etudiant' ";
//        ObservableList<Object> list=FXCollections.observableArrayList();       
//        
//        try {
//            rs=ste.executeQuery(req);
//            while(rs.next()){
//                Utilisateur p=new Utilisateur();
//                p.setIdi(rs.getString(1)); 
//                p.setLogini(rs.getString(2));
//                p.setPasswordi(rs.getString(3));
//                p.setNomi(rs.getString(4));
//                p.setPrenomi(rs.getString(5));
//                p.setMaili(rs.getString(6));
//                p.setDdni(rs.getDate(7));
//                p.setRolei(rs.getString(8));
//               
//                list.add(p);
//                 System.out.println(list.toString());
//            }
            
//        } catch (SQLException ex) {
//            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

   public ObservableList<Wrapper1> displayAlll(String cbvalue,Enseignant e) 
     {
                // String req="select user.id_user,user.nom,user.prenom, note.note FROM user INNER JOIN note ON user.id_user = note.id_user";

        String req="select user.id_user,user.nom,user.prenom, note.note FROM user INNER JOIN note ON user.id_user = note.id_user INNER JOIN etudiant ON etudiant.id_classe = (SELECT classe.id_classe from classe where classe.nom_classe ='"+cbvalue+"') and user.id_user = etudiant.id_user and note.id_matiere='"+e.getId_matiere()+"'";
        ObservableList<Wrapper1> list=FXCollections.observableArrayList();       
         float n;
       try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Wrapper1 p=new Wrapper1();
                p.setId_user(rs.getInt(1)); 
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setNote(rs.getFloat(4));
                list.add(p);
            System.out.println(list.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
    public void Edit (Note p , String i,Enseignant en){ 
        
                try{ 
        

                    { String qry = "UPDATE Note SET note = '"+p.getNote()+"' WHERE id_user = '"+i+"' and note.id_matiere='"+en.getId_matiere()+"'";
                  ste.executeUpdate(qry);}}
                catch(SQLException e){}
    }
    
     public void Edit2 (Assiduite p , String i,Enseignant en ,DatePicker d){ 
                java.sql.Date b =java.sql.Date.valueOf(d.getValue()) ; 

                try{ 
    
                    { String qry = "UPDATE assiduite SET valeur = '"+p.getValeur()+"' WHERE id_user = '"+i+"'and id_matiere='"+en.getId_matiere()+"' and date = '"+b+"'";
                   String qry2 = "UPDATE assiduite SET date = '"+p.getDate()+"' WHERE id_user = '"+i+"'and id_matiere='"+en.getId_matiere()+"'and date = '"+b+"'";
                   ste.executeUpdate(qry2);
                  ste.executeUpdate(qry);}}
                catch(SQLException e){System.out.println("service.UtilisateurCRUD.Edit2()");}
    }
     
       public void EditProfil (Enseignant en, TextField nomf, TextField prenomf, TextField loginf,DatePicker datef ){ 
                  java.sql.Date b =java.sql.Date.valueOf(datef.getValue()) ; 

                try{ 
                    {
                    String qry1 = "UPDATE user SET nom = '"+nomf.getText()+"' WHERE id_user = '"+en.getId()+"'";
                    String qry2 = "UPDATE user SET prenom = '"+prenomf.getText()+"' WHERE id_user = '"+en.getId()+"'";
                    String qry3 = "UPDATE user SET user_name = '"+loginf.getText()+"' WHERE id_user = '"+en.getId()+"'";
                    String qry4 = "UPDATE user SET date_de_naissance = '"+b+"' WHERE id_user = '"+en.getId()+"'";
                    ste.executeUpdate(qry1);
                    ste.executeUpdate(qry2);
                    ste.executeUpdate(qry3);
                    ste.executeUpdate(qry4);
                         
                    
                    }}
                catch(SQLException e){System.out.println("service.UtilisateurCRUD.Edit2()");}
    }
     
       public List getstudents (String cbvalue){ 
        List<Integer> l = new ArrayList<Integer>(); 
        String req2 = "SELECT user.id_user from user INNER JOIN etudiant on etudiant.id_user = user.id_user where etudiant.id_classe = (SELECT classe.id_classe FROM classe WHERE classe.nom_classe='"+cbvalue+"')"; 
      //  String req3 = "insert into assiduite (id_matiere,date,id_user) values ('"+e.getId_matiere()+"','"+b+"',?) ";

         try {
           ste=cnx.prepareStatement(req2);
            rs=ste.executeQuery(req2);
            while(rs.next()){
                l.add(rs.getInt("id_user"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
           System.out.println("list get users "+l.toString());
        return l;
    }
       
        public List getassi (DatePicker d){ 
        java.sql.Date b =java.sql.Date.valueOf(d.getValue()) ; 
        List<Integer> l = new ArrayList<Integer>(); 
        String req2 = "SELECT id_user from assiduite WHERE date='"+b+"'"; 
         try {
            rs=ste.executeQuery(req2);
            while(rs.next()){
                l.add(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
           System.out.println("list get assi "+l.toString());
        return l;
    }
        
      public void makeassi (String cbvalue, Enseignant e,DatePicker d){ 
          String classassi ; 
          int i ; 
          List<Integer> l ; 
          List<Integer> l2 ; 
          l=getstudents(cbvalue);
          l2=getassi(d); 
          java.sql.Date b =java.sql.Date.valueOf(d.getValue()) ; 
 // error lenna 
            for( i=0 ; i<l.size();i++)  {
                          String req3 = "insert into assiduite (id_matiere,date,id_user) values ('"+e.getId_matiere()+"','"+b+"','"+l.get(i)+"')";
                          // where not exists(select * from assiduite where assiduite.id_user = 4 )
                          try{      
                          if (!(l2.containsAll(l))) 
                     ste.executeUpdate(req3);
                    
                    }
             catch(SQLException ex){ Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);}
          }
    }

//   public ObservableList<String> afficher() throws SQLException{
//       ObservableList<String> ls = FXCollections.observableArrayList();
//       String req ="SELECT user.id_user,user.nom,user.prenom, note.note FROM user INNER JOIN note ON user.id_user = note.id_user";
//       ste=cnx.prepareStatement(req);
//       rs =ste.executeQuery(req);
//       while (rs.next())
//       {
//           Utilisateur e= new Utilisateur();
//           
//       }
//             
//   }
//    user.id_user,user.nom,user.prenom, note.note FROM user INNER JOIN note ON user.id_user = note.id_user
    public boolean update(Utilisateur p) {
        String qry = "UPDATE user SET user_name = '"+p.getLogin()+"', prenom = '"+p.getPrenom()+"', nom = '"+p.getNom()+"',email = '"+p.getMail()+"',date_de_naissance = '"+p.getDdn()+"', role = '"+p.getRole()+"' WHERE id_user = "+p.getId();
        
        try {
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void editpass(Enseignant p ,String pass) {
        String qry = "UPDATE user SET Mdp = '"+pass+"' where user.id_user='"+p.getId()+"'";
        
        try {
            ste.executeUpdate(qry);
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public String verifypass(Enseignant p ,String pass) {
        String qry = "select Mdp from user where user.id_user='"+p.getId()+"'";
        String oldpass="";
        try {
            rs=ste.executeQuery(qry);
            while (rs.next())
            { oldpass = rs.getString("Mdp")  ; }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oldpass ;
    }
    
      public List<String> displayClass(int id_ens ) 
     {
                // String req="select user.id_user,user.nom,user.prenom, note.note FROM user INNER JOIN note ON user.id_user = note.id_user";
         
        String req="select classe.nom_classe FROM classe INNER JOIN ens_classe ON ens_classe.id_classe = classe.id_classe WHERE ens_classe.id_user='"+id_ens+"'";
        List<String> list=new ArrayList<String>();       
       try {
           ste=cnx.prepareStatement(req);
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getString("nom_classe"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      
       public ObservableList<Wrapper2> displayAlll2(String cbvalue,Enseignant e,DatePicker d) 
     {
        java.sql.Date b =java.sql.Date.valueOf(d.getValue()) ; 
               
        String req="select user.id_user,user.nom,user.prenom, assiduite.valeur FROM user INNER JOIN assiduite ON user.id_user = assiduite.id_user INNER JOIN etudiant ON etudiant.id_classe = (SELECT classe.id_classe from classe where classe.nom_classe ='"+cbvalue+"') and user.id_user = etudiant.id_user and assiduite.id_matiere='"+e.getId_matiere()+"' and assiduite.date = '"+b+"'";
        
        ObservableList<Wrapper2> list=FXCollections.observableArrayList();       
         float n;      
       
         try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Wrapper2 p=new Wrapper2();
                p.setId_user(rs.getInt(1)); 
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setAssi(rs.getString(4));
                list.add(p);
            System.out.println(list.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
        public ObservableList<Wrapper2> displayAlll3(String cbvalue,Enseignant e,DatePicker d) 
     {
        java.sql.Date b =java.sql.Date.valueOf(d.getValue()) ; 
        String req="select user.id_user,user.nom,user.prenom, assiduite.valeur FROM user INNER JOIN assiduite ON user.id_user = assiduite.id_user and assiduite.date = '"+b+"'INNER JOIN etudiant ON etudiant.id_classe = (SELECT classe.id_classe from classe where classe.nom_classe ='"+cbvalue+"') and user.id_user = etudiant.id_user and assiduite.id_matiere='"+e.getId_matiere()+"'";
        ObservableList<Wrapper2> list=FXCollections.observableArrayList();       
         float n;
       try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Wrapper2 p=new Wrapper2();
                p.setId_user(rs.getInt(1)); 
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setAssi(rs.getString(4));
                list.add(p);
            System.out.println(list.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
         public ObservableList<Wrapper3> displayAlll4(String cbvalue,Enseignant e) throws SQLException 
     {
        String req0 = "select classe.id_classe from classe where nom_classe= '"+cbvalue+"' ";
      int idclass=0 ; 
      rs= ste.executeQuery(req0);
             try {
                 while(rs.next())
                 { 
                     idclass=rs.getInt("id_classe");
                         }}
             catch (SQLException ex) {Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);}       
        String req="select nom_cours from cour where id_user='"+e.getId()+"' and id_classe='"+idclass+"'";
        
        ObservableList<Wrapper3> list=FXCollections.observableArrayList();       
         float n;      

         try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Wrapper3 p = new Wrapper3();
                p.setNom_cours(rs.getString("nom_cours"));            
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
                    System.out.println(list.toString());

        return list;
    }
        
        
          public ObservableList<Wrapper3> displayAlll5(Enseignant e) throws SQLException 
     {
               
        String req="select semaine from emplois_dt where id_emp ='"+e.getId_emp()+"'";
                            System.out.println(e.getId_emp());

        ObservableList<Wrapper3> list=FXCollections.observableArrayList();       
         float n;      

         try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Wrapper3 p = new Wrapper3();
                p.setNom_cours(rs.getDate("semaine").toString());            
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
         
         
         
         public void uploadfile (File f , Enseignant e,String cb) throws IOException, SQLException{ 
      String filename = f.getName() ; 
      String im= e.getId_matiere() ; 
      String req0 = "select classe.id_classe from classe where nom_classe= '"+cb+"' limit 1";
      int idclass=0 ; 
      rs= ste.executeQuery(req0);
             try {
                 while(rs.next())
                 { 
                     idclass=rs.getInt("id_classe");
                         }}
             catch (SQLException ex) {Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);}
//            int idclass = rs.getInt("id_classe");
//            int idclass = rs.getInt("id_classe");
//            int idclass = rs.getInt("id_classe");
//            int idclass = rs.getInt("id_classe");
      
             

      String req = "insert into cour (id_matiere,cours,nom_cours,id_user,id_classe) values (?,?,?,?,?)";
      PreparedStatement pstmt = cnx.prepareStatement(req);
      FileInputStream fin = new FileInputStream(f.getAbsolutePath());
      pstmt.setInt(1, Integer.parseInt(im));
      pstmt.setBinaryStream(2, fin);
      pstmt.setString(3,filename);
      pstmt.setInt(4,Integer.parseInt(e.getId()));
      pstmt.setInt(5,idclass);
      pstmt.execute();
      System.out.println("cours inserée .....");
           
      
          }
         
         
         
     public void uploadfileemp (File f,Date semaine) throws IOException, SQLException{ 
    

      String req = "insert into emplois_dt (emplois,semaine) values (?,?)";
      PreparedStatement pstmt = cnx.prepareStatement(req);
      FileInputStream fin = new FileInputStream(f.getAbsolutePath());
      pstmt.setBinaryStream(1, fin);
      pstmt.setDate(2, semaine);
      pstmt.execute();
      System.out.println("emploi inserted .....");
           
          }
     
     
     
     
         
         public void downloadfile (String cb , Enseignant e, String namecours ) throws SQLException, FileNotFoundException, IOException{ 
            
              String req0 = "select classe.id_classe from classe where nom_classe= '"+cb+"' limit 1";
      int idclass=0 ; 
      rs= ste.executeQuery(req0);
             try {
                 while(rs.next())
                 { 
                     idclass=rs.getInt("id_classe");
                         }}
             catch (SQLException ex) {Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);} 
             
             
             String req = "select cours from cour where id_user='"+e.getId()+"' and id_classe= '"+idclass+"' and nom_cours='"+namecours+"'";
            ste = cnx.prepareStatement(req);
            rs = ste.executeQuery(req);

        File someFile = new File("C:\\Users\\ASUS\\Desktop\\CoursFile.pdf");
        
       while (rs.next())
        {    Blob blob = rs.getBlob(1); 
             InputStream in = blob.getBinaryStream();
             OutputStream out = new FileOutputStream(someFile);
             byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
             int len = 0;

             while ((len = in.read(buff)) != -1) {
                 out.write(buff, 0, len);
        }
           in.close();
           out.close();
        }
             System.out.println("telechargee");
               desktop.open(someFile);

         }
         
         
          public void downloadfileemp (Enseignant e) throws SQLException, FileNotFoundException, IOException{ 
            
             
             String req = "select emplois from emplois_dt where id_emp='"+e.getId_emp()+"'";
            ste = cnx.prepareStatement(req);
            rs = ste.executeQuery(req);

        File someFile = new File("C:\\Users\\ASUS\\Desktop\\CoursFile2.pdf");
        
       while (rs.next())
        {    Blob blob = rs.getBlob(1); 
             InputStream in = blob.getBinaryStream();
             OutputStream out = new FileOutputStream(someFile);
             byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
             int len = 0;

             while ((len = in.read(buff)) != -1) {
                 out.write(buff, 0, len);
        }
           in.close();
           out.close();
        }
             
             System.out.println("telechargee");
             desktop.open(someFile);

         }
         
         
          public void deletecours (String cb,Enseignant e,String namecours) throws SQLException, FileNotFoundException, IOException
          { 
              String req0 = "select classe.id_classe from classe where nom_classe= '"+cb+"' limit 1";
      int idclass=0 ; 
      rs= ste.executeQuery(req0);
             try {
                 while(rs.next())
                 { 
                     idclass=rs.getInt("id_classe");
                         }}
             catch (SQLException ex) {Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);} 
              
            String req = "delete from cour where id_user='"+e.getId()+"' and id_classe= '"+idclass+"' and nom_cours='"+namecours+"'";
           try{  ste.executeUpdate(req)  ; }
          catch (SQLException ex) {Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);} 
          
            
         }
        
          public List<String> getMails(String cb) throws SQLException 
     {
                   String req0 = "select classe.id_classe from classe where nom_classe= '"+cb+"' limit 1";
      int idclass=0 ; 
      rs= ste.executeQuery(req0);
             try {
                 while(rs.next())
                 { 
                     idclass=rs.getInt("id_classe");
                         }}
             catch (SQLException ex) {Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);} 
         
         
        String req="select user.email FROM user INNER JOIN etudiant ON etudiant.id_user = user.id_user WHERE etudiant.id_classe='"+idclass+"'";
        List<String> list=new ArrayList<String>();       
       try {
           ste=cnx.prepareStatement(req);
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getString("email"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnseignantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
          
          
          public static void sendMailrefuser(String recepient,Enseignant e, String filename) throws Exception {
       System.out.println("Preparing to send email");
       Properties properties = new Properties();

       //Enable authentication
       properties.put("mail.smtp.auth", "true");
       //Set TLS encryption enabled
       properties.put("mail.smtp.starttls.enable", "true");
       //Set SMTP host
       properties.put("mail.smtp.host", "smtp.gmail.com");
       //Set smtp port
       properties.put("mail.smtp.port", "587");
       
       properties.put("mail.smtp.ssl.trust", "*");

       //Your gmail address
       String myAccountEmail = "exellenceacademy.cours@gmail.com";
       //Your gmail password
       String password = "hamza123456789";

       //Create a session with account credentials
       Session session = Session.getInstance(properties, new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myAccountEmail, password);
           }
       });
       
       //Prepare email message
       Message message = prepareMessagerefuser(session, myAccountEmail,recepient,e,filename);

       //Send mail
       Transport.send(message);
       System.out.println("Message sent successfully");
   }

   private static Message prepareMessagerefuser(Session session, String myAccountEmail,String recepient,Enseignant e, String filename) {
       try {
    	   
    	   Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(myAccountEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
           message.setSubject("Nouveau support du cours");
           message.setText("Bonjour, On vous informe que (Monsieur/Madame)"+e.getNom()+" "+e.getPrenom()+" à partager un nouveau support du cours appelée "+filename+" \n Pour consulter ce dernier veuillez accéder a votre espace etudiant");

           return message;

          

          
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return null;
   }
          
          
          
     
   
   
          
          
        
       public void  goAssi(ActionEvent event) throws IOException {
       Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionAssi.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
       
        public void  gonote(ActionEvent event) throws IOException {
       Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionNote.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
         public void  goprofil(ActionEvent event) throws IOException {
       Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionProfil.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
         public void  gofirst(ActionEvent event) throws IOException {
       Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/authentification.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
         
          public void  goHistoriqueAssi(ActionEvent event) throws IOException {
                Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiHistoriqueAssi.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
     public void  goCours(ActionEvent event) throws IOException {
                Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionCours.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
   
     public void  gopass(ActionEvent event) throws IOException {
                Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionPass.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
     
      public void  goemp(ActionEvent event) throws IOException {
                Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionEmploiAdmin.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
       public void  goemploi(ActionEvent event) throws IOException {
                Parent root2 = FXMLLoader.load(getClass().getResource("/pi/gui/guiGestionEmploi.fxml"));
                Scene scene2 = new Scene(root2);
                 
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
                window.setScene(scene2);
                window.show();
       }
     
}


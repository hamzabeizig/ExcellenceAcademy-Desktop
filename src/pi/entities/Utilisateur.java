/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author 21655
 */
public class Utilisateur {
    
   private String id;
   private String login;
   private String nom;
   private String prenom;
   private String mail;
   private Date ddn;
   private String role;
   private String mdp;
   
   
   private SimpleStringProperty idi;
   private SimpleStringProperty logini;
   private SimpleStringProperty nomi;
   private SimpleStringProperty prenomi;
   private SimpleStringProperty maili;
   private SimpleStringProperty ddni;
   private SimpleStringProperty rolei;
   private SimpleStringProperty mdpi;

    public Utilisateur(String idoo, String login, String nom, String prenom, String mail, Date ddn, String role, String mdp) {
        this.idi = new SimpleStringProperty(idoo);
        this.logini = new SimpleStringProperty(login);
        this.login=login; 
        this.nom=nom;
        this.prenom=prenom;
        this.mail=mail;
        this.mdp=mdp;
        this.id=id;
        this.nomi = new SimpleStringProperty(nom);
        this.prenomi =new SimpleStringProperty(prenom);
        this.maili =new SimpleStringProperty(mail);
        this.ddni = new SimpleStringProperty(ddn.toString());
        this.ddn=ddn;
        this.rolei = new SimpleStringProperty(role);
        this.mdpi = new SimpleStringProperty(mdp);
        this.mdp=mdp;
        
    } 
    public Utilisateur() {
        
    }

    public Utilisateur(String id, String mail) {
        this.id = id;
        this.mail = mail;
    }
    

  

    public String getId() {
        return idi.get();
    }
       public String getId1() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return logini.get();
    }
    public String getLogin1() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nomi.get();
    }
    public String getNom1() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenomi.get();
    }
     public String getPrenom1() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return maili.get();
    }

    public String getMail1() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDdn() {     
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public String getRole() {
        return rolei.get();
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", ddn=" + ddn + ", role=" + role + ", mdp=" + mdp + '}';
    }

    public String getMdp() {
        return mdp;
    }
    

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public SimpleStringProperty getMdpi() {
        return mdpi;
    }

    public void setMdpi(String mdp) {
        this.mdpi = new SimpleStringProperty(mdp);
    }

    public SimpleStringProperty getIdi() {
        return idi;
    }

    public void setIdi(String id) {
        this.idi = new SimpleStringProperty(id);
    }

    public SimpleStringProperty getLogini() {
        return logini;
    }

    public void setLogini(String login) {
        this.logini = new SimpleStringProperty(login);
    }

    public SimpleStringProperty getNomi() {
        return nomi;
    }

    public void setNomi(String nom) {
        this.nomi = new SimpleStringProperty(nom);
    }

    public SimpleStringProperty getPrenomi() {
        return prenomi;
    }

    public void setPrenomi(String prenom) {
        this.prenomi = new SimpleStringProperty(prenom);
    }

    public SimpleStringProperty getMaili() {
        return maili;
    }

    public void setMaili(String mail) {
        this.maili = new SimpleStringProperty(mail);
    }

    public SimpleStringProperty getDdni() {
        return ddni;
    }

    public void setDdni(Date ddn) {
        this.ddni = new SimpleStringProperty(ddn.toString());
    }

    public SimpleStringProperty getRolei() {
        return rolei;
    }

    public void setRolei(String role) {
        this.rolei = new SimpleStringProperty(role);
    }
   
    public Date getddn (Date date)
    {
        return date;
    }
    
}

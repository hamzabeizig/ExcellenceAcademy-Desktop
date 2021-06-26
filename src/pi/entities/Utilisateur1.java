/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;
import java.sql.Date;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author 21655
 */
public class Utilisateur1 {
    
    private String id;
   private String login;
   private String password;
   private String nom;
   private String prenom;
   private String mail;
   private Date ddn;
   private String role;

    public Utilisateur1() {
    }

    public Utilisateur1(String id, String login, String password, String nom, String prenom, String mail, Date ddn, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.ddn = ddn;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public Date getDdn() {
        return ddn;
    }

    public String getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", ddn=" + ddn + ", role=" + role + '}';
    }
   
   
}
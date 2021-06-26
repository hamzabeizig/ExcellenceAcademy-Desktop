/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Evenement {
   private String id;
   private String nom;
   private Date date;
   private String responsable;
   private String description;
   private String id_user;
   private String nbr_place;
   private String etat;

    public Evenement(String id, String nom, Date date, String responsable, String description, String id_user, String nbr_place, String etat) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.responsable = responsable;
        this.description = description;
        this.id_user = id_user;
        this.nbr_place = nbr_place;
        this.etat = etat;
    }

    public Evenement() {
    }

   

   

   

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDate() {
        return date;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getDescription() {
        return description;
    }

    public String getId_user() {
        return id_user;
    }

    public String getNbr_place() {
        return nbr_place;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setNbr_place(String nbr_place) {
        this.nbr_place = nbr_place;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
   
  
}

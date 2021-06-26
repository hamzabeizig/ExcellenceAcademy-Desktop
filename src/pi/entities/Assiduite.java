/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Assiduite {
    
       private int id_assiduite;
       private int  id_matiere;
       private Date  date;
       private int  id_user;
       private String  valeur;

    public Assiduite() {
    }

    public Assiduite(int id_assiduite, int id_matiere, Date date, int id_user, String valeur) {
        this.id_assiduite = id_assiduite;
        this.id_matiere = id_matiere;
        this.date = date;
        this.id_user = id_user;
        this.valeur = valeur;
    }

    public int getId_assiduite() {
        return id_assiduite;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public Date getDate() {
        return date;
    }

    public int getId_user() {
        return id_user;
    }

    public String getValeur() {
        return valeur;
    }

    public void setId_assiduite(int id_assiduite) {
        this.id_assiduite = id_assiduite;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Assiduite{" + "id_assiduite=" + id_assiduite + ", id_matiere=" + id_matiere + ", date=" + date + ", id_user=" + id_user + ", valeur=" + valeur + '}';
    }

}

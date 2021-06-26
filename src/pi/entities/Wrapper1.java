/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import pi.entities.Note;
import pi.entities.Utilisateur;

/**
 *
 * @author ASUS
 */
public class Wrapper1{
 private int id_user;
 private String nom;
  private String prenom;
 private Float note;


    public Wrapper1() {
    }

    public Wrapper1(int id_user, String nom, String prenom, Float note) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.note = note;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Float getNote() {
        return note;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Wrapper1{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", note=" + note + '}';
    }

   
 
 
}
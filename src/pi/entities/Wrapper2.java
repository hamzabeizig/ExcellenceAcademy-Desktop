/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;



/**
 *
 * @author ASUS
 */
public class Wrapper2{
 private int id_user;
 private String nom;
  private String prenom;
 private String assi;


    public Wrapper2() {
    }

    public Wrapper2(int id_user, String nom, String prenom, String assi) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.assi = assi;
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

    public String getAssi() {
        return assi;
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

    public void setAssi(String assi) {
        this.assi = assi;
    }

    @Override
    public String toString() {
        return "Wrapper2{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", assi=" + assi + '}';
    }
    
    
}
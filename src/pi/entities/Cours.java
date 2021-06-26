/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import java.io.File;

/**
 *
 * @author ASUS
 */
public class Cours {
    
    private int id_cours ;
    private File cours;
    private int id_matiere;
    private String nom_cours;
    private int id_user;
    private int id_classe;

    public Cours() {
    }

    public Cours(int id_cours, File cours, int id_matiere, String nom_cours, int id_user, int id_classe) {
        this.id_cours = id_cours;
        this.cours = cours;
        this.id_matiere = id_matiere;
        this.nom_cours = nom_cours;
        this.id_user = id_user;
        this.id_classe = id_classe;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public File getCours() {
        return cours;
    }

    public void setCours(File cours) {
        this.cours = cours;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    @Override
    public String toString() {
        return "Cours{" + "id_cours=" + id_cours + ", cours=" + cours + ", id_matiere=" + id_matiere + ", nom_cours=" + nom_cours + ", id_user=" + id_user + ", id_classe=" + id_classe + '}';
    }
    
    
}

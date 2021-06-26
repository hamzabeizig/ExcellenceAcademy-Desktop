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
public class Enseignant extends Utilisateur1{
    String id_matiere ; 
    String nom_departement; 
    String id_emp ;
    public Enseignant() {
    }

    public Enseignant(String id_matiere, String nom_departement, String id_emp, String id, String login, String password, String nom, String prenom, String mail, Date ddn, String role) {
        super(id, login, password, nom, prenom, mail, ddn, role);
        this.id_matiere = id_matiere;
        this.nom_departement = nom_departement;
        this.id_emp = id_emp;
    }

    

    public String getId_emp() {
        return id_emp;
    }

    public void setId_emp(String id_emp) {
        this.id_emp = id_emp;
    }

    

    public String getId_matiere() {
        return id_matiere;
    }

    public String getNom_departement() {
        return nom_departement;
    }

 

    public void setId_matiere(String id_matiere) {
        this.id_matiere = id_matiere;
    }

    public void setNom_departement(String nom_departement) {
        this.nom_departement = nom_departement;
    }

    @Override
    public String toString() {
        return "Enseignant{" + "id_matiere=" + id_matiere + ", nom_departement=" + nom_departement + ", id_emp=" + id_emp + '}';
    }

  

   

 


    

}

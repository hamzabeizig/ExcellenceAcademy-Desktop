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
public class Classe {
    
    private int id_classe;
    private String nom_classe;
    private int nbr_etudiant;
    private String nom_salle;
    private int id_emp;

    public Classe() {
    }

    public Classe(int id_classe, String nom_classe, int nbr_etudiant, String nom_salle, int id_emp) {
        this.id_classe = id_classe;
        this.nom_classe = nom_classe;
        this.nbr_etudiant = nbr_etudiant;
        this.nom_salle = nom_salle;
        this.id_emp = id_emp;
    }

    public int getId_classe() {
        return id_classe;
    }

    public String getNom_classe() {
        return nom_classe;
    }

    public int getNbr_etudiant() {
        return nbr_etudiant;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }

    public void setNbr_etudiant(int nbr_etudiant) {
        this.nbr_etudiant = nbr_etudiant;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    @Override
    public String toString() {
        return "Classe{" + "id_classe=" + id_classe + ", nom_classe=" + nom_classe + ", nbr_etudiant=" + nbr_etudiant + ", nom_salle=" + nom_salle + ", id_emp=" + id_emp + '}';
    }

    
        
}

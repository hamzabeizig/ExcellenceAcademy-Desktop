/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

/**
 *
 * @author 21655
 */
public class Departement {
    private int id ; 
    private String nom ; 
    private String nom_chef;

    public Departement(int id, String nom, String nom_chef) {
        this.id = id;
        this.nom = nom;
        this.nom_chef = nom_chef;
    }

    public Departement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom_chef() {
        return nom_chef;
    }

    public void setNom_chef(String nom_chef) {
        this.nom_chef = nom_chef;
    }

    @Override
    public String toString() {
        return "Departement{" + "id=" + id + ", nom=" + nom + ", nom_chef=" + nom_chef + '}';
    }
}

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
public class Wrapper3 {
    String nom_cours ; 

    public Wrapper3() {
    }

    public Wrapper3(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    @Override
    public String toString() {
        return "Wrapper3{" + "nom_cours=" + nom_cours + '}';
    }
    
}

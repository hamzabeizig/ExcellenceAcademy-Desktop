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
public class Matiere {
    private int id ; 
    private String nom ;
    private String coeff ;
    private String volume_h;

    public Matiere(int id, String nom, String coeff, String volume_h) {
        this.id = id;
        this.nom = nom;
        this.coeff = coeff;
        this.volume_h = volume_h;
    }

    public Matiere() {
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

    public String getCoeff() {
        return coeff;
    }

    public void setCoeff(String coeff) {
        this.coeff = coeff;
    }

    public String getVolume_h() {
        return volume_h;
    }

    public void setVolume_h(String volume_h) {
        this.volume_h = volume_h;
    }

    @Override
    public String toString() {
        return "Matiere{" + "id=" + id + ", nom=" + nom + ", coeff=" + coeff + ", volume_h=" + volume_h + '}';
    }
    
    
}

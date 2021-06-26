/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import java.time.LocalDate;

/**
 *
 * @author pc
 */
public class Reunion {
    private int id ; 
    private LocalDate date ; 
    private String departement ; 
    private String matiere ;
    private String Objectif ; 
    private String horaire ;

    public Reunion(int id, LocalDate date, String departement, String matiere, String Objectif,String horaire) {
        this.id = id;
        this.date = date;
        this.departement = departement;
        this.matiere = matiere;
        this.Objectif = Objectif;
        this.horaire =horaire ;
    }

    public Reunion() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate( LocalDate date) {
        this.date = date;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getObjectif() {
        return Objectif;
    }

    public void setObjectif(String Objectif) {
        this.Objectif = Objectif;
    }
    public String getHoraire(){
        return horaire;
    }
    public void setHoraire(String horaire){
        this.horaire=horaire;
    }

    @Override
    public String toString() {
        return "Reunion{" + "id=" + id + ", date=" + date + ", departement=" + departement + ", matiere=" + matiere + ", Objectif=" + Objectif + '}';
    }
    
}

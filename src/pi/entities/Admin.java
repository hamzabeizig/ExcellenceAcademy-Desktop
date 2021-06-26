/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import java.sql.Date;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WIKI
 */
public class Admin extends Utilisateur{
    
private String Id;

    public Admin(String Id, String idoo, String login, String nom, String prenom, String mail, Date ddn, String role, String mdp) {
        super(idoo, login, nom, prenom, mail, ddn, role, mdp);
        this.Id = Id;
    }

    public Admin(String Id) {
        this.Id = Id;
    }

    public Admin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "Admin{" + "Id=" + Id + '}';
    }
    

}
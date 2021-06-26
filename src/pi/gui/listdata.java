/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.entities.Evenement;
import pi.services.EvenementCRUD;
import pi.tools.MyConnection;

/**
 *
 * @author WIKI
 */
public class listdata {

    static ObservableList<Evenement> getadmin;
    
    private ObservableList<Evenement> admin=FXCollections.observableArrayList();

    public listdata() {
        
        
        EvenementCRUD pdao;
        pdao =EvenementCRUD.getInstance();
        admin= pdao.displayAll();
        System.out.println(admin);
    }
    
    /**
     *
     * @return
     */
    public  ObservableList<Evenement> getadmin(){
        return admin;
    }

    
}

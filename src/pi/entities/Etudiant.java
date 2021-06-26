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
public class Etudiant {
	private String niveau;	
       private String specialite;
       private int id_stage;	
       private int id_user;	
       private int id_emp;	

    public Etudiant(String niveau, String specialite, int id_stage, int id_user, int id_emp) {
        this.niveau = niveau;
        this.specialite = specialite;
        this.id_stage = id_stage;
        this.id_user = id_user;
        this.id_emp = id_emp;
    }

    }

   

   
   

       


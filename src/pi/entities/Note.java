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
public class Note {
    	private int id_note;
        private float  note;
        private int  id_matiere;
        private int id_user ; 

    public Note(int id_note, float note, int id_matiere, int id_user) {
        this.id_note = id_note;
        this.note = note;
        this.id_matiere = id_matiere;
        this.id_user = id_user;
    }

    public Note() {
    }

    
    public int getId_note() {
        return id_note;
    }

    public float getNote() {
        return note;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Note{" + "id_note=" + id_note + ", note=" + note + ", id_matiere=" + id_matiere + ", id_user=" + id_user + '}';
    }
        
        
          
}

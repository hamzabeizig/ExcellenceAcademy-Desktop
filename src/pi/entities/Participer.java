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
public class Participer 
{
          private int id;
    private String idevent;
    private String iduser;

    public Participer(int id, String idevent, String iduser) {
        this.id = id;
        this.idevent = idevent;
        this.iduser = iduser;
    }

    public Participer(String idevent, String iduser) {
        this.idevent = idevent;
        this.iduser = iduser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdevent() {
        return idevent;
    }

    public void setIdevent(String idevent) {
        this.idevent = idevent;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Participer{" + "id=" + id + ", idevent=" + idevent + ", iduser=" + iduser + '}';
    }
    
    
}

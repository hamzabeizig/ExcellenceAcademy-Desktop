package pi.entities;

import java.time.LocalDate;

public class Stages {
	private int id ; 
    private String societe ;
    private String email_societe ; 
    private String pays ;
    private LocalDate date_debut ;
    private LocalDate date_fin ;
    private String type_stage ;
	public Stages(int id, String societe, String email_societe, String pays, LocalDate date_debut, LocalDate date_fin,
			String type_stage) {
		super();
		this.id = id;
		this.societe = societe;
		this.email_societe = email_societe;
		this.pays = pays;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.type_stage = type_stage;
	}
	public Stages(String societe, String email_societe, String pays, LocalDate date_debut, LocalDate date_fin,
			String type_stage) {
		super();
		this.societe = societe;
		this.email_societe = email_societe;
		this.pays = pays;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.type_stage = type_stage;
	}
	
	public Stages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public String getEmail_societe() {
		return email_societe;
	}
	public void setEmail_societe(String email_societe) {
		this.email_societe = email_societe;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public LocalDate getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(LocalDate date_debut) {
		this.date_debut = date_debut;
	}
	public LocalDate getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(LocalDate date_fin) {
		this.date_fin = date_fin;
	}
	public String getType_stage() {
		return type_stage;
	}
	public void setType_stage(String type_stage) {
		this.type_stage = type_stage;
	}
	
    
    
    

   
}

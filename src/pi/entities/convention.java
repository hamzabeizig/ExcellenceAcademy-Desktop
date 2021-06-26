package pi.entities;

public class convention {
	
	private int id_conv ; 
	private String user_name ; 
	private String nom ; 
	private String prenom ; 
	private String email ; 
	private int id_user ;
	
	public convention( String user_name, String nom, String prenom, String email, int id_user) {
		super();
		this.user_name = user_name;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.id_user = id_user;
	}
	public convention(int id_conv, String user_name, String nom, String prenom, String email, int id_user) {
		super();
		this.id_conv = id_conv;
		this.user_name = user_name;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.id_user = id_user;
	}
	
	public int getId_conv() {
		return id_conv;
	}
	public void setId_conv(int id_conv) {
		this.id_conv = id_conv;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	
	
	
	
	
}
		
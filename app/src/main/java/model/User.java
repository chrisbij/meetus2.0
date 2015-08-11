package model;

public class User {
	
	
	private long id;
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private String email;
	
	
	public User(){
		
	}
	
	
	public Long getId(){
		
		return this.id;
	}
	
	
	public String getNom(){
		
		return this.nom;
	}
	
	
	public String getPrenom(){
		
		return this.prenom;
	}
	
	
	public String getMail(){
	
		return this.email;
	}
	
	
	public String getLogin(){
		
		return this.login;
	}
	
	
	public String getPassword(){
		
		return this.password;
	}
	
	
	
	public void setId(long id){
		this.id = id;
	}
	
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	
	
	public void setLogin(String login){
		this.login = login;
	}
	
	
	public void setPassword(String password){
		this.password = password;
	}
	
	
	public void setMail(String email){
		this.email = email;
	}
	

}

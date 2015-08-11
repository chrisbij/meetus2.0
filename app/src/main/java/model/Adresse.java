package model;

public class Adresse {

	
	private String nomRue;
	private String ville;
	private String codePostal;
	private String pays;
	
	
	public Adresse(){
		
	}
	
	
	public String getNomRue(){
		return this.nomRue;
	}
	
	public String getVille(){
		return this.ville;
	}
	
	public String getCodePostal(){
		return this.codePostal;
	}
	
	public String getPays(){
		return this.pays;
	}
	
	
	public void setNomRue(String rue){
		this.nomRue = rue;
	}
	
	public void setVille(String ville){
		this.ville = ville;
	}
	
	public void setCodePostal(String cp){
		this.codePostal = cp;
	}
	
	public void setPays(String pays){
		this.pays = pays;
	}
	
}

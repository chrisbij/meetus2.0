package model;

import java.util.Date;

public class Sortie {
	
	
	private long id;
	private String titreSortie;
	private Date dateDebut;
	private Date dateFin;
	private long idAdresse;
	
	
	public long getIdSortie(){
		return this.id;
	}
	
	public String getTitreSortie(){
		return this.titreSortie;
	}
	
	public Date getDateDebut(){
		return this.dateDebut;
	}
	
	public Date getDateFin(){
		return this.dateFin;
	}
	
	public long getIdAdresse(){
		return this.idAdresse;
	}
	
	
	
	public void setIdSortie(long id){
		this.id = id;
	}
	
	public void setTitreSortie(String titre){
		this.titreSortie = titre;
	}
	
	public void setDateDebut(Date dateDebut){
		this.dateDebut = dateDebut;
	}
	
	public void setDateFin(Date dateFin){
		this.dateFin = dateFin;
	}
	
	public void setIdAdresse(long idAdresse){
		this.idAdresse = idAdresse;
	}

}

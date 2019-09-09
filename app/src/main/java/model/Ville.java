package model;

/**
 * Created by bijou on 25/09/2015.
 */
public class Ville {
    private long id_ville;
    private String nom_ville;

    public long getId_ville(){
        return id_ville;
    }

    public void setId_ville(long id_ville) {
        this.id_ville = id_ville;
    }

    public String getNom_ville(){
        return nom_ville;
    }

    public void setNom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
    }

    public String toString(){
        return nom_ville;
    }
}

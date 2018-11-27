package restaurant;

public class Aliment {

    private String nom;
    private int quantite;
    private double prix;

    public String toString(){
        double inf = Double.POSITIVE_INFINITY;
        if(quantite!=(int) inf){ return ""+quantite+" "+nom+"(s) coûtant chacun "+prix+" €"; }
        else{ return "\u221E "+nom+"(s) coûtant chacun "+prix+" €"; }
    }

    //getters
    public String getNom(){return nom; }
    public int getQuantite(){ return quantite; }
    public double getPrix(){ return prix; }

    public void setQuantite(int quantite) { this.quantite = quantite; }

    //constructor
    public Aliment(String nom, int quantite, double prix){
        this.quantite = quantite;
        this.nom = nom;
        this.prix = prix;
    }

}
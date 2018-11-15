package restaurant;

public class Aliment {

    private String nom;
    private int quantite;
    private double prix;

    double inf = Double.POSITIVE_INFINITY;

    public String toString(){
        if(quantite!=(int)inf){ return ""+quantite+" "+nom+"(s) coûtant chacun "+prix+" €"; }
        else{ return "\u221E "+nom+"(s) coûtant chacun "+prix+" €"; }
    }


    //getters
    public String getNom(){return nom; }
    public int getQuantite(){ return quantite; }
    public double getPrix(){ return prix; }

    //setters
    public void setNom(String nom) { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

/*    public void setAliment(String nom, double prix, int quantite){
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }
*/

    //constructor
    public Aliment (String name, int quantity, double price){
        this.quantite = quantity;
        this.nom = name;
        this.prix = price;
    }

    public boolean verification_aliment_produit(Products products, String nom){
        boolean verif = true; /*vrai par défault */
        for (Aliment aliment_courant : products.getProductList()) {
            if(aliment_courant.getNom().toLowerCase().compareTo(nom.toLowerCase())==0){
                verif = false;} //si les chaînes sont identiques:
        }
        return verif;
    }

}

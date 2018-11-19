package restaurant;

class Aliment {

    private String nom;
    private int quantite;
    private double prix;

    private double inf = Double.POSITIVE_INFINITY;

    public String toString(){
        if(quantite!=(int)inf){ return ""+quantite+" "+nom+"(s) coûtant chacun "+prix+" €"; }
        else{ return "\u221E "+nom+"(s) coûtant chacun "+prix+" €"; }
    }


    //getters
    String getNom(){return nom; }
    int getQuantite(){ return quantite; }
    double getPrix(){ return prix; }


    void setQuantite(int quantite) { this.quantite = quantite; }


    //constructor
    Aliment(String name, int quantity, double price){
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

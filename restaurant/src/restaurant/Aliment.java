package restaurant;

class Aliment {

    private String nom;
    private int quantite;
    private double prix;

    public String toString(){
        double inf = Double.POSITIVE_INFINITY;
        if(quantite!=(int) inf){ return ""+quantite+" "+nom+"(s) coûtant chacun "+prix+" €"; }
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

}

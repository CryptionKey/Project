package restaurant;

import java.util.LinkedList;
import logger.Logger;
import logger.LoggerFactory;

public class Note {

    private String nom;
    private final LinkedList<Aliment> productList = new LinkedList<>();
    private Logger logger = LoggerFactory.getLogger("note");

    //constructor
    Note(String nom){
        this.nom = nom.toLowerCase();
    }


    //Pour retourner la liste
    LinkedList<Aliment> getProductList() {return productList;}


    //Pour récupérer le nom.
    String getNom(){
        return nom;
    }


    public String toString(){
        return ""+nom+":\n"+productList.toString()+"";
    }


    //Afficher la liste d'aliments de la note
    void afficherListe(){
        String message = "";//initilisation du message qui sera affiché
        for (Aliment aliment : this.productList) {
            message += "\t"+aliment+"\n";//Chaque aliment de la liste est ajouté au message
        }
        logger.info("OUTPUT", message);//On affiche le message entier
    }


    //Ajouter un aliment à la note
    public void add (Aliment aliment){
        productList.add(aliment);
    }


    //Calculer le prix total HT, c'est le prix affiché dans le menu
    private double prixHT(LinkedList<Aliment> productList){
        double total=0;
        for(Aliment aliment : productList){//il suffit de faire la somme du prix de chaque aliment selon la quantité
            total = total + aliment.getQuantite() * aliment.getPrix();
        }
        return total;
    }


    //Appliquer la TVA
    private double getTVA(double prixHT){
        return prixHT*0.1; //La TVA est fixé à 10%
    }


    //Afficher les produits enregistrés, total HT, TVA; total TTC
    void facture(int remise){
        logger.info("OUTPUT", "\nPrix de chaque produit hors-taxe :\n");
        this.afficherListe();
        double prixHT = prixHT(this.productList);
        String message = "Prix total hors-taxe : "+prixHT+"\n";
        double TVA = getTVA(prixHT);
        message += "TVA : "+TVA+"\nPrix taxes comprises : "+(TVA + prixHT)+"\n";
        if(remise == 1){//Si le vendeur a choisi de faire la remise de 10%
            logger.info("OUTPUT",message+"Prix après remise : "+((TVA + prixHT)-(TVA + prixHT)*0.1)+"\n");
        }
        else logger.info("OUTPUT",message); //s'il n'y a pas de remise
    }

}

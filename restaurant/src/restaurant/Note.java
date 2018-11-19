package restaurant;

import java.util.LinkedList;
import logger.Logger;
import logger.LoggerFactory;

public class Note {

    private String nom;
    private final LinkedList<Aliment> productList = new LinkedList<Aliment>();
    Logger logger = LoggerFactory.getLogger("player");

    //constructor
    public Note(String nom){
        this.nom = nom.toLowerCase();
    }


    public LinkedList<Aliment> getProductList() {return productList;}

    //Pour récupérer le nom
    public String getNom(){
        return nom;
    }


    public String toString(){
        return ""+nom+":\n"+productList.toString()+"";
    }


    //Afficher la liste d'aliments de la note
    public void afficherListe(){
        for (Aliment aliment : this.productList) {
            logger.info("", "\t"+aliment+"\n");
        }
    }

    //Ajouter un aliment à la note
    public void add (Aliment aliment){
        productList.add(aliment);
    }

    //Calculer le prix total HT
    public double prixHT(LinkedList<Aliment> productList){
        double total=0;
        for(Aliment aliment : productList){
            total = total + aliment.getQuantite() * aliment.getPrix();
        }
        return total;
    }

    //Appliquer la TVA
    public double getTVA(double prixHT){
        double TVA = prixHT*0.1;
        return TVA;
    }

    //Afficher les produits enregistrés, total HT, TVA; total TTC
    public void facture(int remise){
        logger.info("", "\nPrix de chaque produit hors-taxe :\n");
        this.afficherListe();
        double prixHT = prixHT(this.productList);
        logger.info("","Prix total hors-taxe : "+prixHT+"\n");
        double TVA = getTVA(prixHT);
        logger.info("","TVA : "+TVA+"\n");
        logger.info("","Prix taxes comprises : "+(TVA + prixHT)+"\n");
        if(remise == 1){
            logger.info("","Prix après remise : "+((TVA + prixHT)-(TVA + prixHT)*0.1)+"\n");
        }
    }

}

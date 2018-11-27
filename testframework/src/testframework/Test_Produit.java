package testframework;

import logger.Logger;
import logger.LoggerFactory;
import restaurant.Aliment;
import restaurant.Produits;

import java.util.*;

public class Test_Produit {

    private Produits produits = new Produits();
    private Logger logger = LoggerFactory.getLogger("Test_Index");
    private final LinkedList<Aliment> productList = new LinkedList<>();
    private Aliment burger = new Aliment("burger", 10, 10);
    private Aliment bagel = new Aliment("bagel", 10, 10);
    private Aliment hot_dog = new Aliment("hot_dog", 10, 10);


    //Nous créons et remplisons une liste de produit avec trois aliments
    private void remplir_liste(LinkedList productList){
        productList.add(burger);
        productList.add(bagel);
        productList.add(hot_dog);
    }


    //Afficher la liste des aliments
    public void afficherListe(){
        StringBuilder liste = new StringBuilder("\n");
        for (Aliment aliment : this.productList) { liste.append("\t").append(aliment).append("\n");  }
        logger.info("OUTPUT", liste+"\n");
    }



    //Méthode qui teste la récupération de l'index d'un aliment
    public void test_getIndexAliment(){
        this.remplir_liste(productList);
        int index = produits.getIndexAliment(burger.getNom());
        if (index != 0) {
            throw new AssertionError("ca devrait etre 0 mais c'est " + index);
        } else logger.info("PROGRAM","Bon index trouvé\n");
    }


    //Méthode qui teste l'augmenatation de stock
    public void test_augmenter_stock(Aliment aliment){
        int quantite = produits.augmenter_stock(aliment);
    }

    /* private int augmenter_stock(Aliment aliment){
        int quantite = (int)Affichage.verification_nombre("entier", Affichage.output_quantite, Affichage.input_quantite);
        quantite += aliment.getQuantite();
        logger.info("INPUT","Nouvelle quantité :"+quantite+", de l'aliment :"+aliment.getNom() );
        return quantite;
    }*/



    //Méthode qui teste la vérification de l'existence d'un aliment dans une liste
    public void test_verif_aliment(){
        afficherListe();
        Aliment burger = produits.verification_aliment_existant("burger", true);
        Aliment coca = produits.verification_aliment_existant("coca",true);
    }


   /* public Aliment verification_aliment_existant(String nom_aliment, boolean afficher_message){
        Aliment aliment = null;
        for (Aliment aliment_courant : productList) {
            if(aliment_courant.getNom().toLowerCase().compareTo(nom_aliment.toLowerCase())==0){
                aliment = aliment_courant;} //si les chaînes sont identiques, l'aliment existe
        }
        if(aliment == null && afficher_message){
            logger.error("OUTPUT","Ce produit n'est pas proposé à la vente.\n");}
        return aliment;
    }*/


}

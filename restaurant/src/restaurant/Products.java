package restaurant;
import java.util.LinkedList;
import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;

public class Products {

    private final LinkedList<Aliment> productList = new LinkedList<>();
    private Logger logger = LoggerFactory.getLogger("product");
    private Scanner scan = new Scanner(System.in);


    public String toString() {
        return productList.toString();
    }

    LinkedList<Aliment> getProductList() { return productList; }

    //Afficher la liste des aliments
    void afficherListe(){
        for (Aliment aliment : this.productList) {
            logger.info("", "\t"+aliment+"\n");
        }
    }

    //Ajouter un aliment à la vente
    private void add (Aliment aliment){
        productList.add(aliment);
    }


    //Créer puis ajouter un aliment à la vente
    private void create_add(String nom, int quantite, double prix){
        Aliment aliment = new Aliment(nom, quantite, prix);
        this.add(aliment);
    }


    //Initialiser les produits mis en vente
    void init() {
        double inf = Double.POSITIVE_INFINITY;
        create_add("bagel", 20, 8);
        create_add("burger", 10, 10);
        create_add("smoothie", 5, 5);
        create_add("café", (int)inf, 2);
    }


    boolean verification_aliment_existant(String nom){
        boolean verif = false; /*l'aliment n'existe pas par défault*/
        for (Aliment aliment_courant : productList) {
            if(aliment_courant.getNom().toLowerCase().compareTo(nom.toLowerCase())==0){
                verif = true;} //si les chaînes sont identiques, l'aliment existe
        }
        return verif;
    }

    int getIndexAliment(String nom_aliment){
        int compteur = 0, index=0;
        for (Aliment aliment_courant : productList) {
            if(aliment_courant.getNom().toLowerCase().compareTo(nom_aliment.toLowerCase())==0){
                index=compteur;} //si les chaînes sont identiques, l'aliment existe
            compteur++;
        }
        return index;
    }

    void ajouter_produit(){
        logger.info("","\nAjoutez un produit à la vente\nEntrez le nom du produit: ");
        String nom = scan.next();
        boolean test_aliment_existant = verification_aliment_existant(nom); //aliment.verification(products); /*vrai par défault */

        if(!test_aliment_existant) { //si l'aliment n'existe pas
            logger.info("","Entrez la quantité du produit: ");
            int quantite = (int)verif_chiffre();
            logger.info("","Entrez le prix du produit: ");
            double prix = verif_chiffre();
            create_add(nom, quantite, prix);
        }
        else{logger.info("", "\nCe produit existe déjà\n");}
    }


    private double verif_chiffre() {
        Scanner scan = new Scanner(System.in);
        double choix = 0 ; String str;

        do {
            str = (scan.nextLine());
            try {
                choix = Integer.parseInt(str);
                if (choix <= 0)   throw new Exception();
            } catch (Exception e) {
                logger.info("","Veuillez entrer un nombre positif! ");
            }
        } while (choix<=0);
        return choix;
    }
}
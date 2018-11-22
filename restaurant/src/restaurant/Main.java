package restaurant;
import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;

class Main {

    private static Scanner scan = new Scanner(System.in);
    private static Logger logger = LoggerFactory.getLogger("main");
    private static Clients clients = new Clients();
    private static Methodes methodes = new Methodes();
    private static Products products = new Products();
    private static Caisse caisse = new Caisse();


    //Fonction regroupant les opérations disponibles depuis le menu
    private static boolean actions(String choice, boolean bool, Products products){
        switch (choice) {
            case "q": bool = true; break;
            case "o": methodes.affichage_choix(); break;
            case "a": products.ajouter_produit(); break;
            case "p": products.afficherListe(); break;
            case "n": caisse.ouvrir_note(clients); break;
            case "k": clients.afficherListe(); break;
            case "e": caisse.enregistrer(clients, products); break;
            case "f": caisse.cloturer(clients); break;
            default: logger.error("OUTPUT","Opération impossible");
        }
        return bool;
    }


    public static void main(String[] args) {

        logger.info("OUTPUT","\nBienvenue dans notre restaurant de Bagels!\n");
        boolean bool = false;//reste faux tant que l'utilisateur ne rentre pas "q"
        products.init();

        while(!bool) {
            logger.info("OUTPUT","\nQue voulez-vous faire? [o pour afficher les opérations possibles] : ");
            String choice = scan.next();
            logger.info("INPUT","Option choisie"+choice);
            bool = actions(choice, bool, products);
        }
        logger.info("OUTPUT"," Merci, à bientôt!");
    }
}
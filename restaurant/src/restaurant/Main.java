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

    private static boolean actions(String choice, boolean bool, Products products){
        switch (choice) {
            case "q": bool = true; break;
            case "o": methodes.affichage_choix(); break;
            case "a": products.ajouter_produit(); break;
            case "p": products.afficherListe(); break;
            case "n": clients.ouvrir_note(clients); break;
            case "k": clients.afficherListe(); break;
            case "e": clients.enregistrer(clients, products); break;
            case "f": clients.cloturer(clients); break;
        }
        return bool;
    }

    public static void main(String[] args) {

        logger.info("","\nBienvenue dans notre restaurant de Bagels!!\n");
        boolean bool = false;
        products.init();

        while(!bool) {
            logger.info("","\nQue voulez-vous faire? [o pour afficher les opérations possibles] : ");
            String choice = scan.next();
            bool = actions(choice, bool, products);
        }
        logger.info(""," Merci, à bientôt!");
    }
}
package restaurant;
import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;

public class Main {

    static Scanner scan = new Scanner(System.in); Scanner scan1 = new Scanner(System.in); Scanner scan2 = new Scanner(System.in);
    static Logger logger = LoggerFactory.getLogger("player");
    static Clients clients = new Clients();
    static Methodes methodes = new Methodes();
    static Products products = new Products();

    public static boolean actions(String choice, boolean bool, Products products){
        switch (choice) {
            case "q": bool = true;   break;
            case "o": methodes.affichage_choix();   break;
            case "a": products.ajouter_produit(); break;
            case "p": products.afficherListe();  break;
            case "n": clients.ouvrir_note(clients); break;
            case "k": clients.afficherListe();    break;
            case "e": methodes.enregistrer(clients, products); break;
        }
        return bool;
    }

    public static void main(String[] args) {

        logger.info("","\nBienvenue dans notre restaurant de Bagels!!\n");
        boolean bool = false;
        products.init();

        while(bool==false) {
            logger.info("","\nQue voulez-vous faire? [o pour afficher les opérations possibles]\n");
            String choice = scan.next();
            bool = actions(choice, bool, products);
        }
        logger.info("","Merci, à bientôt!");
    }
}
package restaurant;

import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        double inf = Double.POSITIVE_INFINITY;

        Scanner scan = new Scanner(System.in); Scanner scan1 = new Scanner(System.in); Scanner scan2 = new Scanner(System.in);
        Logger logger = LoggerFactory.getLogger("player");

        logger.info("","\nBienvenue dans notre restaurant de Bagels!!\n");

        int bool = 0;
        Products products = new Products();    Clients clients = new Clients();     Methodes methodes = new Methodes();

        Aliment aliment1 = new Aliment("bagel", 20, 8);products.add(aliment1);
        Aliment aliment2 = new Aliment("burger", 10, 10); products.add(aliment2);
        Aliment aliment3 = new Aliment("smoothie", 5, 5); products.add(aliment3);
        Aliment aliment4 = new Aliment("café", (int)inf, 2); products.add(aliment4);
        Aliment aliment; Note note; Aliment aliment5 = null;

        while(bool != 1) {

            logger.info("","\nQue voulez-vous faire? [o pour afficher les opérations possibles]\n");
            String choice = scan.next();

            switch (choice) {

                case "q": bool = 1;   break;
                case "o": methodes.affichage_choix();   break;
                case "a": aliment = methodes.ajouter_produit(products); products.add(aliment); break;
                case "p": products.afficherListe();  break;
                case "n": note = methodes.ouvrir_note(clients); clients.add(note); break;
                case "k": clients.afficherListe();    break;
                case "e": methodes.enregistrer(clients, products, aliment5); break;

            }
        }
        logger.info("","Merci, à bientôt!");
    }
}
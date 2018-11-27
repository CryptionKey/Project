package restaurant;

import logger.Logger;
import logger.LoggerFactory;


class Main {

    private static Logger logger = LoggerFactory.getLogger("main");
    private static Clients clients = new Clients();
    private static Affichage affichage = new Affichage();
    private static Produits produits = new Produits();
    private static Caisse caisse = new Caisse();


    //Fonction regroupant les opérations disponibles depuis le menu
    private static boolean actions(String choice, boolean continuer, Produits produits){
        switch (choice) {
            case "q": continuer = true; break;
            case "o": affichage.affichage_choix(); break;
            case "a": produits.ajouter_produit(); break;
            case "p": produits.afficherListe(); break;
            case "n": caisse.ouvrir_note(clients); break;
            case "k": clients.afficherListe(); break;
            case "e": caisse.enregistrer(clients, produits); break;
            case "f": caisse.cloturer(clients); break;
            case "c": caisse.donnees_comptable(); break;
        }
        return continuer;
    }


    //Le Main
    public static void main(String[] args) {
        logger.info("OUTPUT","\nBienvenue dans notre restaurant de Bagels!!\n");
        boolean continuer = false;//reste faux tant que l'utilisateur ne rentre pas "q"
        produits.initialiser();
        while(!continuer) {
            String choix = Affichage.choix_chaine(Affichage.output_choix, Affichage.input_choix);
            continuer = actions(choix, continuer, produits); }
        logger.info("OUTPUT"," Merci, à bientôt!");
    }

}
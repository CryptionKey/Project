package restaurant;
import logger.Logger;
import logger.LoggerFactory;

class Main {

    private static Logger logger = LoggerFactory.getLogger("main");
    private static Clients clients = new Clients();
    private static Affichage affichage = new Affichage();
    private static Products products = new Products();
    private static Caisse caisse = new Caisse();

    //Fonction regroupant les opérations disponibles depuis le menu
    private static boolean actions(String choice, boolean continuer, Products products){
        switch (choice) {
            case "q": continuer = true; break;
            case "o": affichage.affichage_choix(); break;
            case "a": products.ajouter_produit(); break;
            case "p": products.afficherListe(); break;
            case "n": caisse.ouvrir_note(clients); break;
            case "k": clients.afficherListe(); break;
            case "e": caisse.enregistrer(clients, products); break;
            case "f": caisse.cloturer(clients); break;
            case "c": caisse.donnees_comptable(); break;
        }
        return continuer;
    }

    public static void main(String[] args) {
        logger.info("OUTPUT","\nBienvenue dans notre restaurant de Bagels!!\n");
        boolean continuer = false;//reste faux tant que l'utilisateur ne rentre pas "q"
        products.initialiser();
        while(!continuer) {
            String choix = Affichage.choix_chaine(Affichage.output_choix, Affichage.input_choix);
            continuer = actions(choix, continuer, products); }
        logger.info("OUTPUT"," Merci, à bientôt!");
    }

}
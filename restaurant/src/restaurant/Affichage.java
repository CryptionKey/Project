package restaurant;

import logger.Logger;
import logger.LoggerFactory;
import java.util.Scanner;

class Affichage {

    private static Logger logger = LoggerFactory.getLogger("Affichage");
    private static Scanner scan = new Scanner(System.in);

    static String output_nom_aliment_ajouter = "\nEntrez le nom de l'aliment que vous souhaitez ajouter: ";
    static String input_nom_aliment_ajouter = "\tNom de l'aliment à ajouter: ";

    static String output_nom_aliment_facturer = "\nEntrez le nom de l'aliment que vous souhaitez facturer: ";
    static String input_nom_aliment_facturer = "\tNom de l'aliment à facturer: ";

    static String output_note_client_ouvrir = "\nEntrez le nom du client dont vous souhaitez ouvrir ou créer la note: ";
    static String input_note_client_ouvrir = "\tNote du client demandée: ";

    static String output_nom_client_facturer = "\nEntrez le nom du client que vous souhaitez facturer: ";
    static String input_nom_client_facturer = "\tNom du client demandé: ";

    static String output_nom_client_cloturer = "\nEntrez le nom du client dont vous souhaitez cloturer la note: ";
    static String input_nom_client_cloturer = "\tNom du client demandé: ";

    static String output_quantite = "\nEntrez la quantité du produit souhaité: ";
    static String input_quantite = "\tQuantitée demandée: ";

    static String output_prix = "\nEntrez le prix du produit: ";
    static String input_prix = "\tPrix entré: ";

    static String output_choix = "\nQue voulez-vous faire? [o pour afficher les opérations possibles]: ";
    static String input_choix = "\tOption choisie ";

    public void affichage_choix() {
        String operations = "\n\to: Afficher la liste des opérations disponibles.\n";
        String produits = "\tp: Afficher la liste des produits à la vente.\n";
        String ajouter_produit = "\ta: Ajouter un produit à la vente.\n";
        String ouvrir_note = "\tn: Ouvrir la note d'un client.\n";
        String clients = "\tk: Afficher la liste des clients\n";
        String enregistrer = "\te: Enregistrer la vente d'un produit sur la note d'un client.\n";
        String cloturer = "\tf: Clotûrer la note d'un client en affichant:\n\t\tle prix de chaque produit HT (hors-taxe),\n\t\tle prix total HT,\n\t\tla TVA(10%),\n\t\tle prix TTC.\n";
        String donnees_comptables = "\tc: Afficher les données comptables:\n\t\ttotal des rentrées d'argent,\n\t\ttotal de la TVA facturée.\n";
        String quitter = "\tq: Quitter le restaurant.\n";
        logger.info("OUTPUT",operations + produits + ajouter_produit + ouvrir_note + clients + enregistrer + cloturer + donnees_comptables + quitter);
    }

    private static void chaine_input(String chaine_in, String nom){
        logger.info("INPUT", chaine_in + nom + ".\n");
    }

    public static String choix_chaine(String chaine_out, String chaine_in) {
        logger.info("OUTPUT", chaine_out);
        String nom = scan.next();
        chaine_input(chaine_in, nom);
        return nom;
    }

    //Tester ce que rentre l'utilisateur lorsqu'on attend un nombre
    public static double verification_nombre(String type, String chaine_out, String chaine_in) {
        double choix = 0 ;
        String nombre;
        do {
            nombre = choix_chaine(chaine_out, chaine_in);
            try {
                if(type.equals("double")){choix = Double.parseDouble(nombre);}
                if(type.equals("entier")){choix = Integer.parseInt(nombre);}
                if (choix <= 0)   throw new Exception();
            } catch (Exception e) {
                logger.error("OUTPUT"," Veuillez entrer un nombre positif! ");
            }
        } while (choix<=0);
        return choix;
    }

}
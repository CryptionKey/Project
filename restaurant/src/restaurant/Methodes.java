package restaurant;

import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;

public class Methodes {

    Logger logger = LoggerFactory.getLogger("player");
    Scanner scan = new Scanner(System.in); Scanner scan1 = new Scanner(System.in); Scanner scan2 = new Scanner(System.in);

    void affichage_choix(){
        logger.info("","\to: Afficher la liste des opérations disponibles.\n" );
        logger.info("","\tp: Afficher la liste des produits à la vente.\n");
        logger.info("","\ta: Ajouter un produit à la vente.\n");
        logger.info("","\tn: Ouvrir la note d'un client.\n");
        logger.info("","\tk: Afficher la liste des clients\n");
        logger.info("","\te: Enregistrer la vente d'un produit sur la note d'un client.\n");
        logger.info("","\tf: Clotûrer la note d'un client en affichant:\n");
        logger.info("","\t\tle prix de chaque produit HT (hors-taxe),\n\t\tle prix total HT,\n");
        logger.info("","\t\tla TVA(10%),\n\t\tle prix TTC.\n");
        logger.info("","\tc: Afficher les données comptables:\n");
        logger.info("","\t\ttotal des rentrés d'argent,\n\t\ttotal de la TVA facturée.\n");
        logger.info("","\tq: Quitter le restaurant.\n");
    }


    public boolean verification_aliment_produit(Products products, String nom){
        boolean verif = true; /*vrai par défault */
        for (Aliment aliment_courant : products.productList) {
            if(aliment_courant.getNom().toLowerCase().compareTo(nom.toLowerCase())==0){
                verif = false;} //si les chaînes sont identiques:
        }
        return verif;
    }

    Aliment ajouter_produit(Products products){
        logger.info("","\nAjoutez un produit à la vente\nEntrez le nom du produit:\n");
        String nom = scan.next();
        Aliment aliment = null;
        boolean test_nom = verification_aliment_produit(products, nom); //aliment.verification(products); /*vrai par défault */

        if(test_nom) {
            logger.info("","\nEntrez la quantité du produit:\n");   int quantite = scan1.nextInt();
            logger.info("","\nEntrez le prix du produit:\n");      double prix = scan2.nextDouble();
            aliment = new Aliment(nom, quantite, prix); //products.add(aliment);
        }
        else{logger.info("", "\nCe produit existe déjà\n");}
        return aliment;
    }


    Note ouvrir_note(Clients clients){
        logger.info("","\nDonnez le nom du client dont vous souhaitez ouvrir la note\n");
        String nom_1 = scan.next();     int compteur = 0, index=0;      boolean test_nom_1=true; //vrai par défault
        Note note = null;
        for (Note note_courante : clients.noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_1.toLowerCase())==0){  test_nom_1=false; index=compteur; }
            compteur++;
        }
        if(test_nom_1) {  note = new Note(nom_1); }
        else {
            logger.info("", "Voici la note demandée:\n");
            logger.info("","test456:"+clients.noteList.get(index));
        }
        return note;
    }

    void enregistrer(Clients clients, Products products, Aliment aliment){
        logger.info("","Entrez le nom du client que vous voulez facturer:");
        String nom = scan.next();  int compteur = 0, index = 0;
        boolean test_nom = false; //faux par défault

        for (Note note_courante : clients.noteList) {//si les chaînes sont différentes:
            if(note_courante.getNom().toLowerCase().compareTo(nom.toLowerCase())==0){
                test_nom=true;
                index=compteur;
            }
            compteur++;
        }

        if(test_nom) {
            logger.info("","Entrez le nom de l'article que vous souhaitez facturer:");
            String nom_aliment = scan.next();
            Aliment aliment_demande = null;

            test_nom = false; //faux par défautl
            for (Aliment aliment_courant : products.productList) {//si les chaînes sont pareilles:
                if(aliment_courant.getNom().toLowerCase().compareTo(nom_aliment.toLowerCase())==0){
                    test_nom=true;
                    aliment_demande = aliment_courant;
                }
            }

            if(test_nom) {
                logger.info("","\nEntrez la quantité du produit souhaité:\n");
                int quantite = scan1.nextInt();
                aliment = new Aliment(aliment_demande.getNom(), quantite, aliment_demande.getPrix());
                if(quantite>aliment_demande.getQuantite()){ logger.info("", "Il n'y en a pas assez en stock\n"); }
                else{
                    clients.noteList.get(index).productList.add(aliment);
                    logger.info(""," "+clients.noteList.get(index));
                    if(aliment_demande.getNom() != "café"){ aliment_demande.setQuantite(aliment_demande.getQuantite()-quantite); }
                }
            } else{logger.info("","Ce produit n'est pas proposé à la vente.\n");}
        } else {logger.info("", "Ce client n'existe pas, ouvrez d'abord une nouvelle note:\n"); }
    }
}




 /*   boolean retirer_aliment(Products products, Aliment aliment_demande, int quantite){

        boolean stock = true; //faux par défautl

       if(quantite>aliment_demande.getQuantite()){ logger.info("", "Il n'y en a pas assez en stock\n");}
       if(aliment_demande.getNom() != "café"){ aliment_demande.setQuantite(aliment_demande.getQuantite()-quantite);}

        return stock;
    }*/
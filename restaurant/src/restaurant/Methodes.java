package restaurant;

import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;

public class Methodes {

    private Logger logger = LoggerFactory.getLogger("player");
    private Scanner scan = new Scanner(System.in); Scanner scan1 = new Scanner(System.in); Scanner scan2 = new Scanner(System.in);

    public void affichage_choix(){
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

    public void enregistrer(Clients clients, Products products){
        logger.info("","Entrez le nom du client que vous voulez facturer:");
        String nom = scan.next();  int compteur = 0, index = 0;
        boolean test_nom = false; //faux par défault

        for (Note note_courante : clients.getNoteList()) {//si les chaînes sont différentes:
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
            for (Aliment aliment_courant : products.getProductList()) {//si les chaînes sont pareilles:
                if(aliment_courant.getNom().toLowerCase().compareTo(nom_aliment.toLowerCase())==0){
                    test_nom=true;
                    aliment_demande = aliment_courant;
                }
            }

            if(test_nom) {
                logger.info("","\nEntrez la quantité du produit souhaité:\n");
                int quantite = scan1.nextInt();
                Aliment aliment = new Aliment(aliment_demande.getNom(), quantite, aliment_demande.getPrix());
                if(quantite>aliment_demande.getQuantite()){ logger.info("", "Il n'y en a pas assez en stock\n"); }
                else{
                    clients.getNoteList().get(index).getProductList().add(aliment);
                    logger.info(""," "+clients.getNoteList().get(index));
                    if(!"café".equals(aliment_demande.getNom())){ aliment_demande.setQuantite(aliment_demande.getQuantite()-quantite); }
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
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





}




 /*   boolean retirer_aliment(Products products, Aliment aliment_demande, int quantite){

        boolean stock = true; //faux par défautl

       if(quantite>aliment_demande.getQuantite()){ logger.info("", "Il n'y en a pas assez en stock\n");}
       if(aliment_demande.getNom() != "café"){ aliment_demande.setQuantite(aliment_demande.getQuantite()-quantite);}

        return stock;
    }*/
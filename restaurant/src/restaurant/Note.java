package restaurant;

import java.util.LinkedList;
import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;


public class Note {

    private String nom;
    private final LinkedList<Aliment> productList = new LinkedList<>();
    private static Logger logger = LoggerFactory.getLogger("note");
    private static Scanner scan = new Scanner(System.in);

    private java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");


    //constructor
    public Note(String nom){ this.nom = nom.toLowerCase(); }

    //Pour retourner la liste
    public LinkedList<Aliment> getProductList() {return productList;}

    //Pour récupérer le nom.
    public String getNom(){ return nom; }

    public String toString(){ return ""+nom+":\n"+productList.toString()+""; }

    //Afficher la liste d'aliments de la note
    public void afficherListe(){
        if(this.getProductList().size()!=0) {
            logger.info("OUTPUT", "Voici la note demandée:\n");
            String message = "";//initilisation du message qui sera affiché
            for (Aliment aliment : this.productList) {
                message += "\t" + aliment + "\n";//Chaque aliment de la liste est ajouté au message
            }
            logger.info("OUTPUT", message); //On affiche le message entier
        } else{ logger.error("OUTPUT","Cette note ne contient encore aucun produit.\n");}
    }

    //Demander si on veut offrir une remise
    public static boolean demander_remise(){
        boolean remise = false;
        String choix;
        do {
            logger.info("OUTPUT", "Souhaitez-vous offrir une remise de 10% à ce client? [o : oui / n : non]");
            choix = scan.next();
            logger.info("INPUT","Choix: "+choix+".\n");
        }while(!"o".equals(choix) && !"n".equals(choix));
        if("o".equals(choix)){ remise = true;}
        return remise;
    }

    //Calculer le prix total HT, c'est le prix affiché dans le menu
    private double prixHT(LinkedList<Aliment> productList){
        double total=0;
        for(Aliment aliment : productList){//il suffit de faire la somme du prix de chaque aliment selon la quantité
            total = total + aliment.getQuantite() * aliment.getPrix();
        }
        return total;
    }

    //Appliquer la TVA
    public double getTVA(double prixHT){
        return prixHT*0.1; //La TVA est fixé à 10%
    }

    //Afficher les produits enregistrés, total HT, TVA; total TTC
    public void afficher_facture(boolean remise){
        logger.info("OUTPUT", "\nPrix de chaque produit hors-taxe :\n");
        afficherListe();
        double prixHT = prixHT(this.productList);
        String message = "Prix total hors-taxe : "+prixHT+" €\n";
        double TVA = (getTVA(prixHT)*100)/100;
        message += "TVA : "+df.format(TVA)+" €\nPrix taxes comprises : "+(TVA + prixHT)+" €\n";
        if(remise){//Si le vendeur a choisi de faire la remise de 10%
            double prix =  (((TVA + prixHT)-(TVA + prixHT)*0.1)*100)/100;
            logger.info("OUTPUT",message+"Prix après remise : "+df.format(prix)+" €\n");}
        else{logger.info("OUTPUT",message);} //s'il n'y a pas de remise
        Caisse.mise_a_jour_donnees_comptable(remise, prixHT, TVA);
    }

}
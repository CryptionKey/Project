package restaurant;

import java.util.LinkedList;
import logger.Logger;
import logger.LoggerFactory;


public class Note {

    private String nom;
    private final LinkedList<Aliment> productList = new LinkedList<>();
    private static Logger logger = LoggerFactory.getLogger("note");

    private java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");

    //constructor
    public Note(String nom){ this.nom = nom.toLowerCase(); }

    //Pour retourner la liste
    public LinkedList<Aliment> getProductList() {return productList;}

    //Pour récupérer le nom.
    public String getNom(){ return nom; }

    public String toString(){ return ""+nom+":\n"+productList.toString()+""; }

    public void ajouter_aliment(Aliment aliment_demande, int quantite){
        Aliment aliment_verifie = Produits.verification_aliment_existant(aliment_demande.getNom(), false, productList);
        if(aliment_verifie != null){ //l'aliment existe déjà dans la note du client
            productList.get(Produits.getIndexAliment(aliment_verifie.getNom() , productList)).setQuantite(aliment_verifie.getQuantite()+quantite);
        }else{ //l'aliment n'existe pas encore dans la note du client
            Aliment aliment_ajoute = new Aliment(aliment_demande.getNom(), quantite, aliment_demande.getPrix());
            productList.add(aliment_ajoute); }
    }

    //Afficher la liste d'aliments de la note
    public void afficherListe(){
        if(this.getProductList().size()!=0) {
            logger.info("OUTPUT", "Voici la note demandée:\n");
            String message = "";//initilisation du message qui sera affiché
            for (Aliment aliment : this.productList) {
                message += "\t" + aliment + "\n";//Chaque aliment de la liste est ajouté au message
            }
            logger.info("OUTPUT", message); //On affiche le message entier
        } else { logger.error("OUTPUT","Cette note ne contient aucun produit.\n");}
    }

    //Demander si on veut offrir une remise
    public static boolean demander_remise(){
        boolean remise = false;
        String choix;
        do {
            choix = Affichage.choix_chaine(Affichage.output_remise, Affichage.input_reponse);
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
        if(productList.size() != 0){logger.info("OUTPUT", "\nPrix de chaque produit hors-taxe :\n");}
        afficherListe();
        double prixHT = prixHT(this.productList);
        String message = "Prix total hors-taxe : "+df.format(prixHT)+" €\n";
        double TVA = getTVA(prixHT);
        message += "TVA : "+df.format(TVA)+" €\nPrix taxes comprises : "+df.format(TVA + prixHT)+" €\n";
        if(remise){//Si le vendeur a choisi de faire la remise de 10%
            double prix = ((TVA + prixHT)-(TVA + prixHT)*0.1);
            logger.info("OUTPUT",message+"Prix après remise : "+df.format(prix)+" €\n");}
        else{logger.info("OUTPUT",message);} //s'il n'y a pas de remise
        Caisse.mise_a_jour_donnees_comptable(remise, prixHT, TVA);
    }

}
package restaurant;

import java.util.LinkedList;
import logger.Logger;
import logger.LoggerFactory;

public class Note {

    private String nom;
    private final LinkedList<Aliment> productList = new LinkedList<Aliment>();
    Logger logger = LoggerFactory.getLogger("note");

    //constructor
    public Note(String nom){
        this.nom = nom.toLowerCase();
    }


    public LinkedList<Aliment> getProductList() {return productList;}

    //Pour récupérer le nom
    public String getNom(){
        return nom;
    }


    public String toString(){
        return ""+nom+":\n"+productList.toString()+"";
    }


    //Afficher la liste d'aliments de la note
    public void afficherListe(){
        for (Aliment aliment : this.productList) {
            logger.info("", "\t"+aliment+"\n");
        }
    }

    //Ajouter un aliment à la note
    public void add (Aliment aliment){
        productList.add(aliment);
    }

}

package restaurant;

import java.util.LinkedList;
import logger.Logger;
import logger.LoggerFactory;

public class Products {

    public final LinkedList<Aliment> productList = new LinkedList<Aliment>();
    Logger logger = LoggerFactory.getLogger("player");


    public String toString() {
        return productList.toString();
    }


    //Afficher la liste des aliments
    public void afficherListe(){
        for (Aliment aliment : this.productList) {
            logger.info("", "\t"+aliment+"\n");
        }
    }


    //Ajouter un aliment à la vente
    public void add (Aliment aliment){
       productList.add(aliment);
    }

}



package restaurant;

import logger.Logger;
import logger.LoggerFactory;

class Methodes {

    private Logger logger = LoggerFactory.getLogger("methodes");

    void affichage_choix() {
        logger.info("OUTPUT", "\n\to: Afficher la liste des opérations disponibles.\n" +
                                          "\tp: Afficher la liste des produits à la vente.\n" +
                                          "\ta: Ajouter un produit à la vente.\n" +
                                          "\tn: Ouvrir la note d'un client.\n" +
                                          "\tk: Afficher la liste des clients\n" +
                                          "\te: Enregistrer la vente d'un produit sur la note d'un client.\n" +
                                          "\tf: Clotûrer la note d'un client en affichant:\n" +
                                            "\t\tle prix de chaque produit HT (hors-taxe),\n" +
                                            "\t\tle prix total HT,\n\t\tla TVA(10%),\n\t\tle prix TTC.\n" +
                                          "\tc: Afficher les données comptables:\n" +
                                            "\t\ttotal des rentrés d'argent,\n\t\ttotal de la TVA facturée.\n" +
                                          "\tq: Quitter le restaurant.\n");
    }

}
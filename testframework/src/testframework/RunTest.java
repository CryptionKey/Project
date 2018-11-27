package testframework;

import logger.Logger;
import logger.LoggerFactory;
import restaurant.Aliment;


public class RunTest {


    private static Test_TVA test_tva = new Test_TVA();
    private static Test_Produit test_produit = new Test_Produit();
    private static Logger logger = LoggerFactory.getLogger("RunTest");

    private static Aliment burger = new Aliment("burger", 10, 10);

    public static void main(String[] args) {
        logger.info("PROGRAM","TESTS\n");
        //test_tva.Test_get_TVA_decimal();
        test_tva.Test_get_TVA_entier(2425.68, 242.57);
        //test_tva.Test_get_TVA_nul();
        test_produit.test_getIndexAliment();
        //test_produit.test_augmenter_stock(burger);
        test_produit.test_verif_aliment();
    }

}

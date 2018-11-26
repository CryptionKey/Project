package testframework;

import logger.Logger;
import logger.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class RunTest {


    private static Test_TVA test_tva = new Test_TVA();
    private Logger logger = LoggerFactory.getLogger("RunTest");

    public static void main(String[] args) {
        System.out.println("TESTS");
        //test_tva.Test_get_TVA_decimal();
        test_tva.Test_get_TVA_entier(2425.68, 242.57);
        //test_tva.Test_get_TVA_nul();
    }

}

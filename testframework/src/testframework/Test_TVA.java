package testframework;

import logger.LoggerFactory;
import restaurant.Note;
import java.lang.*;
import java.text.DecimalFormat;


public class Test_TVA {

    private logger.Logger logger = LoggerFactory.getLogger("test_TVA");
    private Note note = new Note("note");

    private DecimalFormat df = new DecimalFormat("#.##");

    public void Test_get_TVA_entier(double prixHT, double TVA_attendue){
        double TVA = note.getTVA(prixHT);
        System.out.print("TVA : "+TVA+"\n");
        String test_TVA = df.format(TVA);
        System.out.print("test_TVA : "+test_TVA+"\n");
        if (!df.format(TVA_attendue).equals(test_TVA)){
            throw new AssertionError("La TVA de "+prixHT+"€ devrait être "+TVA_attendue+"€, mais le résultat obtenu est "+test_TVA+"€");
        } else {
            logger.info("PROGRAM","Le test de TVA (avec un entier) a été réussi\n"); }
    }

    /*
    public void Test_get_TVA_decimal(){
        double prixHT = 12.54;
        double test_TVA = note.getTVA(prixHT);
        test_TVA = Double.parseDouble(df.format(test_TVA));
                //Double.parseDouble(new DecimalFormat("0.##").format(test_TVA));
        int resultat = Double.compare(test_TVA, 1.25);
        if (resultat != 0){
            throw new AssertionError("La TVA de 12€ devrait être 1.2€, mais c'est"+(test_TVA));
        }else{logger.info("PROGRAM","Le test de TVA a été réussi\n");}
    }
    */

    /*
    public void Test_get_TVA_nul(){
        double test_TVA = note.getTVA(0);
        int resultat = Double.compare(test_TVA, 0);
        if (resultat != 0){
            throw new AssertionError("La TVA de 0€ devrait être nulle€, mais c'est"+test_TVA);
        }
    }
    */

}
/*import java.lang.reflect.Method;


        Class<?> clazz = ...

        for(Method method : clazz.getDeclaredMethods()) {
        System.out.println(method.getName());
        }*/
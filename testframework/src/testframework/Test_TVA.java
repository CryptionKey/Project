package testframework;

import logger.LoggerFactory;
import restaurant.Note;


public class Test_TVA {


    private String nom;
    private logger.Logger logger = LoggerFactory.getLogger("test_TVA");
    private Note note = new Note(nom);


    public void Test_get_TVA_entier(){
        double test_TVA = note.getTVA(10);
        if (test_TVA != 1){
            throw new AssertionError("La TVA de 10€ devrait être 1€, mais c'est"+test_TVA);
        }
    }


    public void Test_get_TVA_decimal(){
        double test_TVA = note.getTVA(12);
        if (test_TVA != 1.2){
            throw new AssertionError("La TVA de 12€ devrait être 1.2€, mais c'est"+test_TVA);
        }
    }


    public void Test_get_TVA_nul(){
        double test_TVA = note.getTVA(0);
        if (test_TVA != 0){
            throw new AssertionError("La TVA de 0€ devrait être nulle€, mais c'est"+test_TVA);
        }
    }

}
/*import java.lang.reflect.Method;


        Class<?> clazz = ...

        for(Method method : clazz.getDeclaredMethods()) {
        System.out.println(method.getName());
        }*/
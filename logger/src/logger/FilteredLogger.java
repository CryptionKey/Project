package logger;


import java.util.function.Predicate;

public class FilteredLogger implements Logger {


    //public void info(String category, String message) {System.out.print(message); }
    public void error(String category, String message) {
        System.out.print(message);
    }

    private Predicate<String> condition;
    private Logger delegate;

    public FilteredLogger(Logger delegate, Predicate<String> condition) {
        this.delegate = delegate;
        this.condition = condition;
    }

    public void info(String category, String message ) {
        if(condition.test(message))
            delegate.info(category, message);
    }

}

package logger;


import java.util.function.Predicate;

public class FilteredLogger implements Logger {


    private Predicate<String> condition;
    private Logger delegate;

    FilteredLogger(Logger delegate, Predicate<String> condition) {
        this.delegate = delegate;
        this.condition = condition;
    }

    public void info(String category, String message ) {
        if(condition.test(message))
            delegate.info(category, message);
    }

    public void error(String category, String message) {
        if(condition.test(message))
            delegate.info(category, message);
    }

}

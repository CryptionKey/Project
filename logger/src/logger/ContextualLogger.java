package logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContextualLogger implements Logger {


    private String callerClass;
    private Logger delegateLogger;

    ContextualLogger(String callerClass, Logger delegateLogger) {
        this.callerClass = callerClass;
        this.delegateLogger = delegateLogger;
    }


    public void info(String category, String message) {
        SimpleDateFormat logFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
        delegateLogger.info(category,"Date: "+logFormat.format(new Date()) + "      categorie du message: " + category +"        level: INFO       classe utilisée: " + callerClass +"\nle message: "+message+"\n");
    }


    public void error(String category, String message) {
        SimpleDateFormat logFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
        delegateLogger.error(category,"Date: "+logFormat.format(new Date()) + "      categorie du message: " + category +"        level: ERROR      classe utilisée: " + callerClass +"\nle message: "+message+"\n");

    }
}

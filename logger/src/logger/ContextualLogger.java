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
        delegateLogger.info(category,"Date: "+logFormat.format(new Date()) + "      classe utilisée: " + callerClass +"      categorie du message: " + category +"        level: INFO\nle message: "+message+"\n");
    }


    public void error(String category, String message) {
        SimpleDateFormat logFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
        delegateLogger.info(category,"Date: "+logFormat.format(new Date()) + "      classe utilisée: " + callerClass +"      categorie du message: " + category +"        level: ERROR\nle message: "+message+"\n");

    }
}

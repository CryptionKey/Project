package logger;


public class LoggerFactory {

    public static Logger getLogger(String name) {
        return new CompositeLogger(new ContextualLogger(name, new FileLogger("./loggerOutput.txt")),  new ConsoleLogger());
        //return new CompositeLogger(new ContextualLogger(name, new FilteredLogger(new FileLogger("./loggerOutput.txt"), message -> !message.contains("player"))),  new ConsoleLogger());
    }

}

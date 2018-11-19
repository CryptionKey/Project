package logger;


public class LoggerFactory {

    public static Logger getLogger(String name) {
        return new CompositeLogger(new ContextualLogger(name, new FileLogger("./loggerOutput.txt")),  new ConsoleLogger());
        //return new CompositeLogger(new ContextualLogger(name, new FilteredLogger(new FileLogger("./loggerOutput.txt"), message -> !message.contains("player"))),  new ConsoleLogger());
    }

}



  /*  public static Logger getLogger(String name) {
        final String chemin = "./loggerOutput_2.txt";
        final File fichier = new File(chemin);
        try {
            // Creation du fichier
            fichier.createNewFile();
            // creation d'un writer (un écrivain)
            final FileWriter writer = new FileWriter(fichier);
            try {
                writer.write("ceci est un texte\n");
                writer.write("encore et dsd encore");
                return new CompositeLogger(new ContextualLogger(name, new FilteredLogger(new FileLogger("./loggerOutput_2.txt"), message -> !message.contains("player"))), new ContextualLogger(name, new ConsoleLogger()));

            } finally {
                // quoiqu'il arrive, on ferme le fichier
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Impossible de creer le fichier");
        }
       return null;
    }
}*/
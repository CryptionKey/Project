package restaurant;

import java.util.LinkedList;
import logger.Logger;
import logger.LoggerFactory;

public class Clients {

    public final LinkedList<Note> noteList = new LinkedList<Note>();
    Logger logger = LoggerFactory.getLogger("player");


    //constructor
    public Clients(){ }


    public String toString() {
        return noteList.toString();
    }


    //Afficher la liste des notes de client
    void afficherListe(){
        for (Note note : this.noteList) {
            logger.info("", "\t"+note+"\n");
        }
    }


    //Ajouter une note à la liste des clients
    void add (Note note){
        noteList.add(note);
    }
}

package restaurant;
import java.util.LinkedList;
import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;

public class Clients {

    public final LinkedList<Note> noteList = new LinkedList<Note>();
    Logger logger = LoggerFactory.getLogger("player");
    public Scanner scan = new Scanner(System.in);

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

    //verifier si un client se trouve déjà dans la liste
    public boolean verification_client_existant(String nom_client){
        boolean verif = false; /*le client n'existe pas par défault*/
        for (Note note_courante : noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0){
                verif = true;} //si les chaînes sont identiques, le client existe
        }
        return verif;
    }

    Note ouvrir_note(Clients clients){
        logger.info("","\nDonnez le nom du client dont vous souhaitez ouvrir la note\n");
        String nom_1 = scan.next();     int compteur = 0, index=0;      boolean test_nom_1=true; //vrai par défault
        Note note = null;
        for (Note note_courante : clients.noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_1.toLowerCase())==0){  test_nom_1=false; index=compteur; }
            compteur++;
        }
        if(test_nom_1) {  note = new Note(nom_1); }
        else {
            logger.info("", "Voici la note demandée:\n");
            logger.info("",""+clients.noteList.get(index));
        }
        return note;
    }


}

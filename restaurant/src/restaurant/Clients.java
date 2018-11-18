package restaurant;
import java.util.LinkedList;
import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;

public class Clients {

    private final LinkedList<Note> noteList = new LinkedList<Note>();
    Logger logger = LoggerFactory.getLogger("player");
    public Scanner scan = new Scanner(System.in);

    //constructor
    public Clients(){ }

    public String toString() {
        return noteList.toString();
    }

    public LinkedList<Note> getNoteList() { return noteList;  }

    //Afficher la liste des notes de client
    public void afficherListe(){
        for (Note note : this.noteList) {
            logger.info("", "\t"+note+"\n");
        }
    }

    //Ajouter une note à la liste des clients
    public void add (Note note){
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

    public int getIndexNote(String nom_client){
        int compteur = 0, index=0;
        for (Note note_courante : noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0){
                index=compteur;} //si les chaînes sont identiques, le client existe
            compteur++;
        }
        return index;
    }

    public Note ouvrir_note(Clients clients){
        logger.info("","\nDonnez le nom du client dont vous souhaitez ouvrir la note\n");
        String nom_client = scan.next();
        Note note = null;
        if(!verification_client_existant(nom_client)) {
            note = new Note(nom_client);
            clients.add(note);
        }
        else {
            logger.info("", "Voici la note demandée:\n");
            clients.noteList.get(getIndexNote(nom_client)).afficherListe();
            //logger.info("",""+clients.noteList.get(getIndexNote(nom_client)));
        }
        return note;
    }

    public void enregistrer(Clients clients, Products products){
        logger.info("","Entrez le nom du client que vous voulez facturer:");
        String nom_client = scan.next();

        if(verification_client_existant(nom_client)) {
            logger.info("","Entrez le nom de l'article que vous souhaitez facturer:");
            String nom_aliment = scan.next();

            if(products.verification_aliment_existant(nom_aliment)) {
                logger.info("","\nEntrez la quantité du produit souhaité:\n");
                int quantite = scan.nextInt();
                int index_aliment = products.getIndexAliment(nom_aliment);
                Aliment aliment_demande = products.getProductList().get(index_aliment);
                if(quantite>aliment_demande.getQuantite()){ logger.info("", "Il n'y en a pas assez en stock\n"); }
                else{
                    Aliment aliment = new Aliment(nom_aliment.toLowerCase(), quantite, products.getProductList().get(index_aliment).getPrix());
                    clients.getNoteList().get(getIndexNote(nom_client)).getProductList().add(aliment);
                    logger.info(""," "+clients.getNoteList().get(getIndexNote(nom_client)));
                    if(!"café".equals(aliment_demande.getNom())){ products.getProductList().get(index_aliment).setQuantite(aliment_demande.getQuantite()-quantite);}
                    //aliment_demande.setQuantite(aliment_demande.getQuantite()-quantite); }
                }
            } else{logger.info("","Ce produit n'est pas proposé à la vente.\n");}
        } else {logger.info("", "Ce client n'existe pas, ouvrez d'abord une nouvelle note:\n"); }
    }

    public void cloturer(Clients clients){
        logger.info("","Entrez le nom du client dont vous voulez fermez la note:");
        String nom_client = scan.next();
        if(verification_client_existant(nom_client)) {
            int index_note = getIndexNote("nom_client");
            clients.noteList.get(index_note).facture();
        } else {logger.info("", "Ce client n'existe pas, ouvrez d'abord une nouvelle note:\n"); }
    }

}

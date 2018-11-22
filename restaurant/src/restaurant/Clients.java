package restaurant;
import java.util.LinkedList;
import java.util.Scanner;
import logger.Logger;
import logger.LoggerFactory;

public class Clients {

    private final LinkedList<Note> noteList = new LinkedList<>();
    private Logger logger = LoggerFactory.getLogger("client");
    private Scanner scan = new Scanner(System.in);

    //constructeur
    // public Clients(){ }

    public String toString() {return noteList.toString();}

    LinkedList<Note> getNoteList() { return noteList; }

    //Afficher la liste des notes de client
    void afficherListe(){ for (Note note : this.noteList) {logger.info("OUTPUT", "\t"+note.getNom()+"\n"); } }

    //Ajouter une note à la liste des clients
    void add(Note note) {noteList.add(note);}

    //Verifier si un client se trouve déjà dans la liste
    boolean verification_client_existant(String nom_client){
        boolean verif = false; /*le client n'existe pas par défault*/
        for (Note note_courante : noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0){ verif = true;} //si les chaînes sont identiques, le client existe
        }
        return verif;
    }

    //Récupérer l'index de la note demandée
    int getIndexNote(String nom_client){
        int compteur = 0, index=0;
        for (Note note_courante : noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0){ index=compteur;}//si les chaînes sont identiques, le client existe
            compteur++;
        }
        return index;
    }

    Note selection_client(Clients clients){
        Note note = null;
        logger.info("OUTPUT","\nEntrez le nom du client que vous souhaitez facturer:");
        String nom_client = scan.next();
        logger.info("INPUT","\tNom du client demandé: "+nom_client+".\n");
        if(clients.verification_client_existant(nom_client)) { note = clients.getNoteList().get(getIndexNote(nom_client)); }
        else {logger.error("OUTPUT", "Ce client n'existe pas, ouvrez d'abord une nouvelle note (entrez n)\n"); }
        return note;
    }

    int selection_quantite(Aliment aliment_demande, Products products){
        logger.info("OUTPUT","\nEntrez la quantité du produit souhaité:");
        int quantite = scan.nextInt();logger.info("INPUT","\tQuantité demandée: "+quantite+".\n");
        int index_aliment = products.getIndexAliment(aliment_demande.getNom());
        if(quantite>products.getProductList().get(index_aliment).getQuantite()){
            logger.info("OUTPUT", "Il n'y en a pas assez en stock\n");
            quantite=-1; }
        else {
            int nouveau_stock = (products.getProductList().get(index_aliment).getQuantite())-quantite;
            if(!"café".equals(aliment_demande.getNom())){
                products.getProductList().get(index_aliment).setQuantite(nouveau_stock);
                if(products.getProductList().get(index_aliment).getQuantite()==0){ products.getProductList().remove(products.getProductList().get(index_aliment)); } } }
        return quantite;
    }

}
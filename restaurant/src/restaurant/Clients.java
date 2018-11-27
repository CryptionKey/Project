package restaurant;
import java.util.LinkedList;
import logger.Logger;
import logger.LoggerFactory;

public class Clients {

    private final LinkedList<Note> noteList = new LinkedList<>();
    private Logger logger = LoggerFactory.getLogger("client");

    public String toString() {return noteList.toString();}

    public LinkedList<Note> getNoteList() { return noteList; }

    //Afficher la liste des notes de client
    public void afficherListe() {
        if (this.getNoteList().size() != 0) {
            logger.info("OUTPUT", "Les clients actuellement dans le restaurant sont:\n");
            for (Note note : this.noteList) {
                logger.info("OUTPUT", "\t" + note.getNom() + "\n");
            }
        }else{ logger.info("OUTPUT","Il n'y a aucun client dans le restaurant\n");}
    }

    //Ajouter une note à la liste des clients
    private void add(Note note) {noteList.add(note);}

    public void creer_note(String nom_client) {
        Note note = new Note(nom_client);
        this.add(note);
        logger.info("INPUT", "Nouvelle note créée pour le client "+nom_client+"\n");
    }

    //Verifier si un client se trouve déjà dans la liste
    public Note verification_client_existant(String nom_client, boolean afficher_message){
        Note note_client = null; /*le client n'existe pas par défault*/
        for (Note note_courante : noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0){
                note_client = note_courante;
            } //si les chaînes sont identiques, le client existe
        }
        if(note_client==null && afficher_message) {
            logger.error("OUTPUT", "Ce client n'existe pas, ouvrez d'abord une nouvelle note (entrez n)\n"); }
        return note_client;
    }

    //Récupérer l'index de la note demandée
    public int getIndexNote(String nom_client){
        int compteur = 0, index=0;
        for (Note note_courante : noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0){ index=compteur;}//si les chaînes sont identiques, le client existe
            compteur++;
        }
        return index;
    }

    private boolean verification_stock(int quantite, int index_aliment, Produits produits){
        boolean stock = true; //assez de stock par défaut
        if(quantite> produits.getProductList().get(index_aliment).getQuantite()){
            logger.error("OUTPUT", "Il n'y en a pas assez en stock\n");
            stock=false; }
        return stock;
    }

    private void mise_a_jour_stock(int index_aliment, int quantite, Aliment aliment_demande, Produits produits){
        int nouveau_stock = (produits.getProductList().get(index_aliment).getQuantite())-quantite;
        if(!"café".equals(aliment_demande.getNom())){
            produits.getProductList().get(index_aliment).setQuantite(nouveau_stock);
            if(produits.getProductList().get(index_aliment).getQuantite()==0){
                produits.getProductList().remove(produits.getProductList().get(index_aliment)); } }
    }

    public int selection_quantite(Aliment aliment_demande, Produits produits){
        double quantite = Affichage.verification_nombre("entier",Affichage.output_quantite, Affichage.input_quantite);
        int index_aliment = Produits.getIndexAliment(aliment_demande.getNom(), produits.getProductList());
        if(verification_stock((int)quantite, index_aliment, produits)) {
            mise_a_jour_stock(index_aliment, (int)quantite, aliment_demande, produits); }
        else{ quantite = -1; }
        return (int)quantite;
    }

}
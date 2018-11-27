package restaurant;

import logger.Logger;
import logger.LoggerFactory;
import java.util.LinkedList;

public class Clients {

    private final LinkedList<Note> noteList = new LinkedList<>();
    private Logger logger = LoggerFactory.getLogger("client");


    //Récupérer la liste des notes
    public LinkedList<Note> getNoteList() { return noteList; }

    public String toString() {return noteList.toString();}


    //Afficher la liste des notes de client
    public void afficherListe() {
        if (this.getNoteList().size() != 0) {//la liste n'est pas vide
            logger.info("OUTPUT", "Les clients actuellement dans le restaurant sont:\n");
            for (Note note : this.noteList) {//pour tous les éléments de la liste
                logger.info("OUTPUT", "\t" + note.getNom() + "\n"); } }
    }


    //private void add(Note note) {noteList.add(note);}

    //Créer et ajouter une note à la liste des clients
    public void creer_note(String nom_client) {
        Note note = new Note(nom_client);
        this.getNoteList().add(note);//ajout à la liste
        logger.info("INPUT", "Nouvelle note créée pour le client "+nom_client+"\n");
    }


    //Verifier si un client se trouve déjà dans la liste
    public Note verification_client_existant(String nom_client, boolean afficher_message){
        Note note_client = null; /*le client n'existe pas par défault*/
        for (Note note_courante : noteList) {//on déroule la liste des notes
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
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0) index=compteur;//si les chaînes sont identiques, le client existe
            compteur++;
        }
        return index;
    }


    //Cette méthode ermet de vérifier si nous pouvons servr le client selon la quantité demandée
    private boolean verification_stock(int quantite, int index_aliment, Produits produits){
        boolean stock = true; //assez de stock par défaut
        if(quantite> produits.getProductList().get(index_aliment).getQuantite()){
            logger.error("OUTPUT", "Il n'y en a pas assez en stock\n");
            stock=false; }
        return stock;
    }


    //A chaque commande enregistrer, le stock diminu, nous le mettons donc à jour
    private void mise_a_jour_stock(int index_aliment, int quantite, Aliment aliment_demande, Produits produits){
        int nouveau_stock = (produits.getProductList().get(index_aliment).getQuantite())-quantite;
        if(!"café".equals(aliment_demande.getNom()))/*il n'y a que le café qui reste en quantité infinie*/{
            produits.getProductList().get(index_aliment).setQuantite(nouveau_stock);
            if(produits.getProductList().get(index_aliment).getQuantite()==0){
                produits.getProductList().remove(produits.getProductList().get(index_aliment));
            }
        }
    }


    public int selection_quantite(Aliment aliment_demande, Produits produits){
        double quantite = Affichage.verification_nombre("entier",Affichage.output_quantite, Affichage.input_quantite);
        int index_aliment = produits.getIndexAliment(aliment_demande.getNom());
        if(verification_stock((int)quantite, index_aliment, produits)) {
            mise_a_jour_stock(index_aliment, (int)quantite, aliment_demande, produits); }
        else{ quantite = -1; }
        return (int)quantite;
    }

}
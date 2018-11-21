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

    private LinkedList<Note> getNoteList() { return noteList; }

    //Afficher la liste des notes de client
    void afficherListe(){ for (Note note : this.noteList) {logger.info("OUTPUT", "\t"+note.getNom()+"\n"); } }

    //Ajouter une note à la liste des clients
    private void add (Note note)    {noteList.add(note);}

    //Verifier si un client se trouve déjà dans la liste
    private boolean verification_client_existant(String nom_client){
        boolean verif = false; /*le client n'existe pas par défault*/
        for (Note note_courante : noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0){ verif = true;} //si les chaînes sont identiques, le client existe
        }
        return verif;
    }

    //Récupérer l'index de la note demandée
    private int getIndexNote(String nom_client){
        int compteur = 0, index=0;
        for (Note note_courante : noteList) {
            if(note_courante.getNom().toLowerCase().compareTo(nom_client.toLowerCase())==0){ index=compteur;}//si les chaînes sont identiques, le client existe
            compteur++;
        }
        return index;
    }

    Note ouvrir_note(Clients clients){
        if(clients.getNoteList().size()!=0){
            logger.info("OUTPUT","Les clients actuellement dans le restaurant sont:\n");
            afficherListe();}
        logger.info("OUTPUT","\nDonnez le nom du client dont vous souhaitez ouvrir ou créer la note: ");
        String nom_client = scan.next();logger.info("INPUT","\tNote du client demandée: "+nom_client+".\t");
        Note note = null;
        if(!verification_client_existant(nom_client)) {
            note = new Note(nom_client);  clients.add(note);logger.info("OUTPUT","Nouvelle note créée\n");
        } else {
            if(clients.noteList.get(getIndexNote(nom_client)).getProductList().size()!=0) {
                logger.info("OUTPUT", "Voici la note demandée:\n");
                clients.noteList.get(getIndexNote(nom_client)).afficherListe();
            } else{ logger.error("OUTPUT","Cette note ne contient encore aucun produit.\n");}
        } return note;
    }

    private Note selection_client(Clients clients){
        Note note = null;
        logger.info("OUTPUT","\nEntrez le nom du client que vous souhaitez facturer:");
        String nom_client = scan.next();
        logger.info("INPUT","\tNom du client demandé: "+nom_client+".\n");
        if(clients.verification_client_existant(nom_client)) { note = clients.getNoteList().get(getIndexNote(nom_client)); }
        else {logger.error("OUTPUT", "Ce client n'existe pas, ouvrez d'abord une nouvelle note (entrez n)\n"); }
        return note;
    }

    private Aliment selection_aliment(Products products){
        Aliment aliment = null;
        logger.info("OUTPUT","\nEntrez le nom de l'article que vous souhaitez facturer:");
        String nom_aliment = scan.next();
        logger.info("INPUT","\tNom de l'aliment demandé: "+nom_aliment+".\n");
        if(products.verification_aliment_existant(nom_aliment)) { aliment = products.getProductList().get(products.getIndexAliment(nom_aliment)); }
        else{logger.error("OUTPUT","Ce produit n'est pas proposé à la vente.\n");}
        return aliment;
    }

    private int selection_quantite(Aliment aliment_demande, Products products){
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

    void enregistrer(Clients clients, Products products){
        Note note_client = selection_client(clients);
        if(note_client != null) {
            Aliment aliment_demande = selection_aliment(products);
            if(aliment_demande != null) {
                int quantite = selection_quantite(aliment_demande, products);
                if(quantite != -1){
                    Aliment aliment_ajoute = new Aliment(aliment_demande.getNom(), quantite, aliment_demande.getPrix());
                    clients.getNoteList().get(getIndexNote(note_client.getNom())).getProductList().add(aliment_ajoute); } } }
    }


    Clients cloturer(Clients clients){
        logger.info("OUTPUT","\nEntrez le nom du client dont vous voulez fermez la note: ");
        String nom_client = scan.next();logger.info("INPUT","\tNom du client : "+nom_client+".\n");
        int remise = 0;
        if(verification_client_existant(nom_client)) { String choix;
            do {
                logger.info("OUTPUT", "Souhaitez-vous offrir une remise de 10% à ce client? [o : oui / n : non]");
                choix = scan.next();logger.info("INPUT","Choix: "+choix+".\n");
            }while(!"o".equals(choix) && !"n".equals(choix));
            if("o".equals(choix)){ remise = 1;}   int index_note = getIndexNote(nom_client);
            clients.noteList.get(index_note).facture(remise);
            clients.getNoteList().remove(clients.getNoteList().get(index_note));
        } else {logger.error("OUTPUT", "Ce client n'existe pas, ouvrez d'abord une nouvelle note (entrez n)\n"); }
        return clients;
    }

}
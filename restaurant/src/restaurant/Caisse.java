package restaurant;

import logger.Logger;
import logger.LoggerFactory;
import java.util.Scanner;

class Caisse {

    private Logger logger = LoggerFactory.getLogger("caisse");
    private Scanner scan = new Scanner(System.in);

    Note ouvrir_note(Clients clients){
        if(clients.getNoteList().size()!=0){
            logger.info("OUTPUT","Les clients actuellement dans le restaurant sont:\n");
            clients.afficherListe();}
        logger.info("OUTPUT","\nDonnez le nom du client dont vous souhaitez ouvrir ou créer la note: ");
        String nom_client = scan.next();logger.info("INPUT","\tNote du client demandée: "+nom_client+".\t");
        Note note = null;
        if(!clients.verification_client_existant(nom_client)) {
            note = new Note(nom_client);  clients.add(note);logger.info("OUTPUT","Nouvelle note créée\n");
        } else {
            if(clients.getNoteList().get(clients.getIndexNote(nom_client)).getProductList().size()!=0) {
                logger.info("OUTPUT", "Voici la note demandée:\n");
                clients.getNoteList().get(clients.getIndexNote(nom_client)).afficherListe();
            } else{ logger.error("OUTPUT","Cette note ne contient encore aucun produit.\n");}
        } return note;
    }

    void enregistrer(Clients clients, Products products){
        Note note_client = clients.selection_client(clients);
        if(note_client != null) {
            Aliment aliment_demande = products.selection_aliment(products);
            if(aliment_demande != null) {
                int quantite = clients.selection_quantite(aliment_demande, products);
                if(quantite != -1){
                    Aliment aliment_ajoute = new Aliment(aliment_demande.getNom(), quantite, aliment_demande.getPrix());
                    clients.getNoteList().get(clients.getIndexNote(note_client.getNom())).getProductList().add(aliment_ajoute); } } }
    }

    Clients cloturer(Clients clients){
        logger.info("OUTPUT","\nEntrez le nom du client dont vous voulez fermez la note: ");
        String nom_client = scan.next();logger.info("INPUT","\tNom du client : "+nom_client+".\n");
        int remise = 0;
        if(clients.verification_client_existant(nom_client)) { String choix;
            do {
                logger.info("OUTPUT", "Souhaitez-vous offrir une remise de 10% à ce client? [o : oui / n : non]");
                choix = scan.next();logger.info("INPUT","Choix: "+choix+".\n");
            }while(!"o".equals(choix) && !"n".equals(choix));
            if("o".equals(choix)){ remise = 1;}   int index_note = clients.getIndexNote(nom_client);
            clients.getNoteList().get(index_note).facture(remise);
            clients.getNoteList().remove(clients.getNoteList().get(index_note));
        } else {logger.error("OUTPUT", "Ce client n'existe pas, ouvrez d'abord une nouvelle note (entrez n)\n"); }
        return clients;
    }

}
package restaurant;

import logger.Logger;
import logger.LoggerFactory;
import java.util.Scanner;

public class Caisse {

    private Logger logger = LoggerFactory.getLogger("client");
    private Scanner scan = new Scanner(System.in);

    /*logger.info("OUTPUT","\nDonnez le nom du client dont vous souhaitez ouvrir ou créer la note: ");
        String nom_client = scan.next();logger.info("INPUT","\tNote du client demandée: "+nom_client+".\t");
        Note note = null;
        if(!clients.verification_client_existant(nom_client)) {
            note = new Note(nom_client);  clients.add(note);logger.info("OUTPUT","Nouvelle note créée\n");*/

    void ouvrir_note(Clients clients){
        clients.afficherListe();
        Note note_client = clients.selection_client(clients);
        //String nom_client = note_client.getNom();
        if(note_client == null) {
            clients.creer_note(nom_client);
        } else {
            note_client.afficherListe();
        } //return note_client;
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
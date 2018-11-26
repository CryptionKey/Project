package restaurant;

import logger.Logger;
import logger.LoggerFactory;

public class Caisse {

    private java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
    private Logger logger = LoggerFactory.getLogger("caisse");
    private static double total_TVA = 0;
    private static double total_argent = 0;

    public void ouvrir_note(Clients clients){
        clients.afficherListe();
        String nom_client = Affichage.choix_chaine(Affichage.output_note_client_ouvrir, Affichage.input_note_client_ouvrir);
        Note note_client = clients.verification_client_existant(nom_client, false);
        if(note_client == null) {
            clients.creer_note(nom_client);
        } else {
            note_client.afficherListe(); }
    }

    public void enregistrer(Clients clients, Products products){
        String nom_client = Affichage.choix_chaine(Affichage.output_nom_client_facturer, Affichage.input_nom_client_facturer);
        Note note_client = clients.verification_client_existant(nom_client, true);
        if(note_client != null) {
            String nom_aliment = Affichage.choix_chaine(Affichage.output_nom_aliment_facturer, Affichage.input_nom_aliment_facturer);
            Aliment aliment_demande = products.verification_aliment_existant(nom_aliment, true);
            if(aliment_demande != null) {
                int quantite = clients.selection_quantite(aliment_demande, products);
                if(quantite != -1){
                    Aliment aliment_ajoute = new Aliment(aliment_demande.getNom(), quantite, aliment_demande.getPrix());
                    clients.getNoteList().get(clients.getIndexNote(note_client.getNom())).getProductList().add(aliment_ajoute); } } }
    }

    public void cloturer(Clients clients){
        String nom_client = Affichage.choix_chaine(Affichage.output_nom_client_cloturer, Affichage.input_nom_client_cloturer);
        Note note_client = clients.verification_client_existant(nom_client, true);
        if(note_client!=null) {
            int index_note = clients.getIndexNote(nom_client);
            boolean remise = Note.demander_remise();
            clients.getNoteList().get(index_note).afficher_facture(remise);
            clients.getNoteList().remove(clients.getNoteList().get(index_note)); }
    }

    public void donnees_comptable(){
        logger.info("OUTPUT", String.format("Total des rentrées d'argent: %s\n", df.format(total_argent)));
        logger.info("OUTPUT", String.format("Total de la TVA facturée: %s\n", df.format(total_TVA)));
    }

    static void mise_a_jour_donnees_comptable(boolean remise, double prixHT, double TVA){
        if(!remise){
            Caisse.total_argent = Caisse.total_argent + prixHT; //+TVA?
            Caisse.total_TVA = Caisse.total_TVA + TVA;
        }else{
            Caisse.total_argent = Caisse.total_argent + (prixHT - prixHT*0.1); //+(TVA - TVA*0.1)?
            Caisse.total_TVA= Caisse.total_TVA + (TVA - TVA*0.1);}
    }

}
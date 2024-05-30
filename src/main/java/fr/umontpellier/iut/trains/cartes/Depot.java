package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.Collection;

public class Depot extends CarteAction {
    public Depot() {
        super("Dépôt", 3, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.ajouterAlaMain(joueur.piocher(2));
        Collection<String> cartesValide = new ArrayList<>();
        for (Carte carte : joueur.getMain()){
            cartesValide.add(carte.getNom());
        }

        int compteur = 2;
        while ((!joueur.getMain().isEmpty()) && compteur > 0) {
            String carte = joueur.choisir("Choisissez le nom de la carte à défausser", cartesValide, null, false);
            joueur.ajouterCarteDansDefausse(joueur.getMain().retirer(carte));
            cartesValide.remove(carte);
            compteur--;
        }
    }
}

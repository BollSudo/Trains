package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import java.util.List;

public class Gare extends Carte {
    public Gare() {
        super("Gare", 3, 0, TypeCarte.GARE);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = joueur.choixTuilesVille();
        if (joueur.getJeu().getNbJetonsGare() > 0) { // 30 jetons gares, plus de choix possibles au total que de jetons gare
            String choix = joueur.choisir("Placez un jeton gare", choixPossibles, null, false);
            joueur.placerJetonGare(choix);
        }
        joueur.recevoirUneFerraille();
    }
}

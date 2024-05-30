package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainPostal extends CarteTrainAction {
    public TrainPostal() {
        super("Train postal",4, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c : joueur.getMain()) {
            choixPossibles.add(c.getNom());
        }
        String choix = joueur.choisir("Défaussez autant de cartes de votre main : ", choixPossibles, null, true);
        while (!choix.equals("")) {
            joueur.ajouterCarteDansDefausse(joueur.getMain().retirer(choix));
            joueur.incrementerArgent(1);
            choix = joueur.choisir("Défaussez autant de cartes de votre main : ", choixPossibles, null, true);
        }
    }
}

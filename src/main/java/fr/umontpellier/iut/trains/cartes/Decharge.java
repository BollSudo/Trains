package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Decharge extends CarteAction {
    public Decharge() {
        super("Décharge", 2, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        joueur.removeAllFerrailleDepuisMain();
    }
}

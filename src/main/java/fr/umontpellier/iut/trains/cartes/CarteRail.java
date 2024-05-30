package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class CarteRail extends Carte {

    public CarteRail(String nom, int cout, int valeur) {
        super(nom, cout, valeur, TypeCarte.RAIL);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.incrementerPointsRails();
        joueur.recevoirUneFerraille();
    }
}
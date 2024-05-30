package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Cooperation extends CarteRail {
    public Cooperation() {
        super("Coopération", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        EffetDuration.ANNULER_SURCOUT_RAILS.activer();
    }
}

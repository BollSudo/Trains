package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PontEnAcier extends CarteRail {
    public PontEnAcier() {
        super("Pont en acier", 4, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        EffetDuration.ANNULER_SURCOUT_RIVIERE.activer();
    }
}

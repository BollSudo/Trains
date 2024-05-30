package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Tunnel extends CarteRail {
    public Tunnel() {
        super("Tunnel", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        EffetDuration.ANNULER_SURCOUT_MONTAGNE.activer();
    }
}

package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class VoieSouterraine extends CarteRail {
    public VoieSouterraine() {
        super("Voie souterraine", 7, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        EffetDuration.ANNULER_SURCOUT_ALL.activer();
    }
}

package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Depotoir extends CarteAction {
    public Depotoir() {
        super("Dépotoir", 5, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        EffetDuration.DEPOTOIR.activer();
    }
}

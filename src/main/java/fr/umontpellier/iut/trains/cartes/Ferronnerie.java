package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Ferronnerie extends CarteAction {
    public Ferronnerie() {
        super("Ferronnerie", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        EffetDuration.FERRONNERIE.activer();
    }
}

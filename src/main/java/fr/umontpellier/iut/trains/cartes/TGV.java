package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TGV extends CarteTrainAction {
    public TGV() {
        super("TGV", 2, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        if (joueur.getCartesEnJeu().getCarte("Train omnibus") != null) {
            joueur.incrementerArgent(1);
        }
    }
}

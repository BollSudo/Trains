package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainMatinal extends CarteTrainAction {
    public TrainMatinal() {
        super("Train matinal",5,2);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        EffetDuration.PLACER_ACHAT_SUR_DECK.activer();
    }
}

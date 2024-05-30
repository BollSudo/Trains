package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainDeMarchandises extends CarteTrainAction {
    public TrainDeMarchandises() {
        super("Train de marchandises", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c : joueur.getMain()) {
            if (c.estDeType(TypeCarte.FERRAILLE)) {
                choixPossibles.add("Ferraille");
            }
        }
        String choix = joueur.choisir("Sélectionnez les cartes Ferraille que vous souhaitez mettre en réserve : ",
                choixPossibles, null, true);
        while (!choix.equals("")) {
            joueur.getJeu().getReserve().get("Ferraille").add(joueur.getMain().retirer("Ferraille"));
            joueur.incrementerArgent(1);
            choix = joueur.choisir("Sélectionnez les cartes Ferraille que vous souhaitez mettre en réserve : ",
                    choixPossibles, null, true);
        }
    }
}

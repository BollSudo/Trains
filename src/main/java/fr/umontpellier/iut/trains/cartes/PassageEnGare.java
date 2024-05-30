package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PassageEnGare extends CarteAction {
    public PassageEnGare() {
        super("Passage en gare", 3, 1);
    }

    /**
     * Effet : piocher une carte
     * @param joueur le joueur qui joue la carte
     */
    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.ajouterAlaMain(joueur.piocher());
    }
}

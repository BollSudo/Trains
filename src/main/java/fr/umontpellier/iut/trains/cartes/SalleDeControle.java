package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class SalleDeControle extends CarteAction {
    public SalleDeControle() {
        super("Salle de contrôle", 7, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.ajouterAlaMain(joueur.piocher(3));
    }
}

package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.Collection;

public class Echangeur extends CarteAction {
    public Echangeur() {
        super("Échangeur", 3, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        ListeDeCartes cartes = joueur.getCartesEnJeu();
        Collection<String> cartesValide = new ArrayList<>();
        for (Carte carte : cartes) {
            if (carte.estDeType(TypeCarte.TRAIN)){
                cartesValide.add(carte.getNom());
            }
        }
        String nomCarteChoisi = joueur.choisir("Choisissez une carte TRAIN de la zone de jeu ou passez", cartesValide, null, true);
        joueur.ajouterCarteSurPioche(joueur.getCartesEnJeu().retirer(nomCarteChoisi));
    }
}

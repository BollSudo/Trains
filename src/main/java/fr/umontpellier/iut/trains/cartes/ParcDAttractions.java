package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.Collection;

public class ParcDAttractions extends CarteAction {
    public ParcDAttractions() {
        super("Parc d'attractions", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        ListeDeCartes cartes = joueur.getCartesEnJeu();
        Collection<String> cartesValide = new ArrayList<>();
        for (Carte carte : cartes){
            if (carte.estDeType(TypeCarte.TRAIN)){
                cartesValide.add(carte.getNom());
            }
        }
        String carteChoisi = joueur.choisir("Choisissez une carte TRAIN en jeu ou passez s'il n'y en a aucune", cartesValide, null, false);
        joueur.incrementerArgent(cartes.getCarte(carteChoisi).getValeur());
    }
}

package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.Collection;

public class AtelierDeMaintenance extends CarteAction {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        Collection<String> cartesValide = new ArrayList<>();
        for (Carte carte : joueur.getMain()) {
            if (carte.estDeType(TypeCarte.TRAIN)) {
                cartesValide.add(carte.getNom());
            }
        }
        String nomCarteChoisie = joueur.choisir("Choisissez une carte en main de type TRAIN",
                cartesValide, null, false);
        joueur.ajouterCarteRecue(joueur.getJeu().prendreDansLaReserve(nomCarteChoisie));
    }
}

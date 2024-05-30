package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.*;

public class CentreDeControle extends CarteAction {
    public CentreDeControle() {
        super("Centre de contrôle", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.ajouterAlaMain(joueur.piocher());
        List<Bouton> boutonsCartesValides = new ArrayList<>();
        for (String choix : joueur.getJeu().getListeNomsCartes()) {
            boutonsCartesValides.add(new Bouton(choix, choix));
        }
        String nomCarteChoisis = joueur.choisir("Choisissez le nom d'une carte", null, boutonsCartesValides, false);
        Carte carte = joueur.piocher();
        if (carte != null && nomCarteChoisis.equals(carte.getNom())){
            joueur.ajouterAlaMain(carte);
        } else {
            joueur.ajouterCarteSurPioche(carte);
        }
    }
}

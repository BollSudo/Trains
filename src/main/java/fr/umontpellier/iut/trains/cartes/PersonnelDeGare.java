package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class PersonnelDeGare extends CarteAction {
    public PersonnelDeGare() {
        super("Personnel de gare", 2, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        Bouton boutonPioche = new Bouton("Piocher", "piocher");
        Bouton boutonFerraille = new Bouton("Ferraille", "ferraille");
        Bouton boutonArgent = new Bouton("Argent", "argent");
        List<Bouton> boutons = Arrays.asList(boutonPioche, boutonFerraille, boutonArgent);

        String rep = joueur.choisir("Choisissez une option parmi les trois :", null, boutons, false);

        if (rep.equals("piocher")){
            joueur.ajouterAlaMain(joueur.piocher());
        } else if (rep.equals("ferraille")){
            joueur.removeUneFerrailleDepuisMain();
        } else {
            joueur.incrementerArgent(1);
        }
    }
}

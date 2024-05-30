package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;
import java.util.ArrayList;
import java.util.List;

public class CentreDeRenseignements extends CarteAction {
    public CentreDeRenseignements() {
        super("Centre de renseignements", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        ListeDeCartes cartesDevoilees = new ListeDeCartes(joueur.piocher(4));
        List<Bouton> choixPossibles = new ArrayList<>();
        for (Carte carte : cartesDevoilees){
            choixPossibles.add(new Bouton(carte.getNom(), carte.getNom()));
        }

        String action = joueur.choisir("Choisissez le nom de la carte à mettre en main ou passez", null, choixPossibles, true);
        if (!action.equals("")) {
            Carte c = cartesDevoilees.getCarte(action);
            joueur.ajouterAlaMain(c);
            cartesDevoilees.remove(c);
            choixPossibles.remove(new Bouton(action, action));
        }

        while (!choixPossibles.isEmpty()) {
            String choix = joueur.choisir("Choisissez le nom de la carte à replacer sur la pioche", null, choixPossibles, false);
            Carte c = cartesDevoilees.getCarte(choix);
            joueur.ajouterCarteSurPioche(c);
            cartesDevoilees.remove(c);
            choixPossibles.remove(new Bouton(choix, choix));
        }
    }
}

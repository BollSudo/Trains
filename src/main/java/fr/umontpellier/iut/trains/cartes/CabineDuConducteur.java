package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.Collection;

public class CabineDuConducteur extends CarteAction {
    public CabineDuConducteur() {
        super("Cabine du conducteur", 2, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        boolean fini = false;
        Collection<String> cartesValides = new ArrayList<>();
        for (Carte carte : joueur.getMain()) {
            cartesValides.add(carte.getNom());
        }
        int cartesDefaussees = 0;
        while (!fini){
            String nomCarteChoisis = joueur.choisir("Choisissez une carte en main à défausser", cartesValides,
                    null, true);
            if (nomCarteChoisis.equals("")) {
                fini = true;
            } else {
                cartesDefaussees++;
                cartesValides.remove(nomCarteChoisis);
                joueur.ajouterCarteDansDefausse(joueur.getMain().retirer(nomCarteChoisis));
            }
        }
        joueur.ajouterAlaMain(joueur.piocher(cartesDefaussees));
    }
}

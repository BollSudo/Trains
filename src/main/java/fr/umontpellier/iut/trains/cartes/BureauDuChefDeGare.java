package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class BureauDuChefDeGare extends CarteAction {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);

        ListeDeCartes cartesMain = joueur.getMain();
        List<String> cartesValide = new ArrayList<>();
        for (Carte carte : cartesMain) {
            if (carte.estDeType(TypeCarte.ACTION)) {
                cartesValide.add(carte.getNom());
            }
        }

        String nomCarteChoisis = joueur.choisir("Choisissez une carte ACTION en main", cartesValide, null, true);
        if (!nomCarteChoisis.equals("")) {
            Carte c = joueur.getMain().getCarte(nomCarteChoisis);
            EffetDuration.BUREAU_DU_CHEF_DE_GARE.activer();
            c.jouer(joueur);
            EffetDuration.BUREAU_DU_CHEF_DE_GARE.reset();
            joueur.decrementerArgent(c.getValeur()); //annuler le gain d'argent de la methode jouer (appliquer seulement l'action)
        }
    }
}

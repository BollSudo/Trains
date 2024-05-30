package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class HorairesEstivaux extends CarteAction {
    public HorairesEstivaux() {
        super("Horaires estivaux", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        Bouton boutonOui = new Bouton("Oui", "oui");
        Bouton boutonNon = new Bouton("Non", "non");
        List<Bouton> boutons = Arrays.asList(boutonOui, boutonNon);

        String rep = joueur.choisir("Voulez vous écarter la carte ?", null, boutons, true);

        if (rep.equals("oui")){
            if (EffetDuration.BUREAU_DU_CHEF_DE_GARE.getEtat()) {
                joueur.ecarterCarte(joueur.getCartesEnJeu().retirer("Bureau du chef de gare"));
            } else {
                joueur.ecarterCarte(joueur.getCartesEnJeu().retirer("Horaires estivaux"));
            }
            joueur.incrementerArgent(3);
        }
    }
}

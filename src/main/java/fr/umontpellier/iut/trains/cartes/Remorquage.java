package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Remorquage extends CarteAction {
    public Remorquage() {
        super("Remorquage", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        ListeDeCartes cartes = joueur.getDefausse();
        List<Bouton> cartesValide = new ArrayList<>();
        for (Carte carte : cartes){
            if (carte.estDeType(TypeCarte.TRAIN)){
                cartesValide.add(new Bouton(carte.getNom(), carte.getNom()));
            }
        }
        String nomCarteChoisis = joueur.choisir("Choisissez une carte TRAIN à récupérer", null, cartesValide, false);
        joueur.ajouterAlaMain(joueur.getDefausse().retirer(nomCarteChoisis));
    }
}

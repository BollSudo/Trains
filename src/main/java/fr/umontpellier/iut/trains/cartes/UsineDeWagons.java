package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.*;

public class UsineDeWagons extends CarteTrainAction {
    public UsineDeWagons() {
        super("Usine de wagons", 5,0);
    }

    private int choisirDeMainCarteEcarte(Joueur joueur){
        ListeDeCartes cartes = joueur.getMain();
        Collection<String> cartesValide = new ArrayList<>();
        for (Carte carte : cartes){
            if (carte.estDeType(TypeCarte.TRAIN)){
                cartesValide.add(carte.getNom());
            }
        }
        String nomCarteValide = joueur.choisir("Choisissez une carte TRAIN à écarter", cartesValide, null, false);
        Carte c = joueur.getMain().retirer(nomCarteValide);
        joueur.ecarterCarte(c);
        return c.getCout();
    }

    private Collection<String> getCartesValide(Joueur joueur, int prixMax){
        List<String> nomCartesValides = new ArrayList<>();
        for (String nomCarte : joueur.getJeu().getReserve().keySet()) {
            Carte c = joueur.getJeu().getReserve().get(nomCarte).get(0);
            if (c != null && c.estDeType(TypeCarte.TRAIN) && c.getCout() <= prixMax){
                nomCartesValides.add("ACHAT:"+nomCarte);
            }
        }
        return nomCartesValides;
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        int prixMax = choisirDeMainCarteEcarte(joueur) + 3;
        Collection<String> cartesValide = getCartesValide(joueur, prixMax);

        String nomCartesChoisis = joueur.choisir("Choisissez une nouvelle carte TRAIN", cartesValide,null, false);
        nomCartesChoisis = nomCartesChoisis.split(":")[1];
        Carte carte = joueur.getJeu().prendreDansLaReserve(nomCartesChoisis);

        joueur.getJeu().log("Reçoit " + carte); // affichage dans le log
        joueur.ajouterAlaMain(carte);
    }
}

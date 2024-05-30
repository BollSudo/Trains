package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class HorairesTemporaires extends CarteAction {
    public HorairesTemporaires() {
        super("Horaires temporaires", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        ListeDeCartes autresCartesDevoilees = new ListeDeCartes();
        ListeDeCartes trainsDevoiles = new ListeDeCartes();
        Carte carte;
        while (trainsDevoiles.size()<2 && ((carte = joueur.piocher()) != null)) {
            if (carte.estDeType(TypeCarte.TRAIN)){
                trainsDevoiles.add(carte);
            }
            else {
                autresCartesDevoilees.add(carte);
            }
        }
        joueur.ajouterAlaMain(trainsDevoiles);
        joueur.ajouterCarteDansDefausse(autresCartesDevoilees);
    }
}

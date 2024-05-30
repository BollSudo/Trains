package fr.umontpellier.iut.trains.plateau;

/**
 * Classe représentant une tuile étoile (lieu éloigné)
 */
public class TuileEtoile extends Tuile {
    /**
     * Valeur du lieu éloigné (valeur indiquée sur le plateau)
     */
    private int valeur;

    public TuileEtoile(int valeur) {
        super();
        this.valeur = valeur;
    }

    // FONCTIONS AJOUTEES
    //**************************************************************************************/
    /*                             A NETOYER AVANT DE RENDRE                              */
    //**************************************************************************************/

    @Override
    public int getPoint() {
        return valeur;
    }

    @Override
    public int getSurcout() {
        return super.getSurcout() + valeur;
    }
}

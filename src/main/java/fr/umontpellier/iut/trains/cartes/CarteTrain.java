package fr.umontpellier.iut.trains.cartes;

public abstract class CarteTrain extends Carte {

    protected final boolean action = false;

    public CarteTrain(String nom, int cout, int valeur) {
        super(nom, cout, valeur, TypeCarte.TRAIN);
    }


}

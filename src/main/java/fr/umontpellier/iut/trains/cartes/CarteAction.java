package fr.umontpellier.iut.trains.cartes;

public abstract class CarteAction extends Carte {

    public CarteAction(String nom, int cout, int valeur) {
        super(nom, cout, valeur, TypeCarte.ACTION);
    }
}

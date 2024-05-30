package fr.umontpellier.iut.trains.cartes;

public abstract class CarteVictoire extends Carte {
    private final int pointVictoire;

    public CarteVictoire(String nom, int cout, int valeur, int pv) {
        super(nom, cout, valeur, TypeCarte.VICTOIRE);
        pointVictoire = pv;
    }

    //Carte non jouable

    @Override
    public int getPointVictoire() {
        return pointVictoire;
    }
}

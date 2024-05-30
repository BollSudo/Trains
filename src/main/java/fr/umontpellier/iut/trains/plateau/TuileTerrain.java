package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.cartes.EffetDuration;

/**
 * Classe représentant une tuile plaine, fleuve ou montagne.
 */
public class TuileTerrain extends Tuile {
    /**
     * Type de terrain de la tuile ({@code PLAINE}, {@code FLEUVE} ou {@code MONTAGNE})
     */
    private TypeTerrain type;

    public TuileTerrain(TypeTerrain type) {
        super();
        this.type = type;
    }

    @Override
    public int getSurcout() {
        int surcoutBase;
        if (type==TypeTerrain.FLEUVE && !EffetDuration.ANNULER_SURCOUT_RIVIERE.getEtat()) {
            surcoutBase = 1;
        } else if (type==TypeTerrain.MONTAGNE && !EffetDuration.ANNULER_SURCOUT_MONTAGNE.getEtat()) {
            surcoutBase = 2;
        } else
            surcoutBase = 0;
        return super.getSurcout() + surcoutBase;
    }

}

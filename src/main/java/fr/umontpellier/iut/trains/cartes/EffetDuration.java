package fr.umontpellier.iut.trains.cartes;

public enum EffetDuration {
    ANNULER_SURCOUT_VILLE, ANNULER_SURCOUT_MONTAGNE, ANNULER_SURCOUT_RIVIERE, ANNULER_SURCOUT_RAILS, ANNULER_SURCOUT_ALL,
    PLACER_ACHAT_SUR_DECK, BUREAU_DU_CHEF_DE_GARE, DEPOTOIR, FERRONNERIE;

    private boolean etat;
    private int stacks; //pour effet stackable (Ferronerie)

    //constructeur par default sans params etat=false, stacks=0

    public void activer() {
        etat = true;
        stacks++;
    }
    public void reset() {
        etat = false;
        stacks = 0;
    }
    public boolean getEtat() {
        return etat;
    }
    public int getStacks() {
        return stacks;
    }

    public static void resetAll() {
        for(EffetDuration effet : EffetDuration.values()) {
            effet.reset();
        }
    }


}

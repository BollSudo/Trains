package fr.umontpellier.iut.trains.plateau;

import java.util.*;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.EffetDuration;

public abstract class Tuile {
    /**
     * Liste des tuiles voisines qui sont connectées à la tuile courante (voisines
     * sur le plateau, sauf les tuiles entre lesquelles il y a une barrière
     * infranchissable)
     */
    private ArrayList<Tuile> voisines;
    /**
     * Ensemble des joueurs qui ont posé un rail sur la tuile
     */
    private Set<Joueur> rails;

    public Tuile() {
        this.voisines = new ArrayList<>();
        this.rails = new HashSet<>();
    }

    /**
     * @return {@code true} si la tuile ne contient aucun rail, {@code false} sinon
     */
    public boolean estVide() {
        return rails.isEmpty();
    }

    /**
     * @param joueur le joueur dont on veut déterminer s'il a posé un rail sur la
     *               tuile
     * @return {@code true} si le joueur a posé un rail sur la tuile, {@code false}
     *         sinon
     */
    public boolean hasRail(Joueur joueur) {
        return rails.contains(joueur);
    }

    /**
     * Ajoute un rail du joueur sur la tuile
     * 
     * @param joueur le joueur qui pose un rail sur la tuile
     */
    public void ajouterRail(Joueur joueur) {
        rails.add(joueur);
    }

    /**
     * Ajoute une voisine à la tuile courante, et ajoute la tuile courante comme
     * voisine de la tuile passée en argument.
     * <p>
     * Cette méthode est appelée par la méthode {@code Plateau.makeTuiles()} pour
     * construire le plateau de jeu.
     * 
     * @param tuile la tuile voisine à ajouter
     */
    public void ajouterVoisine(Tuile tuile) {
        voisines.add(tuile);
        tuile.voisines.add(this);
    }

    /**
     * Supprime une tuile de la liste de voisines de {@code this} (et supprime
     * {@code this} des voisines de la tuile passée en paramètres).
     * <p>
     * Cette méthode est appelée par la méthode {@code Plateau.makeTuiles()} pour
     * représenter les barrières infranchissables sur le plateau.
     * 
     * @param tuile la tuile voisine à supprimer
     */
    public void supprimerVoisine(Tuile tuile) {
        voisines.remove(tuile);
        tuile.voisines.remove(this);
    }

    /**
     * @return le nombre de jetons gare posés sur la tuile. Par défaut la fonction
     *         renvoie 0 car on ne peut pas poser de jeton gare sur une tuile
     *         quelconque.
     */
    public int getNbGares() {
        return 0;
    }

    /**
     * @return une représentation de la tuile sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface
     *         graphique)
     */
    public Map<String, Object> dataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("rails", rails.stream().map(Joueur::getCouleur).toArray());
        int nbGares = getNbGares();
        if (nbGares > 0) {
            map.put("nbGares", nbGares);
        }
        return map;
    }

    // FONCTIONS AJOUTEES
    //**************************************************************************************/
    /*                             A NETOYER AVANT DE RENDRE                              */
    //**************************************************************************************/

    public ArrayList<Tuile> getVoisines() {
        return voisines;
    }

    /**
     * Calcul le nombre de points que rapporte la tuile si un joueur possède un jeton rail dessus.
     * @return 0 par défault, car pas toutes les tuiles rapportent des points.
     */
    public int getPoint() {
        return 0;
    }

    /**
     * Pose une gare sur la tuile, par défault ne fait rien, car on ne peut pas
     * poser une gare sur une tuile quelconque.
     */
    public void ajouterGare() {
    }

    /**
     * Vérifie si l'on peut construire une gare sur la tuile. Cette méthode est indépendante du joueur.
     * @return false par défault car seul la TuileVille peut avoir des gares.
     */
    public boolean peutAvoirGare() {
        return false;
    }

    /**
     * Vérifie si l'on peut construire un rail sur la tuile. Cette méthode est indépendante du joueur.
     * @return true par défault car seul la TuileMer ne peut pas avoir de rail.
     */
    public boolean peutAvoirRail() {
        return true;
    }

    public int getSurcout() {
        return EffetDuration.ANNULER_SURCOUT_RAILS.getEtat() ? 0 : rails.size();
        //le surcout doit etre calculer avant la pose de rail du joueur sinon rails.size() fausse
    }
}

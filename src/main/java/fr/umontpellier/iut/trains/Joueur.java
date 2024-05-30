package fr.umontpellier.iut.trains;

import java.util.*;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.*;

public class Joueur {
    /**
     * Le jeu auquel le joueur est rattaché
     */
    private Jeu jeu;
    /**
     * Nom du joueur (pour les affichages console et UI)
     */
    private String nom;
    /**
     * Quantité d'argent que le joueur a (remis à zéro entre les tours)
     */
    private int argent;
    /**
     * Nombre de points rails dont le joueur dispose. Ces points sont obtenus en
     * jouant les cartes RAIL (vertes) et remis à zéro entre les tours
     */
    private int pointsRails;
    /**
     * Nombre de jetons rails disponibles (non placés sur le plateau)
     */
    private int nbJetonsRails;
    /**
     * Liste des cartes en main
     */
    private ListeDeCartes main;
    /**
     * Liste des cartes dans la pioche (le début de la liste correspond au haut de
     * la pile)
     */
    private ListeDeCartes pioche;
    /**
     * Liste de cartes dans la défausse
     */
    private ListeDeCartes defausse;
    /**
     * Liste des cartes en jeu (cartes jouées par le joueur pendant le tour)
     */
    private ListeDeCartes cartesEnJeu;
    /**
     * Liste des cartes reçues pendant le tour
     */
    private ListeDeCartes cartesRecues;
    /**
     * Couleur du joueur (utilisé par l'interface graphique)
     */
    private CouleurJoueur couleur;
    //ATTRIBUT AJOUTE (score actuel du joueur provenant des cartes)
    private int score = 0;
    public Joueur(Jeu jeu, String nom, CouleurJoueur couleur) {
        this.jeu = jeu;
        this.nom = nom;
        this.couleur = couleur;
        argent = 0;
        pointsRails = 0;
        nbJetonsRails = 20;
        main = new ListeDeCartes();
        defausse = new ListeDeCartes();
        pioche = new ListeDeCartes();
        cartesEnJeu = new ListeDeCartes();
        cartesRecues = new ListeDeCartes();

        // créer 7 Train omnibus (non disponibles dans la réserve)
        pioche.addAll(FabriqueListeDeCartes.creerListeDeCartes("Train omnibus", 7));
        // prendre 2 Pose de rails de la réserve
        for (int i = 0; i < 2; i++) {
            pioche.add(jeu.prendreDansLaReserve("Pose de rails"));
        }
        // prendre 1 Gare de la réserve
        pioche.add(jeu.prendreDansLaReserve("Gare"));

        // mélanger la pioche
        pioche.melanger();
        // Piocher 5 cartes en main
        // Remarque : on peut aussi appeler piocherEnMain(5) si la méthode est écrite
        for (int i = 0; i < 5; i++) {
            main.add(pioche.remove(0));
        }

    }

    public String getNom() {
        return nom;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }

    /**
     * Renvoie le score total du joueur
     * <p>
     * Le score total est la somme des points obtenus par les effets suivants :
     * <ul>
     * <li>points de rails (villes et lieux éloignés sur lesquels le joueur a posé
     * un rail)
     * <li>points des cartes possédées par le joueur (cartes VICTOIRE jaunes)
     * <li>score courant du joueur (points marqués en jouant des cartes pendant la
     * partie p.ex. Train de tourisme)
     * </ul>
     * 
     * @return le score total du joueur
     */
    public int getScoreTotal() {
        return score + calculerScoreRails();
    }

    /**
     * Retire et renvoie la première carte de la pioche.
     * <p>
     * Si la pioche est vide, la méthode commence par mélanger toute la défausse
     * dans la pioche.
     *
     * @return la carte piochée ou {@code null} si aucune carte disponible
     */
    public Carte piocher() {
        // À FAIRE - DONE
        if (pioche.isEmpty() && defausse.isEmpty()) {
            return null;
        }
        if (pioche.isEmpty()) {
            defausse.melanger();
            pioche.addAll(defausse);
            defausse.clear();
        }
        Carte cartePiochee = pioche.get(0);
        pioche.remove(0);
        return cartePiochee;
    }

    /**
     * Retire et renvoie les {@code n} premières cartes de la pioche.
     * <p>
     * Si à un moment il faut encore piocher des cartes et que la pioche est vide,
     * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
     * S'il n'y a plus de cartes à piocher la méthode s'interromp et les cartes qui
     * ont pu être piochées sont renvoyées.
     * 
     * @param n nombre de cartes à piocher
     * @return une liste des cartes piochées (la liste peut contenir moins de n
     *         éléments si pas assez de cartes disponibles dans la pioche et la
     *         défausse)
     */
    public List<Carte> piocher(int n) {
        // À FAIRE - DONE
        List<Carte> cartesPiochees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Carte cartePiochee = piocher();
            if (cartePiochee==null){
                break;
            }
            cartesPiochees.add(cartePiochee);
        }

        return cartesPiochees;
    }

    /**
     * Joue un tour complet du joueur
     * <p>
     * Le tour du joueur se déroule en plusieurs étapes :
     * <ol>
     * <li>Initialisation
     * <p>
     * Dans ce jeu il n'y a rien de particulier à faire en début de tour à part un
     * éventuel affichage dans le log.
     * 
     * <li>Boucle principale
     * <p>
     * C'est le cœur de la fonction. Tant que le tour du joueur n'est pas terminé,
     * il faut préparer la liste de tous les choix valides que le joueur peut faire
     * (jouer des cartes, poser des rails, acheter des cartes, etc.), puis demander
     * au joueur de choisir une action (en appelant la méthode {@code choisir}).
     * <p>
     * Ensuite, en fonction du choix du joueur il faut exécuter l'action demandée et
     * recommencer la boucle si le tour n'est pas terminé.
     * <p>
     * Le tour se termine lorsque le joueur décide de passer (il choisit {@code ""})
     * ou lorsqu'il exécute une action qui termine automatiquement le tour (par
     * exemple s'il choisit de recycler toutes ses cartes Ferraille en début de
     * tour)
     * 
     * <li>Finalisation
     * <p>
     * Actions à exécuter à la fin du tour : réinitialiser les attributs
     * du joueur qui sont spécifiques au tour (argent, rails, etc.), défausser
     * toutes les
     * cartes, piocher 5 nouvelles cartes en main, etc.
     * </ol>
     */
    public void jouerTour() {
        // Initialisation
        jeu.log("<div class=\"tour\">Tour de " + toLog() + "</div>");
        // À FAIRE - DONE : compléter l'initialisation du tour si nécessaire (mais possiblement
        // rien de spécial à faire)

        EffetDuration.resetAll();
        boolean finTour = false;
        boolean premiereAction = true;
        List<Tuile> tuilesRails = getTuilesRails();
        // Boucle principale
        while (!finTour) {
            List<String> choixPossibles = new ArrayList<>();

            // À FAIRE - DONE: préparer la liste des choix possibles
            for (Carte c: main) {
                // ajoute les noms de toutes les cartes en main à choixPossibles
                if (!c.getNom().equals("Ferraille") && !c.estDeType(TypeCarte.VICTOIRE)) {
                    choixPossibles.add(c.getNom());
                }
            }
            for (String nomCarte: jeu.getReserve().keySet()) {
                // ajoute les noms des cartes dans la réserve préfixés de "ACHAT:" à choixPossibles
                // A FAIRE : verifier que la pile n'est pas vide et que le joueur peut l'acheter
                if (!(nomCarte.equals("Ferraille")) && (!jeu.getReserve().get(nomCarte).isEmpty()) && (jeu.getReserve().get(nomCarte).get(0).getCout() <= argent)) {
                    choixPossibles.add("ACHAT:" + nomCarte);
                }
            }
            if (premiereAction) {
                choixPossibles.add("Ferraille"); // action spéciale possible si passe son tour
            }
            if (pointsRails > 0 && nbJetonsRails > 0) {
                // Filtrer toutes les tuiles voisines jouables du joueur
                for (Tuile tuileRails : tuilesRails) {
                    for (Tuile voisine : tuileRails.getVoisines()) {
                        if (voisine.peutAvoirRail() && !voisine.hasRail(this)) {
                            //verfier que voisine n'est pas une tuile mer
                            // verifier si assez d'argent pour pose de rail - A FAIRE
                            if ((EffetDuration.ANNULER_SURCOUT_ALL.getEtat() ? 0 : voisine.getSurcout()) <= argent) {
                                choixPossibles.add("TUILE:"+jeu.getTuiles().indexOf(voisine));
                            }
                        }
                    }
                }
            }

            // Choix de l'action à réaliser
            String choix = choisir(String.format("Tour de %s", this.nom), choixPossibles, null, true);

            // À FAIRE: exécuter l'action demandée par le joueur

            //CAS 1 : ACHETER
                // - Acheter des cartes de la réserve (no order and no limit)
                        // Rq : il faut filtrer les cartes qui sont achetables - A FAIRE

                //  Attention : chaque action se fait successivement (pas deux en meme temps)
                // rq : pas obligé de dépenser tout l'argent généré, ni de jouer toutes ses cartes

            //CAS 2 : TERMINER LE TOUR ("")
                // - Rien faire

            //CAS 3 : PASSER SON TOUR + ("SPECIAL") - Valable que si n'a pas fait d'autres actions avant
                // - Action spéciale : remettre toutes Ferrailles de sa main dans pile Ferraille de reserve

            //CAS 4 : POSER UN RAIL ("TUILE")

            //CAS 5 : JOUER
                // - Jouer des cartes de sa main (no order and no limit)
                // Rq : certaines cartes ne sont pas jouables - A FAIRE
                // si Pose de Rails alors doit placer un jeton rail sur le plateau en respectant les règles

            //CAS 1
            if (choix.startsWith("ACHAT:")) {
                // prendre une carte dans la réserve
                String nomCarte = choix.split(":")[1];
                Carte carte = jeu.prendreDansLaReserve(nomCarte);
                if (carte != null) {
                    log("Reçoit " + carte); // affichage dans le log
                    if (EffetDuration.PLACER_ACHAT_SUR_DECK.getEtat()) { //effet train matinal
                        List<Bouton> choixBoutons = new ArrayList<>();
                        choixBoutons.add(new Bouton("Oui", "oui"));
                        choixBoutons.add(new Bouton("Non", "non"));
                        String choixBouton = choisir("Voulez-vous placer cette carte "+nomCarte+
                                " directement au dessus de votre deck", null, choixBoutons, false);
                        if (choixBouton.equals("oui")) {
                            pioche.add(0, carte);
                        }
                        else {
                            cartesRecues.add(carte);
                        }
                    } else {
                        cartesRecues.add(carte);
                    }
                    decrementerArgent(carte.getCout());
                    if (carte.estDeType(TypeCarte.VICTOIRE)) {
                        recevoirUneFerraille();
                        score += carte.getPointVictoire();
                    }
                }
            }

            //CAS 2
            else if (choix.equals("")) {
                // terminer le tour
                finTour = true;
            }

            //CAS 3
            else if (choix.equals("Ferraille")) {
                // passe son tour avec action spéciale - A FAIRE - DONE
                int nbFerraillesDeplacees = removeAllFerrailleDepuisMain();
                log("Réalise l'action spéciale : "+nbFerraillesDeplacees+" Ferraille déposé(s) dans la réserve");
                finTour = true;
            }

            //CAS 4

            else if (choix.startsWith("TUILE:")) {
                Tuile tuileChoisie = jeu.getTuile(placerJetonRail(choix, false));
                tuilesRails.add(tuileChoisie);
            }

            //CAS 5
            else {
                // jouer une carte de la main
                Carte carte = main.retirer(choix);
                log("Joue " + carte); // affichage dans le log
                cartesEnJeu.add(carte); // mettre la carte en jeu
                carte.jouer(this);  // exécuter l'action de la carte

                if (EffetDuration.FERRONNERIE.getEtat() && carte.estDeType(TypeCarte.RAIL)) {
                    incrementerArgent(2 * EffetDuration.FERRONNERIE.getStacks());
                }
            }

            //fin de la premiere action
            premiereAction = false;
        }


        // Finalisation
        // À FAIRE: compléter la finalisation du tour (phase d'ajustement)

        // défausser toutes les cartes
        defausse.addAll(main);
        main.clear();
        defausse.addAll(cartesRecues);
        cartesRecues.clear();
        defausse.addAll(cartesEnJeu);
        cartesEnJeu.clear();

        //main.addAll(piocher(5)); // piocher 5 cartes en main
        ajouterAlaMain(piocher(5));
        //reset
        argent = 0;
        pointsRails = 0;
    }

    /**
     * Attend une entrée de la part du joueur (au clavier ou sur la websocket) et
     * renvoie le choix du joueur.
     * <p>
     * Cette méthode lit les entrées du jeu ({@code Jeu.lireligne()}) jusqu'à ce
     * qu'un choix valide (un élément de {@code choix} ou la valeur d'un élément de
     * {@code boutons} ou éventuellement la chaîne vide si l'utilisateur est
     * autorisé à passer) soit reçu.
     * Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
     * <p>
     * Exemple d'utilisation pour demander à un joueur de répondre à une question
     * par "oui" ou "non" :
     * <p>
     * 
     * <pre>{@code
     * List<String> choix = Arrays.asList("oui", "non");
     * String input = choisir("Voulez-vous faire ceci ?", choix, null, false);
     * }</pre>
     * <p>
     * Si par contre on voulait proposer les réponses à l'aide de boutons, on
     * pourrait utiliser :
     * 
     * <pre>{@code
     * List<String> boutons = Arrays.asList(new Bouton("Oui !", "oui"), new Bouton("Non !", "non"));
     * String input = choisir("Voulez-vous faire ceci ?", null, boutons, false);
     * }</pre>
     * 
     * (ici le premier bouton a le label "Oui !" et envoie la String "oui" s'il est
     * cliqué, le second a le label "Non !" et envoie la String "non" lorsqu'il est
     * cliqué)
     *
     * <p>
     * <b>Remarque :</b> Normalement, si le paramètre {@code peutPasser} est
     * {@code false} le choix
     * {@code ""} n'est pas valide. Cependant s'il n'y a aucun choix proposé (les
     * listes {@code choix} et {@code boutons} sont vides ou {@code null}), le choix
     * {@code ""} est accepté pour éviter un blocage.
     * 
     * @param instruction message à afficher à l'écran pour indiquer au joueur la
     *                    nature du choix qui est attendu
     * @param choix       une collection de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (ou {@code null})
     * @param boutons     une liste d'objets de type {@code Bouton} définis par deux
     *                    chaînes de caractères (label, valeur) correspondant aux
     *                    choix valides attendus du joueur qui doivent être
     *                    représentés par des boutons sur l'interface graphique (le
     *                    label est affiché sur le bouton, la valeur est ce qui est
     *                    envoyé au jeu quand le bouton est cliqué) ou {@code null}
     * @param peutPasser  booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la
     *                    chaîne de caractères vide ({@code ""}) qui signifie qu'il
     *                    désire passer.
     * @return le choix de l'utilisateur (un élement de {@code choix}, ou la valeur
     *         d'un élément de {@code boutons} ou la chaîne vide)
     */
    public String choisir(
            String instruction,
            Collection<String> choix,
            List<Bouton> boutons,
            boolean peutPasser) {
        if (choix == null)
            choix = new ArrayList<>();
        if (boutons == null)
            boutons = new ArrayList<>();

        HashSet<String> choixDistincts = new HashSet<>(choix);
        choixDistincts.addAll(boutons.stream().map(Bouton::valeur).toList());
        if (peutPasser || choixDistincts.isEmpty()) {
            // si le joueur a le droit de passer ou s'il n'existe aucun choix valide, on
            // ajoute "" à la liste des choix possibles
            choixDistincts.add("");
        }

        String entree;
        // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
        while (true) {
            jeu.prompt(instruction, boutons, peutPasser);
            entree = jeu.lireLigne();
            // si une réponse valide est obtenue, elle est renvoyée
            if (choixDistincts.contains(entree)) {
                return entree;
            }
        }
    }

    /**
     * Ajoute un message dans le log du jeu
     * 
     * @param message message à ajouter dans le log
     */
    public void log(String message) {
        jeu.log(message);
    }

    @Override
    public String toString() {
        // Vous pouvez modifier cette fonction comme bon vous semble pour afficher
        // d'autres informations si nécessaire
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("=== %s (%d pts) ===", nom, getScoreTotal()));
        joiner.add(String.format("  Argent: %d  Rails: %d", argent, pointsRails));
        joiner.add("  Cartes en jeu: " + cartesEnJeu);
        joiner.add("  Cartes reçues: " + cartesRecues);
        joiner.add("  Cartes en main: " + main);
        return joiner.toString();
    }

    /**
     * @return une représentation du joueur pour l'affichage dans le log du jeu
     */
    public String toLog() {
        return String.format("<span class=\"joueur %s\">%s</span>", couleur.toString(), nom);
    }

    /**
     * @return une représentation du joueur sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface graphique)
     */
    Map<String, Object> dataMap() {
        return Map.ofEntries(
                Map.entry("nom", nom),
                Map.entry("couleur", couleur),
                Map.entry("scoreTotal", getScoreTotal()),
                Map.entry("argent", argent),
                Map.entry("rails", pointsRails),
                Map.entry("nbJetonsRails", nbJetonsRails),
                Map.entry("main", main.dataMap()),
                Map.entry("defausse", defausse.dataMap()),
                Map.entry("cartesEnJeu", cartesEnJeu.dataMap()),
                Map.entry("cartesRecues", cartesRecues.dataMap()),
                Map.entry("pioche", pioche.dataMap()),
                Map.entry("actif", jeu.getJoueurCourant() == this));
    }

    // FONCTIONS AJOUTEES
    //**************************************************************************************/
    /*                             A NETOYER AVANT DE RENDRE                              */
    //**************************************************************************************/


    /**
     * Action : retire toutes les cartes férrailles de la main du joueur et les placent dans la pile Ferraille de la réserve.
     * @return le nombre de cartes Ferraille mis en réserve.
     */
    public int removeAllFerrailleDepuisMain() {
        int count = main.count("Ferraille");
        for (int i = 0; i < count ; i++) {
            removeUneFerrailleDepuisMain();
        }
        return count;
    }

    public void removeUneFerrailleDepuisMain() {
        Carte f;
        if ((f = main.retirer("Ferraille")) != null) {
            jeu.getReserve().get("Ferraille").add(f);
        }
    }

    /**
     * Action (récursif) : retire toutes les cartes férrailles de la main du joueur et les placent dans la pile Ferraille de la réserve.
     */
    public void removeAllFerrailleDepuisMainRecursif(int value){
        if (value > 0) {
            Carte ferraille = main.getCarte("Ferraille");
            if (ferraille != null){
                ferraille = main.retirer("Ferraille");
                jeu.getReserve().get("Ferraille").add(ferraille);
                removeAllFerrailleDepuisMainRecursif(1);
            }
        }
    }

    /**
     * Action : le joueur place un jeton rail sur la tuile choisie entrée en paramètre sous la forme d'une chaine de
     * caractères, et envoie un message d'information.
     * Pré-requis : la chaîne de caractère est de la forme : "TUILE:"+index de la tuile sur le plateau
     * @return l'index de la tuile choisie
     */
    public int placerJetonRail(String choix, boolean debutTour) {
        int indexTuile = Integer.parseInt(choix.split(":")[1]);
        Tuile tuileChoisie = jeu.getTuile(indexTuile);
        if (!debutTour) {
            decrementerArgent((EffetDuration.ANNULER_SURCOUT_ALL.getEtat() ? 0 : tuileChoisie.getSurcout()));
            pointsRails--;
            if (!tuileChoisie.estVide() && !EffetDuration.ANNULER_SURCOUT_RAILS.getEtat()) {
                recevoirUneFerraille();
            }
        }
        tuileChoisie.ajouterRail(this);
        nbJetonsRails--;
        log("A placé un jeton Rails à "+ Plateau.getCoordonnees(indexTuile));
        return indexTuile;
    }

    public void placerJetonGare(String choix) {
        int indexTuile = Integer.parseInt(choix.split(":")[1]);
        jeu.getTuile(indexTuile).ajouterGare();
        jeu.decrementerNbJetonsGare();
        log("A placé un jeton Gare à "+ Plateau.getCoordonnees(indexTuile));
    }

    public int getNbJetonsRails() {
        return nbJetonsRails;
    }

    public void incrementerPointsRails() {
        pointsRails++;
    }

    /**
     * Le joueur reçoit une carte Ferraille issue de la pile Ferraille de la reserve.
     * Ne fait rien si la pile Ferraille est vide.
     */
    public void recevoirUneFerraille() {
        if (!jeu.getReserve().get("Ferraille").isEmpty() && !EffetDuration.DEPOTOIR.getEtat()) {
            cartesRecues.add(jeu.prendreDansLaReserve("Ferraille"));
        }
    }

    /**
     * Calcul le score du joueur issu des cartes VICTOIRE (et Train de tourisme cas particulier) provenant de la liste
     * de cartes entrée en paramètre.
     */
    public int calculerScoreCartes(ListeDeCartes cartes) {
        int score = 0;
        for (Carte carte : cartes) {
            score += carte.getPointVictoire();
        }
        return score;
    }

    public int calculerScoreRails() {
        int score = 0;
        for (Tuile tuile : jeu.getTuiles()) {
            if (tuile.hasRail(this)) {
                score += tuile.getPoint();
            }
        }
        return score;
    }

    public List<Tuile> getTuilesRails() {
        List<Tuile> tuilesRails = new ArrayList<>();
        for (Tuile tuile : jeu.getTuiles()) {
            if (tuile.hasRail(this)) {
                tuilesRails.add(tuile);
            }
        }
        return tuilesRails;
    }

    public List<String> choixTuilesVille() {
        List<String> choix = new ArrayList<>();
        for (Tuile tuile : jeu.getTuiles()) {
            if (tuile.peutAvoirGare()) {
                choix.add("TUILE:"+jeu.getTuiles().indexOf(tuile));
            }
        }
        return choix;
    }

    public void ajouterAlaMain(List<Carte> cartes) {
        main.addAll(cartes);
    }
    public void ajouterAlaMain(Carte carte) {
        if (carte != null) {
            main.add(carte);
        }
    }
    public void incrementerArgent(int valeur) {
        argent+=valeur;
    }
    public void decrementerArgent(int cout) {
        argent-=cout;
    }
    public ListeDeCartes getMain() {
        return main;
    }
    public Jeu getJeu() {
        return jeu;
    }
    public void ajouterCarteSurPioche(Carte carte){
        if (carte != null) {
            pioche.add(0, carte);
        }
    }
    public void ajouterCarteDansDefausse(Carte carte){
        if (carte != null) {
            defausse.add(carte);
        }
    }

    public void ajouterCarteDansDefausse(List<Carte> cartes){
        defausse.addAll(cartes);
    }

    public ListeDeCartes getCartesEnJeu() {
        return cartesEnJeu;
    }

    public void ajouterCarteRecue(Carte carte){
        if (carte != null) {
            cartesRecues.add(carte);
        }
    }

    public ListeDeCartes getDefausse(){
        return defausse;
    }

    public void ecarterCarte(Carte carte){
        if (carte != null) {
            getJeu().getCartesEcartees().add(carte);
            score -= carte.getPointVictoire();
        }
    }

    public void incrementerScore() {
        score++;
    }
}

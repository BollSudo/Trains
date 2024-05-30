package fr.umontpellier.iut.trains;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.TuileTerrain;
import fr.umontpellier.iut.trains.plateau.TuileVille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JoueurEleveTest extends BaseTestClass {

    @BeforeEach
    public void initTest() {
        setupJeu();
        initialisation();
    }

    // @Disabled
    @Test
    void test_jouerTour_passer_action_speciale() {
        Carte ferraille1 = new Ferraille();
        Carte ferraille2 = new Ferraille();
        Carte omni3 = new TrainOmnibus();
        Carte omni4 = new TrainOmnibus();
        Carte omni5 = new TrainOmnibus();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();
        Carte gare3 = new Gare();
        Carte gare4 = new Gare();
        Carte ferraille5 = new Gare();
        Carte fondPioche = new Ferraille();

        reserve.get("Ferraille").clear();
        addAll(main, ferraille1, ferraille2, omni3, omni4, omni5);
        addAll(pioche, gare1, gare2, gare3, gare4, ferraille5, fondPioche);

        jeu.setInput("Ferraille");
        joueur.jouerTour();

        assertTrue(containsReferences(main, gare1, gare2, gare3, gare4, ferraille5));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, omni3, omni4, omni5));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues));
        assertTrue(containsReferences(reserve.get("Ferraille"), ferraille1, ferraille2));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    //@Disabled
    @Test
    void test_removeAllFerrailleDepuisMainRecursif_aucune_ferraille_dans_main() {
        reserve.get("Ferraille").clear();
        joueur.removeAllFerrailleDepuisMainRecursif(1);

        assertEquals(0, main.size());
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(reserve.get("Ferraille")));
    }

    //@Disabled
    @Test
    void test_removeAllFerrailleDepuisMainRecursif_une_ferraille_dans_main() {
        Carte ferraille = new Ferraille();
        reserve.get("Ferraille").clear();
        main.add(ferraille);
        joueur.removeAllFerrailleDepuisMainRecursif(1);

        assertEquals(0, main.size());
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(reserve.get("Ferraille"), ferraille));
    }

    //@Disabled
    @Test
    void test_removeAllFerrailleDepuisMainRecursif_max_ferraille_dans_main() {
        Carte ferraille1 = new Ferraille();
        Carte ferraille2 = new Ferraille();
        Carte ferraille3 = new Ferraille();
        Carte ferraille4 = new Ferraille();
        Carte ferraille5 = new Ferraille();

        reserve.get("Ferraille").clear();
        addAll(main, ferraille1, ferraille2, ferraille3, ferraille4, ferraille5);
        joueur.removeAllFerrailleDepuisMainRecursif(1);

        assertEquals(0, main.size());
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(reserve.get("Ferraille"), ferraille1, ferraille2, ferraille3, ferraille4, ferraille5));
    }

    //@Disabled
    @Test
    void test_removeAllFerrailleDepuisMain_aucune_ferraille_dans_main() {
        reserve.get("Ferraille").clear();
        joueur.removeAllFerrailleDepuisMain();

        assertEquals(0, main.size());
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(reserve.get("Ferraille")));
    }

    //@Disabled
    @Test
    void test_removeAllFerrailleDepuisMain_une_feraille_dans_main() {
        Carte ferraille = new Ferraille();
        reserve.get("Ferraille").clear();
        main.add(ferraille);
        joueur.removeAllFerrailleDepuisMain();

        assertEquals(0, main.size());
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(reserve.get("Ferraille"), ferraille));
    }

    //@Disabled
    @Test
    void test_removeAllFerrailleDepuisMain_max_feraille_dans_main() {
        Carte ferraille1 = new Ferraille();
        Carte ferraille2 = new Ferraille();
        Carte ferraille3 = new Ferraille();
        Carte ferraille4 = new Ferraille();
        Carte ferraille5 = new Ferraille();

        reserve.get("Ferraille").clear();
        addAll(main, ferraille1, ferraille2, ferraille3, ferraille4, ferraille5);
        joueur.removeAllFerrailleDepuisMain();

        assertEquals(0, main.size());
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(reserve.get("Ferraille"), ferraille1, ferraille2, ferraille3, ferraille4, ferraille5));
    }

    // @Disabled
    @Test
    void test_ajouter_rail_ne_peut_ajouter_une_seule_rail_par_point_rail() {
        tuiles.get(1).ajouterRail(joueur);
        setAttribute(joueur, "pointsRails", 2);
        setAttribute(joueur, "argent", 10);

        jouerTourPartiel("TUILE:2", "TUILE:3", "TUILE:4");

        assertEquals(0, getPointsRails(joueur));
        assertEquals(18, getNbJetonsRails(joueur));
        assertEquals(6, getArgent(joueur));
        checkPlateau(null, List.of(1, 2, 3), null);
    }

    // @Disabled
    @Test
    void test_ajouter_rail_pas_obliger_de_placer_rail_si_a_point_rail_et_reset_fin_de_tour() {
        tuiles.get(1).ajouterRail(joueur);
        setAttribute(joueur, "pointsRails", 5);

        jeu.setInput("");
        joueur.jouerTour();

        assertEquals(0, getPointsRails(joueur));
        assertEquals(20, getNbJetonsRails(joueur));
        checkPlateau(null, List.of(1), null);
    }

    // @Disabled
    @Test
    void test_ajouter_rail_uniquement_sur_les_tuiles_voisines() {
        tuiles.get(1).ajouterRail(joueur);
        setAttribute(joueur, "pointsRails", 5);
        setAttribute(joueur, "argent", 10);

        jouerTourPartiel("TUILE:2", "TUILE:4");

        assertEquals(4, getPointsRails(joueur));
        assertEquals(19, getNbJetonsRails(joueur));
        assertEquals(8, getArgent(joueur));
        checkPlateau(null, List.of(1, 2), null);
    }

    // @Disabled
    @Test
    void test_ajouter_rail_un_seul_jeton_par_tuile_1() {
        tuiles.get(1).ajouterRail(joueur);
        setAttribute(joueur, "pointsRails", 5);
        setAttribute(joueur, "argent", 10);

        jouerTourPartiel("TUILE:2", "TUILE:2");

        assertEquals(4, getPointsRails(joueur));
        assertEquals(19, getNbJetonsRails(joueur));
        assertEquals(8, getArgent(joueur));
        checkPlateau(null, List.of(1, 2), null);
    }

    // @Disabled
    @Test
    void test_ajouter_rail_un_seul_jeton_par_tuile_2() {
        tuiles.get(1).ajouterRail(joueur);
        setAttribute(joueur, "pointsRails", 5);
        setAttribute(joueur, "argent", 10);

        jouerTourPartiel("TUILE:2", "TUILE:1");

        assertEquals(4, getPointsRails(joueur));
        assertEquals(19, getNbJetonsRails(joueur));
        assertEquals(8, getArgent(joueur));
        checkPlateau(null, List.of(1, 2), null);
    }

    // @Disabled
    @Test
    void test_jouerTour_poseDeRail_pas_dispo_si_plus_assez_de_jetons() {
        tuiles.get(1).ajouterRail(joueur);
        setAttribute(joueur, "nbJetonsRails", 1);
        setAttribute(joueur, "pointsRails", 5);
        setAttribute(joueur, "argent", 10);

        jouerTourPartiel("TUILE:2", "TUILE:3");

        assertEquals(8, getArgent(joueur));
        assertEquals(4, getPointsRails(joueur));
        assertEquals(0, getNbJetonsRails(joueur));
        checkPlateau(null, List.of(1,2), null);
    }

    // @Disabled
    @Test
    void test_recevoirUneFerraille_pile_non_vide() {
        Carte f = new Ferraille();

        reserve.get("Ferraille").clear();
        reserve.get("Ferraille").add(f);
        joueur.recevoirUneFerraille();

        assertTrue(containsReferences(cartesRecues, f));
        assertTrue(containsReferences(reserve.get("Ferraille")));
    }

    // @Disabled
    @Test
    void test_recevoirUneFerraille_pile_vide() {

        reserve.get("Ferraille").clear();
        joueur.recevoirUneFerraille();

        assertTrue(containsReferences(cartesRecues));
        assertTrue(containsReferences(reserve.get("Ferraille")));
    }

    // @Disabled
    @Test
    void test_calculerScoreCartes() {
        ListeDeCartes cartes = new ListeDeCartes();
        addAll(cartes, new Appartement(), new Appartement(), new Immeuble(), new GratteCiel(), new TrainDeTourisme(), new TrainDeTourisme());
        int res = joueur.calculerScoreCartes(cartes);
        assertEquals(8, res);
    }

    // @Disabled
    @Test
    void test_calculerScoreRails() {
        tuiles.get(0).ajouterRail(joueur);
        tuiles.get(1).ajouterRail(joueur);
        tuiles.get(8).ajouterRail(joueur);
        tuiles.get(8).ajouterGare();
        tuiles.get(8).ajouterGare();
        tuiles.get(8).ajouterGare();

        assertEquals(12, joueur.calculerScoreRails());
    }


}

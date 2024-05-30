package fr.umontpellier.iut.trains;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.Plateau;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CartesEleveTest extends BaseTestClass {

    @BeforeEach
    public void init() {
        setupJeu();
        initialisation();
        setAttribute(joueur, "argent", 30);
        setAttribute(joueur, "pointsRails", 10);
    }

    private void init_tests_effets_surcout() {
        tuiles.get(20).ajouterRail(joueur);
        tuiles.get(29).ajouterGare();
        tuiles.get(29).ajouterGare();
        tuiles.get(1).ajouterGare();
        tuiles.get(1).ajouterRail(joueurs.get(0));
        tuiles.get(0).ajouterRail(joueurs.get(0));
    }

    //@Disabled
    @Test
    void test_base_ajouter_rails_sans_effet_surcout() {
        init_tests_effets_surcout();

        Carte fondPioche = new Ferraille();
        Carte f1 = reserve.get("Ferraille").get(0);
        Carte f2 = reserve.get("Ferraille").get(1);

        addAll(pioche, fondPioche);

        jouerTourPartiel("TUILE:29", "TUILE:38", "TUILE:10",
                "TUILE:21", "TUILE:22", "TUILE:1", "TUILE:0");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues, f1, f2));
        assertFalse(containsReferences(reserve.get("Ferraille"), f1, f2));
        assertEquals(13, getArgent(joueur));
        assertEquals(3, getPointsRails(joueur));

        //-3-3-2-1-0-3(ferraille)-5(ferraille)
    }

    // @Disabled
    @Test
    void test_voie_souterraine_effet_surcout_aucun() {
        init_tests_effets_surcout();

        Carte c = new VoieSouterraine();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);
        Carte f1 = reserve.get("Ferraille").get(1);
        Carte f2 = reserve.get("Ferraille").get(2);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Voie souterraine", "TUILE:29", "TUILE:38", "TUILE:10",
                "TUILE:21", "TUILE:22", "TUILE:1", "TUILE:0");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f, f1, f2));
        assertFalse(containsReferences(reserve.get("Ferraille"), f, f1, f2));
        assertEquals(30, getArgent(joueur));
        assertEquals(4, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_viaduc_effet_surcout_ville() {
        init_tests_effets_surcout();

        Carte c = new Viaduc();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);
        Carte f1 = reserve.get("Ferraille").get(1);
        Carte f2 = reserve.get("Ferraille").get(2);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Viaduc", "TUILE:29", "TUILE:38", "TUILE:10",
                "TUILE:21", "TUILE:22", "TUILE:1", "TUILE:0");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f, f1, f2));
        assertFalse(containsReferences(reserve.get("Ferraille"), f, f1, f2));
        assertEquals(18, getArgent(joueur));
        assertEquals(4, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_tunnel_effet_surcout_montagne() {
        init_tests_effets_surcout();

        Carte c = new Tunnel();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);
        Carte f1 = reserve.get("Ferraille").get(1);
        Carte f2 = reserve.get("Ferraille").get(2);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Tunnel", "TUILE:29", "TUILE:38", "TUILE:10",
                "TUILE:21", "TUILE:22", "TUILE:1", "TUILE:0");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f, f1, f2));
        assertFalse(containsReferences(reserve.get("Ferraille"), f, f1, f2));
        assertEquals(15, getArgent(joueur));
        assertEquals(4, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_pont_en_acier_effet_surcout_riviere() {
        init_tests_effets_surcout();

        Carte c = new PontEnAcier();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);
        Carte f1 = reserve.get("Ferraille").get(1);
        Carte f2 = reserve.get("Ferraille").get(2);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Pont en acier", "TUILE:29", "TUILE:38", "TUILE:10",
                "TUILE:21", "TUILE:22", "TUILE:1", "TUILE:0");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f, f1, f2));
        assertFalse(containsReferences(reserve.get("Ferraille"), f, f1, f2));
        assertEquals(14, getArgent(joueur));
        assertEquals(4, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_cooperation_effet_surcout_rails() {
        init_tests_effets_surcout();

        Carte c = new Cooperation();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);
        Carte f1 = reserve.get("Ferraille").get(1);
        Carte f2 = reserve.get("Ferraille").get(2);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Coopération", "TUILE:29", "TUILE:38", "TUILE:10",
                "TUILE:21", "TUILE:22", "TUILE:1", "TUILE:0");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReferences(reserve.get("Ferraille"), f, f1, f2));
        assertEquals(15, getArgent(joueur));
        assertEquals(4, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_superposition_effets_surcouts() {
        init_tests_effets_surcout();
        Carte c1 = new Tunnel();
        Carte c2 = new Cooperation();
        Carte c3 = new PontEnAcier();
        Carte c4 = new Viaduc();
        Carte c5 = new VoieSouterraine();

        addAll(main, c1, c2, c3, c4, c5);

        jouerTourPartiel("Tunnel", "Coopération", "Pont en acier", "Viaduc", "Voie souterraine", "TUILE:29", "TUILE:38", "TUILE:10",
                "TUILE:21", "TUILE:22", "TUILE:1", "TUILE:0");

        assertEquals(30, getArgent(joueur));
        assertEquals(8, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_reset_effets_apres_fin_du_tour_du_joueur() {
        init_tests_effets_surcout();
        Carte c1 = new Tunnel();
        Carte c2 = new Cooperation();
        Carte c3 = new PontEnAcier();
        Carte c4 = new Viaduc();
        Carte c5 = new VoieSouterraine();
        addAll(main, c1, c2, c3, c4, c5);
        addAll(joueurs.get(0).getMain(), c1, c2, c3, c4, c5);

        jeu.setInput("Tunnel", "Coopération", "Pont en acier", "Viaduc", "Voie souterraine", "");
        joueur.jouerTour();
        setAttribute(joueur, "argent", 30);
        setAttribute(joueur, "pointsRails", 10);

        jeu.setInput("Tunnel", "Coopération", "Pont en acier", "Viaduc", "Voie souterraine", "");
        joueurs.get(0).jouerTour();

        jouerTourPartiel("TUILE:29", "TUILE:38", "TUILE:10",
                "TUILE:21", "TUILE:22", "TUILE:1", "TUILE:0");

        assertEquals(13, getArgent(joueur));
        assertEquals(3, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_carte_victoire() {
        Carte c1 = reserve.get("Appartement").get(0);
        Carte c2 = reserve.get("Immeuble").get(0);
        Carte c3 = reserve.get("Gratte-ciel").get(0);
        Carte fondPioche = new Ferraille();
        Carte f1 = reserve.get("Ferraille").get(0);
        Carte f2 = reserve.get("Ferraille").get(0);
        Carte f3 = reserve.get("Ferraille").get(0);

        addAll(pioche, fondPioche);

        jouerTourPartiel("ACHAT:Appartement", "ACHAT:Immeuble", "ACHAT:Gratte-ciel");

        assertEquals(14, getArgent(joueur));
        //assertEquals(7, getScore(joueur));
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(pioche, fondPioche));
        assertTrue(containsReferences(cartesRecues, c1, f1, c2, f2, c3, f3));
        assertFalse(containsReferences(reserve.get("Ferraille"), f1, f2, f3));
        assertFalse(containsReferences(reserve.get("Appartement"), c1));
        assertFalse(containsReferences(reserve.get("Immeuble"), c2));
        assertFalse(containsReferences(reserve.get("Gratte-ciel"), c3));
    }

    // @Disabled
    @Test
    void test_train_postal_defausse_tout() {
        Carte c = new TrainPostal();
        Carte fondPioche = new Ferraille();
        Carte t1 = new TrainOmnibus();
        Carte t2 = new TrainOmnibus();
        Carte t3 = new TrainOmnibus();

        addAll(main, c, t1, t2, t3);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train postal", "Train omnibus", "Train omnibus", "Train omnibus", "");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, t1, t2, t3));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(34, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_train_postal_defausse_plusieurs() {
        Carte c = new TrainPostal();
        Carte fondPioche = new Ferraille();
        Carte t1 = new TrainOmnibus();
        Carte t2 = new TrainOmnibus();
        Carte t3 = new TrainOmnibus();

        addAll(main, c, t1, t2, t3);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train postal", "Train omnibus", "Train omnibus", "");

        assertTrue(containsReferences(main, t3));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, t1, t2));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(33, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_train_postal_defausse_rien() {
        Carte c = new TrainPostal();
        Carte fondPioche = new Ferraille();
        Carte t1 = new TrainOmnibus();
        Carte t2 = new TrainOmnibus();
        Carte t3 = new TrainOmnibus();

        addAll(main, c, t1, t2, t3);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train postal", "");

        assertTrue(containsReferences(main, t1, t2, t3));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(31, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_train_postal_defausse_aucune_carte_en_main() {
        Carte c = new TrainPostal();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train postal", "");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(31, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_train_de_marchandises_tout_ferraille() {
        Carte c = new TrainDeMarchandises();
        Carte fondPioche = new Ferraille();
        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte f3 = new Ferraille();
        Carte t1 = new TrainOmnibus();
        reserve.get("Ferraille").clear();

        addAll(main, c, f1, f2, f3, t1);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train de marchandises", "Ferraille", "Ferraille", "Ferraille", "");

        assertTrue(containsSame(main, t1));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(34, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
        assertEquals(3,  reserve.get("Ferraille").size());
        assertTrue(containsReferences(reserve.get("Ferraille"), f1, f2, f3));
    }

    // @Disabled
    @Test
    void test_train_de_marchandises_aucune_ferraille() {
        Carte c = new TrainDeMarchandises();
        Carte fondPioche = new Ferraille();
        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte f3 = new Ferraille();
        Carte t1 = new TrainOmnibus();
        reserve.get("Ferraille").clear();

        addAll(main, c, f1, f2, f3, t1);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train de marchandises", "");

        assertTrue(containsSame(main, f1, f2, f3, t1));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(31, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
        assertEquals(0,  reserve.get("Ferraille").size());
        assertTrue(containsReferences(reserve.get("Ferraille")));
    }

    // @Disabled
    @Test
    void test_train_de_marchandises_aucune_ferraille_dispo() {
        Carte c = new TrainDeMarchandises();
        Carte fondPioche = new Ferraille();
        Carte t1 = new TrainOmnibus();
        reserve.get("Ferraille").clear();

        addAll(main, c, t1);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train de marchandises", "Ferraille", "", "Train omnibus");

        assertTrue(containsSame(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, t1));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(32, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
        assertEquals(0,  reserve.get("Ferraille").size());
        assertTrue(containsReferences(reserve.get("Ferraille")));
    }

    // @Disabled
    @Test
    void test_train_matinal_oui_non_oui() {
        Carte c = new TrainMatinal();
        Carte fondPioche = new Ferraille();
        Carte gare1 = reserve.get("Gare").get(0);
        Carte gare2 = reserve.get("Gare").get(1);
        Carte gare3 = reserve.get("Gare").get(2);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train matinal", "ACHAT:Gare", "oui", "ACHAT:Gare", "non",
                "ACHAT:Gare", "oui");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, gare3, gare1, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, gare2));
        assertEquals(23, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
        assertFalse(containsReferences(reserve.get("Gare"), gare1, gare2, gare3));
    }

    // @Disabled
    @Test
    void test_atelier_de_maintenance_carte_train_en_main_no_pass() {
        Carte c = new AtelierDeMaintenance();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte expr1 = new TrainExpress();
        Carte expr2 = reserve.get("Train express").get(0);

        addAll(main, c, expr1, gare);
        addAll(pioche, fondPioche);


        jouerTourPartiel("Atelier de maintenance", "", "Gare", "Train express");

        assertTrue(containsReferences(main, expr1, gare));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, expr2));
        assertFalse(containsReference(reserve.get("Train express"), expr2));
        assertEquals(30, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
    }

    // @Disabled
    @Test
    void test_atelier_de_maintenance_pas_de_carte_train_en_main_can_pass() {
        Carte c = new AtelierDeMaintenance();
        Carte fondPioche = new Ferraille();
        Carte depot = new Depotoir();

        addAll(main, c, depot);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Atelier de maintenance", "", "Dépotoir");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, depot));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(31, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
    }

    //@Disabled
    @Test
    void test_bureau_avec_horaires_estivaux_non() {
        Carte c = new BureauDuChefDeGare();
        Carte he = new HorairesEstivaux();
        Carte fondPioche = new Ferraille();

        addAll(main, c, he);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Horaires estivaux", "non");

        assertTrue(containsReferences(main, he));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(30, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
        assertTrue(containsReferences(cartesEcartees));
    }

    // @Disabled
    @Test
    void test_bureau_avec_horaires_estivaux_oui() {
        Carte c = new BureauDuChefDeGare();
        Carte he = new HorairesEstivaux();
        Carte fondPioche = new Ferraille();

        addAll(main, c, he);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Horaires estivaux", "oui");

        assertTrue(containsReferences(main, he));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(33, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
        assertTrue(containsReferences(cartesEcartees, c));
    }


    // @Disabled
    @Test
    void test_calculer_score_total() {
        Carte tourisme1 = new TrainDeTourisme();
        Carte tourisme2 = new TrainDeTourisme();
        Carte tourisme3 = new TrainDeTourisme();
        Carte bureau = new BureauDuChefDeGare();
        Carte usine = new UsineDeWagons();
        Carte immeuble = reserve.get("Immeuble").get(0);
        Carte gratteCiel = reserve.get("Gratte-ciel").get(0);
        Carte appart = reserve.get("Appartement").get(0);
        Carte express = reserve.get("Train express").get(0);
        Carte f1 = reserve.get("Ferraille").get(0);
        Carte f2 = reserve.get("Ferraille").get(1);
        Carte f3 = reserve.get("Ferraille").get(2);

        tuiles.get(0).ajouterRail(joueur);
        tuiles.get(1).ajouterRail(joueur);
        tuiles.get(8).ajouterRail(joueur);
        tuiles.get(8).ajouterGare();
        tuiles.get(8).ajouterGare();
        tuiles.get(8).ajouterGare();

        addAll(main, tourisme1, tourisme2, tourisme3, bureau, usine);

        jouerTourPartiel("ACHAT:Immeuble", "ACHAT:Gratte-ciel", "ACHAT:Appartement", "Train de tourisme",
                "Bureau du chef de gare", "Train de tourisme", "Usine de wagons", "Train de tourisme", "ACHAT:Train express");

        assertTrue(containsReferences(main, tourisme3, express));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, tourisme1, bureau, usine));
        assertTrue(containsReferences(cartesRecues, immeuble, f1, gratteCiel, f2, appart, f3));
        assertTrue(containsReferences(cartesEcartees, tourisme2));
        assertEquals(15, getArgent(joueur));
        assertEquals(10, getPointsRails(joueur));
        assertEquals(9, getScore(joueur));
        assertEquals(21, joueur.getScoreTotal());
    }
}

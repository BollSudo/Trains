package fr.umontpellier.iut.trains;

import fr.umontpellier.iut.trains.cartes.Ferraille;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.ListeDeCartes;
import fr.umontpellier.iut.trains.plateau.TuileTerrain;
import fr.umontpellier.iut.trains.plateau.TuileVille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JeuEleveTest extends BaseTestClass {

    @BeforeEach
    public void initTest() {
        setupJeu();
        initialisation();
    }

    // @Disabled
    @Test
    void test_estFini_cas_jetons_rails() {
        setAttribute(jeu.getJoueurCourant(), "nbJetonsRails", 0);
        assertTrue(jeu.estFini());
    }

    // @Disabled
    @Test
    void test_estFini_cas_jetons_gares() {
        setAttribute(jeu, "nbJetonsGare", 0);
        assertTrue(jeu.estFini());
    }

    // @Disabled
    @Test
    void test_estFini_cas_4_piles_vides_sans_ferrailles() {
        reserve.get("Train express").clear();
        reserve.get("Pose de rails").clear();
        reserve.get("Appartement").clear();
        reserve.get("Gare").clear();

        assertTrue(jeu.estFini());
    }

    // @Disabled
    @Test
    void test_estFini_cas_4_piles_vides_avec_ferrailles() {
        reserve.get("Train express").clear();
        reserve.get("Pose de rails").clear();
        reserve.get("Appartement").clear();
        reserve.get("Ferraille").clear();

        assertFalse(jeu.estFini());
    }

    // @Disabled
    @Test
    void test_initPartie_tuiles_valides() {
        jeu.setInput("TUILE:1", "TUILE:2");
        jeu.initPartie();

        assertTrue(jeu.getTuile(1).hasRail(joueurs.get(0)));
        assertTrue(jeu.getTuile(2).hasRail(joueurs.get(1)));
        assertTrue((jeu.getTuile(1) instanceof TuileTerrain) || (jeu.getTuile(1) instanceof TuileVille));
        assertTrue((jeu.getTuile(2) instanceof TuileTerrain) || (jeu.getTuile(2) instanceof TuileVille));
        assertEquals(19, getNbJetonsRails(joueurs.get(1)));
        assertEquals(19, getNbJetonsRails(joueurs.get(0)));
        checkPlateau(List.of(1), List.of(2), null);
    }

    // @Disabled
    @Test
    void test_initPartie_tuiles_valides_mais_meme_choix() {
        jeu.setInput("TUILE:1", "TUILE:1");
        boolean erreur = false;
        try {
            jeu.initPartie();
        } catch (Exception e) {
            erreur = true;
        }
        assertTrue(erreur);
        checkPlateau(List.of(1), null, null);
    }

    // @Disabled
    @Test
    void test_initPartie_tuile_non_valide() {
        jeu.setInput("TUILE:1", "TUILE:0");
        boolean erreur = false;
        try {
            jeu.initPartie();
        } catch (Exception e) {
            erreur = true;
        }
        assertFalse((jeu.getTuile(0) instanceof TuileTerrain) || (jeu.getTuile(0) instanceof TuileVille));
        assertTrue(erreur);
        checkPlateau(List.of(1), null, null);

    }


}


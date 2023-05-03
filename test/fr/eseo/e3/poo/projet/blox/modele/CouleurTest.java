package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CouleurTest {
    @Test
    void testConstructeur() {
        Couleur couleur = Couleur.ROUGE;
        assertEquals(Couleur.ROUGE, couleur, "Couleur incorrecte");
    }

    @ParameterizedTest
    @CsvSource({ "ROUGE, ORANGE, BLEU, VERT, JAUNE, CYAN, VIOLET"})
    void testEqualsTrue(Couleur couleur1, Couleur couleur2, Couleur couleur3,
                        Couleur couleur4, Couleur couleur5, Couleur couleur6,
                        Couleur couleur7) {
        assertTrue(couleur1.equals(couleur1), "Couleur1 différente de couleur1");
        assertTrue(couleur2.equals(couleur2), "Couleur2 différente de couleur2");
        assertTrue(couleur3.equals(couleur3), "Couleur3 différente de couleur3");
        assertTrue(couleur4.equals(couleur4), "Couleur4 différente de couleur4");
        assertTrue(couleur5.equals(couleur5), "Couleur5 différente de couleur5");
        assertTrue(couleur6.equals(couleur6), "Couleur6 différente de couleur6");
        assertTrue(couleur7.equals(couleur7), "Couleur7 différente de couleur7");
    }

    @ParameterizedTest
    @CsvSource({ "ROUGE, ORANGE, BLEU, VERT, JAUNE, CYAN, VIOLET"})
    void testEqualsFalse(Couleur couleur1, Couleur couleur2, Couleur couleur3,
                        Couleur couleur4, Couleur couleur5, Couleur couleur6,
                        Couleur couleur7) {
        assertFalse(couleur1.equals(couleur2), "Couleur1 égale à couleur2");
        assertFalse(couleur1.equals(couleur3), "Couleur1 égale à couleur3");
        assertFalse(couleur1.equals(couleur4), "Couleur1 égale à couleur4");
        assertFalse(couleur1.equals(couleur5), "Couleur1 égale à couleur5");
        assertFalse(couleur1.equals(couleur6), "Couleur1 égale à couleur6");
        assertFalse(couleur1.equals(couleur7), "Couleur1 égale à couleur7");
        assertFalse(couleur2.equals(couleur3), "Couleur2 égale à couleur3");
        assertFalse(couleur2.equals(couleur4), "Couleur2 égale à couleur4");
        assertFalse(couleur2.equals(couleur5), "Couleur2 égale à couleur5");
        assertFalse(couleur2.equals(couleur6), "Couleur2 égale à couleur6");
        assertFalse(couleur2.equals(couleur7), "Couleur2 égale à couleur7");
        assertFalse(couleur3.equals(couleur4), "Couleur3 égale à couleur4");
        assertFalse(couleur3.equals(couleur5), "Couleur3 égale à couleur5");
        assertFalse(couleur3.equals(couleur6), "Couleur3 égale à couleur6");
        assertFalse(couleur3.equals(couleur7), "Couleur3 égale à couleur7");
        assertFalse(couleur4.equals(couleur5), "Couleur4 égale à couleur5");
        assertFalse(couleur4.equals(couleur6), "Couleur4 égale à couleur6");
        assertFalse(couleur4.equals(couleur7), "Couleur4 égale à couleur7");
        assertFalse(couleur5.equals(couleur6), "Couleur5 égale à couleur6");
        assertFalse(couleur5.equals(couleur7), "Couleur5 égale à couleur7");
        assertFalse(couleur6.equals(couleur7), "Couleur6 égale à couleur7");
    }
}

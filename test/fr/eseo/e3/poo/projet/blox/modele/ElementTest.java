package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ElementTest {
    @ParameterizedTest
    @CsvSource({"1, 2", "3, 4", "5, 6",
            "7, 8", "9, 10", "11, 12",
            "13, 14", "15, 16", "17, 18",
            "19, 20", "21, 22", "23, 24"})
    void testConstructeur1(int x, int y) {
        Element element = new Element(new Coordonnees(x, y));
        assertEquals(x, element.getCoordonnees().getAbscisse(), "Abscisse incorrecte");
        assertEquals(y, element.getCoordonnees().getOrdonnee(), "Ordonnée incorrecte");
    }

    @ParameterizedTest
    @CsvSource({"1, 2", "3, 4", "5, 6",
            "7, 8", "9, 10", "11, 12",
            "13, 14", "15, 16", "17, 18",
            "19, 20", "21, 22", "23, 24"})
    void testConstructeur2(int x, int y) {
        Element element = new Element(x, y);
        assertEquals(x, element.getCoordonnees().getAbscisse(), "Abscisse incorrecte");
        assertEquals(y, element.getCoordonnees().getOrdonnee(), "Ordonnée incorrecte");
    }

    @Test
    void testConstructeur3() {
        Element element = new Element(2, 3, Couleur.BLEU);
        assertEquals(2, element.getCoordonnees().getAbscisse(), "Abscisse incorrecte");
        assertEquals(3, element.getCoordonnees().getOrdonnee(), "Ordonnée incorrecte");
        assertEquals(Couleur.BLEU, element.getCouleur(), "Couleur incorrecte");
    }

    @Test
    void testConstructeur4() {
        Element element = new Element(new Coordonnees(2, 3), Couleur.BLEU);
        assertEquals(2, element.getCoordonnees().getAbscisse(), "Abscisse incorrecte");
        assertEquals(3, element.getCoordonnees().getOrdonnee(), "Ordonnée incorrecte");
        assertEquals(Couleur.BLEU, element.getCouleur(), "Couleur incorrecte");
    }

    @ParameterizedTest
    @CsvSource({"1, 2", "3, 4", "5, 6",
            "7, 8", "9, 10", "11, 12",
            "13, 14", "15, 16", "17, 18",
            "19, 20", "21, 22", "23, 24"})
    void testSetCoordonnees(int x, int y) {
        Element element = new Element(0, 0);
        assertEquals(0, element.getCoordonnees().getAbscisse(), "Abscisse incorrecte");
        assertEquals(0, element.getCoordonnees().getOrdonnee(), "Ordonnée incorrecte");
        element.setCoordonnees(new Coordonnees(x, y));
        assertEquals(x, element.getCoordonnees().getAbscisse(), "Abscisse incorrecte");
        assertEquals(y, element.getCoordonnees().getOrdonnee(), "Ordonnée incorrecte");
    }

    // Test set color (ROUGE, ORANGE, BLEU, VERT, JAUNE, CYAN, VIOLET)
    @ParameterizedTest
    @CsvSource({"ROUGE", "ORANGE", "BLEU", "VERT", "JAUNE", "CYAN", "VIOLET"})
    void testSetCouleur(Couleur couleur) {
        Element element = new Element(0, 0);
        assertEquals(Couleur.ROUGE, element.getCouleur(), "Couleur incorrecte");
        element.setCouleur(couleur);
        assertEquals(couleur, element.getCouleur(), "Couleur incorrecte");
    }

    @Test
    void testEquals() {
        Element element1 = new Element(0, 0);
        Element element2 = new Element(0, 0);
        Element element3 = new Element(1, 1);
        Element element4 = new Element(0, 0, Couleur.BLEU);
        Element element5 = new Element(0, 0, Couleur.BLEU);
        Element element6 = new Element(1, 1, Couleur.BLEU);
        Element element7 = new Element(0, 0, Couleur.VERT);
        Element element8 = new Element(1, 1, Couleur.VERT);
        assertTrue(element1.equals(element2), "Les éléments devraient être égaux");
        assertFalse(element1.equals(element3), "Les éléments ne devraient pas être égaux");
        assertFalse(element1.equals(element4), "Les éléments ne devraient pas être égaux");
        assertTrue(element4.equals(element5), "Les éléments devraient être égaux");
        assertFalse(element4.equals(element6), "Les éléments ne devraient pas être égaux");
        assertFalse(element4.equals(element7), "Les éléments ne devraient pas être égaux");
        assertFalse(element4.equals(element8), "Les éléments ne devraient pas être égaux");
    }

    @Test
    void testHashCode() {
        Element element1 = new Element(0, 0);
        Element element2 = new Element(0, 0);
        Element element3 = new Element(1, 1);
        Element element4 = new Element(0, 0, Couleur.BLEU);
        Element element5 = new Element(0, 0, Couleur.BLEU);
        Element element6 = new Element(1, 1, Couleur.BLEU);
        Element element7 = new Element(0, 0, Couleur.VERT);
        Element element8 = new Element(1, 1, Couleur.VERT);
        assertEquals(element1.hashCode(), element2.hashCode(), "Les éléments devraient avoir le même hashcode");
        assertNotEquals(element1.hashCode(), element3.hashCode(), "Les éléments ne devraient pas avoir le même hashcode");
        assertNotEquals(element1.hashCode(), element4.hashCode(), "Les éléments ne devraient pas avoir le même hashcode");
        assertEquals(element4.hashCode(), element5.hashCode(), "Les éléments devraient avoir le même hashcode");
        assertNotEquals(element4.hashCode(), element6.hashCode(), "Les éléments ne devraient pas avoir le même hashcode");
        assertNotEquals(element4.hashCode(), element7.hashCode(), "Les éléments ne devraient pas avoir le même hashcode");
        assertNotEquals(element4.hashCode(), element8.hashCode(), "Les éléments ne devraient pas avoir le même hashcode");
    }
}

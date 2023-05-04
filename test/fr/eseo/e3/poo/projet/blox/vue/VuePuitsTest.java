package fr.eseo.e3.poo.projet.blox.vue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class VuePuitsTest {
    @Test
    public void testConstructeur() {
        Puits puits = new Puits(10, 20);
        VuePuits vuePuits = new VuePuits(puits);
        assertEquals(puits, vuePuits.getPuits());
        assertTrue(vuePuits.getPuits().getLargeur() == 10);
        assertTrue(vuePuits.getPuits().getProfondeur() == 20);
        assertTrue(vuePuits.getTaille() == 20);
        assertTrue(vuePuits.getPreferredSize().width == 10*20);
        assertTrue(vuePuits.getPreferredSize().height == 20*20);
    }

    @ParameterizedTest
    @CsvSource({"10, 20, 10, 10, 20, 10",
                "10, 20, 20, 15, 25, 30",
                "10, 20, 30, 10, 15, 10"})
    public void testMultipleSize(int largeur, int profondeur, int taille,
                                 int deuxiemeLargeur, int deuxiemeProfondeur,
                                 int deuxiemeTaille) {
        Puits puits = new Puits(largeur, profondeur);
        VuePuits vuePuits = new VuePuits(puits, taille);
        assertEquals(puits, vuePuits.getPuits());
        assertTrue(vuePuits.getPuits().getLargeur() == largeur);
        assertTrue(vuePuits.getPuits().getProfondeur() == profondeur);
        assertTrue(vuePuits.getTaille() == taille);
        assertTrue(vuePuits.getPreferredSize().getWidth() == largeur*taille);
        assertTrue(vuePuits.getPreferredSize().getHeight() == profondeur*taille);
        vuePuits.setTaille(deuxiemeTaille);
        assertTrue(vuePuits.getPuits().getLargeur() == largeur);
        assertTrue(vuePuits.getPuits().getProfondeur() == profondeur);
        assertTrue(vuePuits.getTaille() == deuxiemeTaille);
        assertTrue(vuePuits.getPreferredSize().getWidth() == largeur*deuxiemeTaille);
        assertTrue(vuePuits.getPreferredSize().getHeight() == profondeur*deuxiemeTaille);
        Puits deuxiemePuits = new Puits(deuxiemeLargeur, deuxiemeProfondeur);
        vuePuits.setPuits(deuxiemePuits);
        assertEquals(deuxiemePuits, vuePuits.getPuits());
        assertTrue(vuePuits.getPuits().getLargeur() == deuxiemeLargeur);
        assertTrue(vuePuits.getPuits().getProfondeur() == deuxiemeProfondeur);
        assertTrue(vuePuits.getTaille() == deuxiemeTaille);
        assertTrue(vuePuits.getPreferredSize().getWidth() == deuxiemeLargeur*deuxiemeTaille);
        assertTrue(vuePuits.getPreferredSize().getHeight() == deuxiemeProfondeur*deuxiemeTaille);
    }
}

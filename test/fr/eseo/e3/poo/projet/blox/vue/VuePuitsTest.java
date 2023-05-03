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
        assertTrue(vuePuits.getTaille() == 10);
        assertTrue(vuePuits.getSize().width == 10*10);
        assertTrue(vuePuits.getSize().height == 20*10);
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
        assertTrue(vuePuits.getSize().width == largeur*taille);
        assertTrue(vuePuits.getSize().height == profondeur*taille);
        vuePuits.setTaille(deuxiemeTaille);
        assertTrue(vuePuits.getPuits().getLargeur() == largeur);
        assertTrue(vuePuits.getPuits().getProfondeur() == profondeur);
        assertTrue(vuePuits.getTaille() == deuxiemeTaille);
        assertTrue(vuePuits.getSize().width == largeur*deuxiemeTaille);
        assertTrue(vuePuits.getSize().height == profondeur*deuxiemeTaille);
        Puits deuxiemePuits = new Puits(deuxiemeLargeur, deuxiemeProfondeur);
        vuePuits.setPuits(deuxiemePuits);
        assertEquals(deuxiemePuits, vuePuits.getPuits());
        assertTrue(vuePuits.getPuits().getLargeur() == deuxiemeLargeur);
        assertTrue(vuePuits.getPuits().getProfondeur() == deuxiemeProfondeur);
        assertTrue(vuePuits.getTaille() == deuxiemeTaille);
        assertTrue(vuePuits.getSize().width == deuxiemeLargeur*deuxiemeTaille);
        assertTrue(vuePuits.getSize().height == deuxiemeProfondeur*deuxiemeTaille);
    }
}

package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TasTest {
    @Test
    public void testConstructeur() {
        Puits puits = new Puits(15, 20);
        Tas tas = new Tas(puits);
        assertEquals(puits, tas.getPuits());
        assertEquals(20, tas.getElements().length);
        assertEquals(15, tas.getElements()[0].length);
    }

    @Test
    public void testConstructeur2() {
        Puits puits = new Puits(15, 20);
        Tas tas = new Tas(puits, 10);
        assertEquals(puits, tas.getPuits());
        assertEquals(puits.getProfondeur(), tas.getElements().length);
        assertEquals(puits.getLargeur(), tas.getElements()[0].length);
    }

    @Test
    public void testConstructeur3() {
        Puits puits = new Puits(15, 20);
        Tas tas = new Tas(puits, 10, 4);
        assertEquals(puits, tas.getPuits());
        assertEquals(puits.getProfondeur(), tas.getElements().length);
        assertEquals(puits.getLargeur(), tas.getElements()[0].length);
    }
}

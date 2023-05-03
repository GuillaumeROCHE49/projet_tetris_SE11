package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CoordonneesTest {
	@Test
	void testConstructeur() {
		Coordonnees coordonnees = new Coordonnees(1, 2);
		assertEquals(1, coordonnees.getAbscisse(), "Abscisse incorrecte");
		assertEquals(2, coordonnees.getOrdonnee(), "Ordonnée incorrecte");
	}

	@Test
	void testSetAbscisse() {
		Coordonnees coordonnees = new Coordonnees(1, 2);
		coordonnees.setAbscisse(3);
		assertEquals(3, coordonnees.getAbscisse(), "Abscisse incorrecte");
	}

	@Test
	void testSetOrdonnee() {
		Coordonnees coordonnees = new Coordonnees(1, 2);
		coordonnees.setOrdonnee(3);
		assertEquals(3, coordonnees.getOrdonnee(), "Ordonnée incorrecte");
	}

	// Test move
	@ParameterizedTest
	@CsvSource({ "1, 2, 3, 2", "1, 2, 1, 3", "1, 2, 2, 3",
			"1, 2, 3, 3", "1, 2, 2, 4", "1, 2, 3, 4",
			"1, 2, 4, 3", "1, 2, 4, 4", "1, 2, 4, 5",
			"1, 2, 5, 4", "1, 2, 5, 5", "1, 2, 5, 6"})
	void testMove(int x1, int y1, int x2, int y2) {
		Coordonnees coordonnees = new Coordonnees(x1, y1);
		assertEquals(x1, coordonnees.getAbscisse(), "Abscisse incorrecte");
		assertEquals(y1, coordonnees.getOrdonnee(), "Ordonnée incorrecte");
		coordonnees.setAbscisse(x2);
		coordonnees.setOrdonnee(y2);
		assertEquals(x2, coordonnees.getAbscisse(), "Abscisse incorrecte");
		assertEquals(y2, coordonnees.getOrdonnee(), "Ordonnée incorrecte");
	}

	@ParameterizedTest
	@CsvSource({ "1, 2, 3, 2", "1, 2, 1, 3", "1, 2, 2, 3",
			"1, 2, 3, 3", "1, 2, 2, 4", "1, 2, 3, 4",
			"1, 2, 4, 3", "1, 2, 4, 4", "1, 2, 4, 5",
			"1, 2, 5, 4", "1, 2, 5, 5", "1, 2, 5, 6"})
	void testEqualsFalse(int x1, int y1, int x2, int y2) {
		Coordonnees coordonnees = new Coordonnees(x1, y1);
		Coordonnees coordonnees2 = new Coordonnees(x2, y2);
		assertFalse(coordonnees.equals(coordonnees2), "Coordonnées égales");
	}

	@Test
	void testToString() {
		Coordonnees coordonnees = new Coordonnees(1, 2);
		assertEquals("(1, 2)", coordonnees.toString(), "Coordonnées incorrectes");
	}

	@Test
	void testHashCode() {
		Coordonnees coordonnees = new Coordonnees(1, 2);
		assertEquals(Objects.hash(1, 2), coordonnees.hashCode(), "Hash incorrect");
	}

	@Test
	void testEqualsTrue() {
		Coordonnees coordonnees = new Coordonnees(1, 2);
		Coordonnees coordonnees2 = new Coordonnees(1, 2);
		assertTrue(coordonnees.equals(coordonnees2), "Coordonnées différentes");
	}
}

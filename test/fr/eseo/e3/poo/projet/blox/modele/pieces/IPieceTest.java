package fr.eseo.e3.poo.projet.blox.modele.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class IPieceTest {
	@Test
	void testConstructeur() {
		IPiece piece = new IPiece(new Coordonnees(0, 0), Couleur.BLEU);
		List<Element> elements = piece.getElements();
		assertEquals(4, elements.size());
		assertEquals(new Element(new Coordonnees(0, 0), Couleur.BLEU), elements.get(0));
		assertEquals(new Element(new Coordonnees(0, 1), Couleur.BLEU), elements.get(1));
		assertEquals(new Element(new Coordonnees(0, -1), Couleur.BLEU), elements.get(2));
		assertEquals(new Element(new Coordonnees(0, -2), Couleur.BLEU), elements.get(3));
	}

	// Test with multiple coordinates and colors
	@ParameterizedTest
	@CsvSource({ "0, 0, BLEU", "1, 1, ROUGE",
				"2, 2, VERT", "3, 3, JAUNE",
				"4, 4, CYAN"})
	void testConstructeurMultiple(int x, int y, Couleur couleur) {
		IPiece piece = new IPiece(new Coordonnees(x, y), couleur);
		List<Element> elements = piece.getElements();
		assertEquals(4, elements.size());
		assertEquals(new Element(new Coordonnees(x, y), couleur), elements.get(0));
		assertEquals(new Element(new Coordonnees(x, y+1), couleur), elements.get(1));
		assertEquals(new Element(new Coordonnees(x, y-1), couleur), elements.get(2));
		assertEquals(new Element(new Coordonnees(x, y-2), couleur), elements.get(3));
	}

	// Test with null coordinates
	@Test
	void testConstructeurNullCoordonnees() {
		assertThrows(NullPointerException.class, () -> {
			new IPiece(null, Couleur.BLEU);
		});
	}

	// Test toString
	@Test
	void testToString() {
		IPiece piece = new IPiece(new Coordonnees(0, 0), Couleur.BLEU);
		assertEquals("IPiece :\n\t(0, 0) - BLEU\n\t(0, 1) - BLEU\n\t(0, -1) - BLEU\n\t(0, -2) - BLEU\n",
				piece.toString());
	}

	// Test setPuits
	@Test
	void testSetPuits() {
		IPiece piece = new IPiece(new Coordonnees(0, 0), Couleur.BLEU);
		Puits puits = new Puits();
		piece.setPuits(puits);
		assertEquals(puits, piece.getPuits());
	}

	// Test parametisé de la méthode deplacerDe
	@ParameterizedTest
	@CsvSource({ "0, 0, 0, 0, 0, 0", "0, 0, 1, 0, 1, 0", "0, 0, 0, 1, 0, 1", "0, 0, 1, 0, 1, 0",
				 "0, 0, 1, 1, 1, 1", "0, 0, -1, 1, -1, 1", "0, 0, -1, 1, -1, 1"})
	void testDeplacerDe(int x, int y, int dx, int dy, int xFinal, int yFinal) {
		IPiece piece = new IPiece(new Coordonnees(x, y), Couleur.BLEU);
		try{
			piece.deplacerDe(dx, dy);
		} catch (BloxException e) {
			e.printStackTrace();
		}
		List<Element> elements = piece.getElements();
		assertEquals(4, elements.size());
		assertEquals(new Element(new Coordonnees(xFinal, yFinal), Couleur.BLEU), elements.get(0));
		assertEquals(new Element(new Coordonnees(xFinal, yFinal+1), Couleur.BLEU), elements.get(1));
		assertEquals(new Element(new Coordonnees(xFinal, yFinal-1), Couleur.BLEU), elements.get(2));
		assertEquals(new Element(new Coordonnees(xFinal, yFinal-2), Couleur.BLEU), elements.get(3));
	}

	@Test
	void testDeplacerDeErreur(){
		IPiece piece = new IPiece(new Coordonnees(0, 0), Couleur.BLEU);
		assertThrows(IllegalArgumentException.class, () -> {
			piece.deplacerDe(1, -2);
		});
	}

	// Tester la methode tourner
	@Test
	void testTourner() {
		IPiece piece = new IPiece(new Coordonnees(0, 0), Couleur.BLEU);
		piece.tourner(true);
		List<Element> elements = piece.getElements();
		assertEquals(4, elements.size());
		assertEquals(new Element(new Coordonnees(0, 0), Couleur.BLEU), elements.get(0));
		assertEquals(new Element(new Coordonnees(-1, 0), Couleur.BLEU), elements.get(1));
		assertEquals(new Element(new Coordonnees(1, 0), Couleur.BLEU), elements.get(2));
		assertEquals(new Element(new Coordonnees(2, 0), Couleur.BLEU), elements.get(3));
		// Sens anti-horaire
		piece.tourner(false);
		elements = piece.getElements();
		assertEquals(4, elements.size());
		assertEquals(new Element(new Coordonnees(0, 0), Couleur.BLEU), elements.get(0));
		assertEquals(new Element(new Coordonnees(0, 1), Couleur.BLEU), elements.get(1));
		assertEquals(new Element(new Coordonnees(0, -1), Couleur.BLEU), elements.get(2));
		assertEquals(new Element(new Coordonnees(0, -2), Couleur.BLEU), elements.get(3));
	}
}

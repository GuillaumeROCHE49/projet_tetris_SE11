package fr.eseo.e3.poo.projet.blox.modele.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class OPieceTest {
	@Test
	void testConstructeur() {
		OPiece piece = new OPiece(new Coordonnees(0, 0), Couleur.BLEU);
		List<Element> elements = piece.getElements();
		assertEquals(4, elements.size());
		assertEquals(new Element(new Coordonnees(0, 0), Couleur.BLEU), elements.get(0));
		assertEquals(new Element(new Coordonnees(1, 0), Couleur.BLEU), elements.get(1));
		assertEquals(new Element(new Coordonnees(0, -1), Couleur.BLEU), elements.get(2));
		assertEquals(new Element(new Coordonnees(1, -1), Couleur.BLEU), elements.get(3));
	}

	// Test with multiple coordinates and colors
	@ParameterizedTest
	@CsvSource({ "0, 0, BLEU", "1, 1, ROUGE",
				"2, 2, VERT", "3, 3, JAUNE",
				"4, 4, CYAN"})
	void testConstructeurMultiple(int x, int y, Couleur couleur) {
		OPiece piece = new OPiece(new Coordonnees(x, y), couleur);
		List<Element> elements = piece.getElements();
		assertEquals(4, elements.size());
		assertEquals(new Element(new Coordonnees(x, y), couleur), elements.get(0));
		assertEquals(new Element(new Coordonnees(x + 1, y), couleur), elements.get(1));
		assertEquals(new Element(new Coordonnees(x, y - 1), couleur), elements.get(2));
		assertEquals(new Element(new Coordonnees(x + 1, y - 1), couleur), elements.get(3));
	}

	// Test with null coordinates
	@Test
	void testConstructeurNullCoordonnees() {
		assertThrows(NullPointerException.class, () -> {
			new OPiece(null, Couleur.BLEU);
		});
	}

	// Test toString
	@Test
	void testToString() {
		OPiece piece = new OPiece(new Coordonnees(0, 0), Couleur.BLEU);
		assertEquals("OPiece :\n\t(0, 0) - BLEU\n\t(1, 0) - BLEU\n\t(0, -1) - BLEU\n\t(1, -1) - BLEU\n",
				piece.toString());
	}

	@Test
	void testSetPuits() {
		IPiece piece = new IPiece(new Coordonnees(0, 0), Couleur.BLEU);
		Puits puits = new Puits();
		piece.setPuits(puits);
		assertEquals(puits, piece.getPuits());
	}

	// Test parametisé de la méthode deplacerDe
	@ParameterizedTest
	@CsvSource({ "0, 0, 0, 0, 0, 0", "0, 0, 1, 0, 1, 0", "0, 0, 0, 1, 0, 1", "0, 0, -1, 0, -1, 0",
				"0, 0, 0, -1, 0, -1", "0, 0, 1, 1, 1, 1", "0, 0, -1, 1, -1, 1", "0, 0, -1, -1, -1, -1",
				"0, 0, 1, -1, 1, -1" })
	void testDeplacerDe(int x, int y, int dx, int dy, int xFinal, int yFinal) {
		OPiece piece = new OPiece(new Coordonnees(x, y), Couleur.BLEU);
		piece.deplacerDe(dx, dy);
		List<Element> elements = piece.getElements();
		assertEquals(4, elements.size());
		assertEquals(new Element(new Coordonnees(xFinal, yFinal), Couleur.BLEU), elements.get(0));
		assertEquals(new Element(new Coordonnees(xFinal + 1, yFinal), Couleur.BLEU), elements.get(1));
		assertEquals(new Element(new Coordonnees(xFinal, yFinal - 1), Couleur.BLEU), elements.get(2));
		assertEquals(new Element(new Coordonnees(xFinal + 1, yFinal - 1), Couleur.BLEU), elements.get(3));
	}

	@Test
	void testDeplacerDeErreur(){
		OPiece piece = new OPiece(new Coordonnees(0, 0), Couleur.BLEU);
		assertThrows(IllegalArgumentException.class, () -> {
			piece.deplacerDe(1, 2);
		});
	}
}

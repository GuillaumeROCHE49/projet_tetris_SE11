package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class PuitsTest {
    @Test
    void testConstructeur0() {
        Puits puits = new Puits();
        assertEquals(10, puits.getLargeur(), "Largeur par défaut");
        assertEquals(20, puits.getProfondeur(), "Profondeur par défaut");
    }

    // Test avec des valeurs valides compris entre les bornes
    @ParameterizedTest
    @CsvSource({"5, 15", "10, 20", "15, 25",
                "5, 20", "10, 15", "15, 20",
                "5, 25", "10, 25", "15, 25"})
    void testConstructeur1(int largeur, int profondeur) {
        Puits puits = new Puits(largeur, profondeur);
        assertEquals(largeur, puits.getLargeur(), "Largeur");
        assertEquals(profondeur, puits.getProfondeur(), "Profondeur");
    }

    // Test avec des valeurs valides en dehors des bornes
    @ParameterizedTest
    @CsvSource({"4, 10", "3, 14", "16, 26", "20, 30"})
    void testConstructeur2(int largeur, int profondeur) {
        // Renvoie IllegalArgumentException si les dimensions sont incorrectes
        assertThrows(IllegalArgumentException.class, () -> {
            Puits puits = new Puits(largeur, profondeur);
        });
    }

    // Test setPieceSuivante
    @Test
    void testSetPieceSuivante() {
        Puits puits = new Puits();
        Piece piece1 = new OPiece(new Coordonnees(0, 0), Couleur.BLEU);
        puits.setPieceSuivante(piece1);
        assertEquals(piece1, puits.getPieceSuivante(), "Piece suivante");
        assertEquals(null, puits.getPieceActuelle(), "Piece actuelle");
        Piece piece2 = new IPiece(new Coordonnees(0, 0), Couleur.ROUGE);
        puits.setPieceSuivante(piece2);
        assertEquals(piece2, puits.getPieceSuivante(), "Piece suivante");
        assertEquals(piece1, puits.getPieceActuelle(), "Piece actuelle");
        Piece piece3 = new OPiece(new Coordonnees(0, 0), Couleur.BLEU);
        puits.setPieceSuivante(piece3);
        assertEquals(piece3, puits.getPieceSuivante(), "Piece suivante");
        assertEquals(piece2, puits.getPieceActuelle(), "Piece actuelle");
        // Test la position de la pièce actuelle
        assertEquals(5, puits.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse(), "Abscisse");
        assertEquals(-4, puits.getPieceActuelle().getElements().get(0).getCoordonnees().getOrdonnee(), "Ordonnee");
    }
}

package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.TPiece;

public class UsineDePieceTest {
    // Test si les pièces sont bien générées et sont de type IPiece, OPiece ou TPiece
    @Test
    void testAleatoireMode() {
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
        Piece piece = UsineDePiece.genererPiece();
        assertEquals(true, 
                    (piece instanceof IPiece ||
                    piece instanceof OPiece ||
                    piece instanceof TPiece),
                    "Type de pièce");
        // Test position (2, 3)
        assertEquals(2, piece.getElements().get(0).getCoordonnees().getAbscisse(), "Position X");
        assertEquals(3, piece.getElements().get(0).getCoordonnees().getOrdonnee(), "Position Y");
    }

    // Test le mode semi aleatoire
    @Test
    void testSemiAleatoireMode() {
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
        Piece piece = UsineDePiece.genererPiece();
        assertEquals(true, 
                    (piece instanceof IPiece ||
                    piece instanceof OPiece ||
                    piece instanceof TPiece),
                    "Type de pièce");
        // Test la couleur
        if (piece instanceof IPiece) {
            assertEquals(Couleur.ORANGE, piece.getElements().get(0).getCouleur(), "Couleur");
        } else if (piece instanceof OPiece) {
            assertEquals(Couleur.ROUGE, piece.getElements().get(0).getCouleur(), "Couleur");
        } else if (piece instanceof TPiece) {
            assertEquals(Couleur.BLEU, piece.getElements().get(0).getCouleur(), "Couleur");
        }
        // Test position (2, 3)
        assertEquals(2, piece.getElements().get(0).getCoordonnees().getAbscisse(), "Position X");
        assertEquals(3, piece.getElements().get(0).getCoordonnees().getOrdonnee(), "Position Y");
    }

    @Test
    void testGenerateur(){
        UsineDePiece.setMode(UsineDePiece.CYCLIC);
        Piece piece = UsineDePiece.genererPiece();
        assertEquals(true, 
                    (piece instanceof IPiece ||
                    piece instanceof OPiece ||
                    piece instanceof TPiece),
                    "Type de pièce");
        // Test position (2, 3)
        assertEquals(2, piece.getElements().get(0).getCoordonnees().getAbscisse(), "Position X");
        assertEquals(3, piece.getElements().get(0).getCoordonnees().getOrdonnee(), "Position Y");
        // Test la couleur
        assertEquals(Couleur.ROUGE, piece.getElements().get(0).getCouleur(), "Couleur");
    }
}

package fr.eseo.e3.poo.projet.blox.modele;

import java.util.List;
import java.util.Random;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.TPiece;

public class UsineDePiece {
    // Cette classe à pour but de créer des pièces de trois manières différentes :
    // - aléatoirement
    // - type aléatoire (même couleur)
    // - de manière cyclique
    //
    // Nous utiliserons java.util.Random.nextInt pour générer des nombres aléatoires.

    public static final int ALEATOIRE_COMPLET = 0;
    public static final int ALEATOIRE_PIECE = 1;
    public static final int CYCLIC = 2;

    private static final Random RANDOM = new Random();

    private static int mode = ALEATOIRE_COMPLET;
    private static int pieceIndex = 0;

    private UsineDePiece() {
        // Constructeur privé pour empêcher l'instanciation de la classe
    }

    public static void setMode(int mode) {
        if (mode < 0 || mode > 2)
            throw new IllegalArgumentException("Mode incorrect");
        UsineDePiece.mode = mode;
    }

    public static Piece genererPiece() {
        Piece piece = null;
        switch (UsineDePiece.mode) {
            case ALEATOIRE_COMPLET:
                piece = genererPieceAleatoire();
                break;
            case ALEATOIRE_PIECE:
                piece = genererPieceAleatoireMemeCouleur();
                break;
            case CYCLIC:
                piece = genererPieceCyclique();
                break;
            default:
                break;
        }
        return piece;
    }

    private static Piece genererPieceAleatoire() {
        
        Couleur couleur = Couleur.values()[RANDOM.nextInt(7)];
        List<Piece> pieces = List.of(new OPiece(new Coordonnees(2, 3), couleur),
                                    new IPiece(new Coordonnees(2, 3), couleur),
                                    new TPiece(new Coordonnees(2, 3), couleur));
        return pieces.get(RANDOM.nextInt(pieces.size()));

    }

    private static Piece genererPieceAleatoireMemeCouleur() {
        List<Piece> pieces = List.of(new OPiece(new Coordonnees(2, 3), Couleur.ROUGE),
                                    new IPiece(new Coordonnees(2, 3), Couleur.ORANGE),
                                    new TPiece(new Coordonnees(2, 3), Couleur.BLEU));
        return pieces.get(RANDOM.nextInt(pieces.size()));
    }

    private static Piece genererPieceCyclique() {
        List<Piece> pieces = List.of(new OPiece(new Coordonnees(2, 3), Couleur.ROUGE),
                                    new IPiece(new Coordonnees(2, 3), Couleur.ORANGE),
                                    new TPiece(new Coordonnees(2, 3), Couleur.BLEU));
        Piece piece = pieces.get(pieceIndex);
        pieceIndex = (pieceIndex + 1) % pieces.size();
        return piece;
    }
}

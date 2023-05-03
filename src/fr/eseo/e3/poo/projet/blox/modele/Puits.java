package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class Puits {
    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 20;
    private static final int LARGEUR_MIN = 5, LARGEUR_MAX = 15;
    private static final int PROFONDEUR_MIN = 15, PROFONDEUR_MAX = 25;

    private int largeur;
    private int profondeur;
    private Piece pieceActuelle;
    private Piece pieceSuivante;
    
    public Puits() {
        this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT);
    }

    public Puits(int largeur, int profondeur) {
        // Renvoie IllegalArgumentException si les dimensions sont incorrectes
        if (largeur < LARGEUR_MIN || largeur > LARGEUR_MAX ||
            profondeur < PROFONDEUR_MIN || profondeur > PROFONDEUR_MAX)
            throw new IllegalArgumentException("Dimensions incorrectes");
        this.largeur = largeur;
        this.profondeur = profondeur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getProfondeur() {
        return this.profondeur;
    }

    public Piece getPieceActuelle() {
        return this.pieceActuelle;
    }

    public Piece getPieceSuivante() {
        return this.pieceSuivante;
    }

    public void setPieceSuivante(Piece pieceSuivante) {
        if (this.pieceSuivante != null) {
            this.pieceActuelle = this.pieceSuivante;
            this.pieceActuelle.setPosition(LARGEUR_PAR_DEFAUT/2, -4);
            this.pieceSuivante = pieceSuivante;
        } else {
            this.pieceSuivante = pieceSuivante;
        }
    }

    public void setLargeur(int largeur) {
        if (largeur < LARGEUR_MIN || largeur > LARGEUR_MAX)
            throw new IllegalArgumentException("Largeur incorrecte");
        this.largeur = largeur;
    }

    public void setProfondeur(int profondeur) {
        if (profondeur < PROFONDEUR_MIN || profondeur > PROFONDEUR_MAX)
            throw new IllegalArgumentException("Profondeur incorrecte");
        this.profondeur = profondeur;
    }

    @Override
    public String toString() {
        String str = "Puits : Dimension "+ this.largeur +" x " +
                                            this.profondeur + "\n";

        String pieceStr = (this.pieceActuelle == null) ? "<aucune>\n" :
                                        this.pieceActuelle.toString();
        str += "Piece Actuelle : " + pieceStr;

        pieceStr = (this.pieceSuivante == null) ? "<aucune>\n" :
                                        this.pieceSuivante.toString();
        str += "Piece Suivante : " + pieceStr;

        return str;
    }
}

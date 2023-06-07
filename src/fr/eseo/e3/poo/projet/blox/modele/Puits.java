package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Puits {
    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 20;
    private static final int LARGEUR_MIN = 5;
    private static final int LARGEUR_MAX = 15;
    private static final int PROFONDEUR_MIN = 15;
    private static final int PROFONDEUR_MAX = 25;

    public static final String MODIFICATION_PIECE_ACTUELLE = "PieceActuelle";
    public static final String MODIFICATION_PIECE_SUIVANTE = "PieceSuivante";

    private int largeur;
    private int profondeur;
    private Piece pieceActuelle;
    private Piece pieceSuivante;
    private Tas tas;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
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
        this.tas = new Tas(this);
    }

    public Puits(int largeur, int profondeur, int nbElements, int nbLignes) throws IllegalArgumentException {
        this(largeur, profondeur);
        this.tas = new Tas(this, nbElements, nbLignes);
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

    public void setPieceSuivante(Piece piece) {
        if (this.pieceSuivante != null) { // Si une pièce suivante existe déjà
            Piece oldPieceActuel = this.pieceActuelle; // On enregistre l'ancienne piece actuelle
            this.pieceActuelle = this.pieceSuivante;
            this.pieceActuelle.setPosition(this.largeur/2, -4); // On la place au dessus du puits
            this.pieceActuelle.setPuits(this); // On lui donne le puits
           
             this.pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE,
                    oldPieceActuel, this.pieceActuelle);
        } 
        Piece p= this.pieceSuivante;
        this.pieceSuivante = piece; // Si aucune pièce suivante
        this.pieceSuivante.setPuits(this); // On lui donne le puits
           
        this.pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE,p, piece);
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

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void setTas(Tas tas) {
        this.tas = tas;
    }

    public Tas getTas() {
        return this.tas;
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

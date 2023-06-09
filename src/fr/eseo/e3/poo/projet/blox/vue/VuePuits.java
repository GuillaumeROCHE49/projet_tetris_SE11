package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import fr.eseo.e3.poo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.poo.projet.blox.controleur.PieceRotation;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import java.awt.Color;

public class VuePuits extends JPanel implements PropertyChangeListener {
    public static final int TAILLE_PAR_DEFAUT = 15;

    private Puits puits;
    private VuePiece vuePiece;
    private final VueTas vueTas;

    private int taille;
    private int largeur;
    private int profondeur;

    public VuePuits(Puits puits) {
        this(puits, VuePuits.TAILLE_PAR_DEFAUT);
    }

    public VuePuits(Puits puits, int taille) {
        this.puits = puits;
        this.setTaille(taille);
        this.setPuits(puits);
        this.setBackground(java.awt.Color.WHITE);
        this.vuePiece = null;
        this.puits.addPropertyChangeListener(this);
        // Ajouter PieceDeplacement comme MouseMotionListener
        PieceDeplacement pieceDeplacement = new PieceDeplacement(this);
        PieceRotation pieceRotation = new PieceRotation(this);
        this.addMouseMotionListener(pieceDeplacement);
        this.addMouseListener(pieceDeplacement);
        this.addMouseWheelListener(pieceDeplacement);
        this.addMouseListener(pieceRotation);
        // Ajout de la vueTas
        this.vueTas = new VueTas(this);
    }

    public Puits getPuits() {
        return this.puits;
    }

    public int getTaille() {
        return this.taille;
    }

    public VuePiece getVuePiece() {
        return this.vuePiece;
    }

    public VueTas getVueTas() {
        return this.vueTas;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        this.largeur = taille * puits.getLargeur();
        this.profondeur = taille * puits.getProfondeur();
        this.setPreferredSize(new Dimension(
            this.largeur, this.profondeur));
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
        this.largeur = this.getTaille() * puits.getLargeur();
        this.profondeur = this.getTaille() * puits.getProfondeur();
        this.getPuits().addPropertyChangeListener(this);
        this.setPreferredSize(new Dimension(
            this.largeur, this.profondeur));
    }

    private void setVuePiece(VuePiece vuePiece) {
        this.vuePiece = vuePiece;
    }

    @Override
    protected void paintComponent(Graphics g){
        // Methode de Romain C. pour eviter le bug 3.7.2
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g.create();

        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, puits.getLargeur()*taille, puits.getProfondeur()*taille);

        g2D.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i<= puits.getLargeur(); i++){
            int x = i * taille;
            g2D.drawLine(x, 0, x, puits.getProfondeur() * taille);
        }
        for (int i = 0; i <= puits.getProfondeur(); i++) {
            int y = i * taille;
            g2D.drawLine(0, y, puits.getLargeur() * taille, y);
        }

        if (getVuePiece()!= null) {
            getVuePiece().afficherPiece(g2D);
        }

        if (this.vueTas!= null){
            this.vueTas.afficher(g2D);
        }

        g2D.dispose();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        // Verifier que l'evenement concerne PieceActuelle
        if (event.getPropertyName().equals(Puits.MODIFICATION_PIECE_ACTUELLE)) {
            // Mettre a jour la vue de la piece
            Piece piece = (Piece)event.getNewValue();
            if(piece!=null)
                this.setVuePiece(new VuePiece(piece, this.getTaille()));
            this.repaint();
        }
    }
}

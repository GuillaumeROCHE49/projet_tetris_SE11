package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

// Cette classe est la vue graphique du puits.
// Elle est une sous classe de javax.swing.Jpanel

import javax.swing.JPanel;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class VuePuits extends JPanel {
    public static final int TAILLE_PAR_DEFAUT = 20;

    private Puits puits;
    private VuePiece vuePiece;

    private int taille;
    private int largeur;
    private int profondeur;

    public VuePuits(Puits puits) {
        this(puits, VuePuits.TAILLE_PAR_DEFAUT);
    }

    public VuePuits(Puits puits, int taille) {
        this.puits = puits;
        this.taille = taille;
        this.setTaille(taille);
        this.setPuits(puits);
        this.setBackground(java.awt.Color.WHITE);
        this.vuePiece = null;
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

    public void setTaille(int taille) {
        this.taille = taille;
        this.largeur = taille * puits.getLargeur();
        this.profondeur = taille * puits.getProfondeur();
        this.setPreferredSize(new Dimension(
            this.largeur, this.profondeur));
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
        this.largeur = taille * puits.getLargeur();
        this.profondeur = taille * puits.getProfondeur();
        this.setPreferredSize(new Dimension(
            this.largeur, this.profondeur));
    }

    public void setVuePiece(VuePiece vuePiece) {
        this.vuePiece = vuePiece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner la grille using 
        g.setColor(java.awt.Color.LIGHT_GRAY);
        for (int i = 0; i < this.puits.getLargeur(); i++) {
            // Dessiner les lignes verticales
            g.drawLine(i * this.taille, 0, i * this.taille, this.getPreferredSize().height);
        }
        for (int i = 0; i < this.puits.getProfondeur(); i++) {
            // Dessiner les lignes horizontales
            g.drawLine(0, i * this.taille, this.getPreferredSize().width, i * this.taille);
        }
        Graphics2D g2D = (Graphics2D)g.create();
        // Dessiner les pieces
        if (this.vuePiece != null) {
            this.vuePiece.afficherPiece(g2D);
        }
        g2D.dispose();
    }
}

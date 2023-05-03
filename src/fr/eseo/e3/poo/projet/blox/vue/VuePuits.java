package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Graphics;
import java.awt.Graphics2D;

// Cette classe est la vue graphique du puits.
// Elle est une sous classe de javax.swing.Jpanel

import javax.swing.JPanel;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class VuePuits extends JPanel {
    public static final int TAILLE_PAR_DEFAUT = 10;

    private Puits puits;
    private int taille;

    public VuePuits(Puits puits) {
        this(puits, VuePuits.TAILLE_PAR_DEFAUT);
    }

    public VuePuits(Puits puits, int taille) {
        this.puits = puits;
        this.taille = taille;
        this.setSize(taille * puits.getLargeur(),
                     taille * puits.getProfondeur());
        this.setLayout(null);
        this.setOpaque(false);
        this.setVisible(true);
    }

    public Puits getPuits() {
        return this.puits;
    }

    public int getTaille() {
        return this.taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        this.setSize(taille * this.puits.getLargeur(),
                     taille * this.puits.getProfondeur());
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
        this.setSize(taille * puits.getLargeur(),
                     taille * puits.getProfondeur());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* appel vers super pour remplir le fond du JPanel */
        /*Le paramètre g est copie en utilisant la méthode copie()
        * puis converti en instance de Graphics2D grâce à
        * un transtypage (cast) explicite.
        */
        Graphics2D g2D = (Graphics2D)g.create();
        /* Nous utiliserons l'instance de Graphics2D*/
        /*Puis nous liberons la memoire*/
        g2D.dispose();
        }
        
}

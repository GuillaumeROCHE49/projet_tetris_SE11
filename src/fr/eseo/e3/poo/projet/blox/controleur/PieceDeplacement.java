package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.MouseAdapter;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class PieceDeplacement extends MouseAdapter {
    private VuePuits vuePuits;
    private Puits puits;

    private int derniereColonne = -1;

    public PieceDeplacement(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
    }

    public void mouseMoved(java.awt.event.MouseEvent event) {
        if(this.puits.getPieceActuelle() != null) {
            int colonne = event.getX() / this.vuePuits.getTaille();
            // Si c'est la première fois qu'on bouge la souris
            if(this.derniereColonne == -1) {
                this.derniereColonne = colonne;
            }
            // Si on a changé de colonne
            if(this.derniereColonne != colonne) {
                // Les deplacement se font entre 1 et -1
                try{
                    int deplacement = colonne - this.derniereColonne;
                    this.puits.getPieceActuelle().deplacerDe(deplacement, 0);
                    this.derniereColonne = colonne;
                    vuePuits.repaint();
                }catch(Exception e){
                    System.out.println("Erreur deplacement");
                }
                
            }
        }
    }

    public void mouseEntered(java.awt.event.MouseEvent event) {
        this.derniereColonne = event.getX() / this.vuePuits.getTaille();
    }

    public void mouseWheelMoved(java.awt.event.MouseWheelEvent event) {
        if(this.puits.getPieceActuelle() != null) {
            int rotation = event.getWheelRotation();
            if (rotation > 0){
                try{
                    this.puits.getPieceActuelle().deplacerDe(0, rotation);
                }catch(BloxException e){
                    System.out.println("Erreur deplacement");
                }
            }
            vuePuits.repaint();
        }        
    }
}

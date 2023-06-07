package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.MouseAdapter;

import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class PieceRotation extends MouseAdapter {
    private VuePuits vuePuits;
    private Puits puits;

    public PieceRotation(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
    }

    // Si les boutons de la souries sont pressés
    public void mouseClicked(java.awt.event.MouseEvent event) {
        if(this.puits.getPieceActuelle() != null) {
            try{
                if (SwingUtilities.isLeftMouseButton(event)) {
                    try{
                        this.puits.getPieceActuelle().tourner(false);
                    } catch(BloxException e){
                        System.out.println("Erreur rotation: " + e.getMessage());
                    }
                } else if (SwingUtilities.isRightMouseButton(event)) {
                    try{
                        this.puits.getPieceActuelle().tourner(true);
                    } catch(BloxException e){
                        System.out.println("Erreur rotation: " + e.getMessage());
                    }
                }
            } catch(Exception e){
                System.out.println("Erreur rotation");
            }
            vuePuits.repaint();
        }        
    }
}

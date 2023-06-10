package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import javafx.scene.paint.Color;

public class PanneauInformation extends JPanel implements PropertyChangeListener{
    private final Puits puits;

    private VuePiece vuePiece;

    public PanneauInformation(Puits puits){
        super();
        this.puits = puits;
        this.puits.addPropertyChangeListener(this);
        // Definir la taille du panneau
        this.setPreferredSize(new java.awt.Dimension(70, 70));
    }

    @Override
    protected void paintComponent(Graphics g){
        // Methode de Romain C. pour eviter le bug 3.7.2
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g.create();
        
        // Dessiner la piece suivante
        if (this.vuePiece != null) {
            this.vuePiece.afficherPiece(g2D);
        }
        g2D.dispose();
    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent event){
        if (event.getPropertyName().equals(Puits.MODIFICATION_PIECE_SUIVANTE)){
            // Mettre a jour la vue de la piece
            Piece piece = (Piece)event.getNewValue();
            this.vuePiece = new VuePiece(piece, 10);
            this.repaint();
        }
    }
}

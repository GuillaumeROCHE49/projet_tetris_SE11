package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ListIterator;

public class VuePiece {
    public static final double MULTIPLIER_TEINTE = 0.5;

    private final int taille;
    private final Piece piece;

    public VuePiece(Piece piece, int taille) {
        this.taille = taille;
        this.piece = piece;
    }

    public Color teinte(Color couleur) {
        int r = couleur.getRed();
        int g = couleur.getGreen();
        int b = couleur.getBlue();
        int alpha = couleur.getAlpha();
        
        r = (int)(r + (255 - r) * VuePiece.MULTIPLIER_TEINTE);
        g = (int)(g + (255 - g) * VuePiece.MULTIPLIER_TEINTE);
        b = (int)(b + (255 - b) * VuePiece.MULTIPLIER_TEINTE);

        return new Color(r, g, b, alpha);
    }

    public void afficherPiece(Graphics2D g2D) {
        List<Element> elements = this.piece.getElements();
        ListIterator<Element> iterateur = elements.listIterator();
        while(iterateur.hasNext()) {
            Element element = iterateur.next(); // element courant

            // Changement de la couleur si l'element est le premier de la liste
            Color couleur = element.getCouleur().getCouleurPourAffichage();
            if(iterateur.nextIndex() == 1){
                couleur = this.teinte(element.getCouleur().getCouleurPourAffichage());
            }

            // Affichage de l'element
            g2D.setColor(couleur);
            g2D.fill3DRect(element.getCoordonnees().getAbscisse() * this.taille,
                           element.getCoordonnees().getOrdonnee() * this.taille,
                           this.taille, this.taille, true);
        }
    }
}

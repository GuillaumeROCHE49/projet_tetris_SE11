package fr.eseo.e3.poo.projet.blox.controleur;

import javax.swing.JFrame;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class GraviteTest {
    private void createWindows(){
        // Creer une vuePuits --------------------------------
        VuePuits vuePuits = new VuePuits(new Puits(10, 20), 30);        
        
        // Generer la piece ----------------------------------
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
        Piece piece = UsineDePiece.genererPiece();
        // Ajouter la piece au puits
        vuePuits.getPuits().setPieceSuivante(piece);

        // Ajouter une deuxieme piece
        Piece piece2 = UsineDePiece.genererPiece();
        vuePuits.getPuits().setPieceSuivante(piece2);

        piece.setPosition(4, -4);

        Gravite gravite = new Gravite(vuePuits);
        gravite.setPeriodicite(500);

        // Creer une frame --------------------------------
        JFrame jFrame = new JFrame();
        // Ajoute un titre à la fenêtre
        jFrame.setTitle("Mouvement de la piece");
        jFrame.add(vuePuits);
        jFrame.pack();  // Redimensionne la fenêtre
        // Centre la fenêtre sur l'écran
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        
        // S'assurer que la fenêtre est bien fermer quand on clique sur la croix
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GraviteTest test = new GraviteTest();
        test.createWindows();
    }
}

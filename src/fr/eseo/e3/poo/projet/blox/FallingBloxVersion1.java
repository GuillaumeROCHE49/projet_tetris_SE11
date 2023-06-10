package fr.eseo.e3.poo.projet.blox;
import javax.swing.JFrame;

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class FallingBloxVersion1 {
    private void createWindows(){
        Puits puit = new Puits(10, 20);
        // Creer une vuePuits --------------------------------
        VuePuits vuePuits = new VuePuits(puit, 30);

        // Creer un panneau d'information ---------------------
        PanneauInformation panneauInformation = new PanneauInformation(puit);

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
        jFrame.setTitle("Falling Blox");
        // Ajouter les composants (vuePuits au centre et panneauInformation à droite)
        jFrame.add(vuePuits);
        jFrame.add(panneauInformation, "East");
        jFrame.pack();  // Redimensionne la fenêtre
        // Centre la fenêtre sur l'écran
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        // La fenetre ne peut pas être redimensionnée
        jFrame.setResizable(false);
        
        // S'assurer que la fenêtre est bien fermer quand on clique sur la croix
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        FallingBloxVersion1 test = new FallingBloxVersion1();
        test.createWindows();
    }
}

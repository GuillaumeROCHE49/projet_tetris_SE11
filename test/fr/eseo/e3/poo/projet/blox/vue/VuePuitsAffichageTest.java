package fr.eseo.e3.poo.projet.blox.vue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class VuePuitsAffichageTest {
    public VuePuitsAffichageTest() {
        testPanneau();
    }

    private void testConstructeurPuits() {
        JFrame jFrame = new JFrame();
        // Ajoute un titre à la fenêtre
        jFrame.setTitle("Puits");
        // Creer un objet VuePuits avec une instance de Puits
        VuePuits vuePuits = new VuePuits(new Puits(10, 20));
        jFrame.add(vuePuits);
        jFrame.pack();  // Redimensionne la fenêtre
        // Centre la fenêtre sur l'écran
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        // S'assurer que la fenêtre est bien fermer quand on clique sur la croix
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void testConstructeurPuitsTaille() {
        JFrame jFrame = new JFrame();
        // Ajoute un titre à la fenêtre
        jFrame.setTitle("Puits et taille");
        // Creer un objet VuePuits avec une instance de Puits et une taille
        VuePuits vuePuits = new VuePuits(new Puits(10, 15), 40);
        jFrame.add(vuePuits);
        jFrame.pack();  // Redimensionne la fenêtre
        // Centre la fenêtre sur l'écran
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        // S'assurer que la fenêtre est bien fermer quand on clique sur la croix
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void testPuitsEtPieces() {
        // Generer la piece
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
        Piece piece = UsineDePiece.genererPiece();
        // Creer une vuePuits
        VuePuits vuePuits = new VuePuits(new Puits(10, 20), 30);
        // Creer une vuePiece
        VuePiece vuePiece = new VuePiece(piece, vuePuits.getTaille());
        // Ajouter la vuePiece dans la vuePuits
        //vuePuits.setVuePiece(vuePiece);
        JFrame jFrame = new JFrame();
        // Ajoute un titre à la fenêtre
        jFrame.setTitle("Puits et pièce");
        jFrame.add(vuePuits);
        jFrame.pack();  // Redimensionne la fenêtre
        // Centre la fenêtre sur l'écran
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        // S'assurer que la fenêtre est bien fermer quand on clique sur la croix
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void testPuitsEtPiecesAuto() {
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

        // Creer une frame --------------------------------
        JFrame jFrame = new JFrame();
        // Ajoute un titre à la fenêtre
        jFrame.setTitle("Puits et pièce auto");
        jFrame.add(vuePuits);
        jFrame.pack();  // Redimensionne la fenêtre
        // Centre la fenêtre sur l'écran
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        // S'assurer que la fenêtre est bien fermer quand on clique sur la croix
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void testPanneau() {
        this.testConstructeurPuits();
        this.testConstructeurPuitsTaille();
        this.testPuitsEtPiecesAuto();
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable () {
            @Override
            public void run() {
                new VuePuitsAffichageTest();
            }
        });
    }
}

package fr.eseo.e3.poo.projet.blox.modele;


public enum Couleur {
    // The enum Couleur is used to store the colors of the blocks.
    ROUGE(java.awt.Color.RED),
    ORANGE(java.awt.Color.ORANGE),
    BLEU(java.awt.Color.BLUE),
    VERT(java.awt.Color.GREEN),
    JAUNE(java.awt.Color.YELLOW),
    CYAN(java.awt.Color.CYAN),
    VIOLET(java.awt.Color.MAGENTA);

    private final java.awt.Color couleurPourAffichage;

    private Couleur(java.awt.Color couleurPourAffichage) {
        this.couleurPourAffichage = couleurPourAffichage;
    }

    public java.awt.Color getCouleurPourAffichage() {
        return this.couleurPourAffichage;
    }
}

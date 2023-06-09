package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Tas;
import java.awt.Color;
import java.awt.Graphics2D;

public class VueTas {
    // Constante MULTIPLIER_NUANCE
    public static final double MULTIPLIER_NUANCE = 0.4;

    private final VuePuits vuePuits;
    private final Tas tas;

    public VueTas(VuePuits vuePuits){
        this.vuePuits = vuePuits;
        this.tas = vuePuits.getPuits().getTas();
    }

    public Color nuance (Color couleur){
        int red = (int) (couleur.getRed() * (1 - MULTIPLIER_NUANCE));
        int green = (int) (couleur.getGreen() * (1 - MULTIPLIER_NUANCE));
        int blue = (int) (couleur.getBlue() * (1 - MULTIPLIER_NUANCE));
        return new Color(red, green, blue);
    }

    public void afficher(Graphics2D g2d){
        for (int i = 0; i < this.tas.getPuits().getProfondeur(); i++) {
            for (int j = 0; j < this.tas.getPuits().getLargeur(); j++) {
                if (this.tas.getElements()[i][j] != null) {
                    g2d.setColor(nuance(
                        this.tas.getElements()[i][j].getCouleur().getCouleurPourAffichage()));
                    g2d.fill3DRect(j * this.vuePuits.getTaille(),
                                   i * this.vuePuits.getTaille(),
                                   this.vuePuits.getTaille(),
                                   this.vuePuits.getTaille(), true);
                }
            }
        }
    }

    public VuePuits getVuePuits() {
        return this.vuePuits;
    }
}

package fr.eseo.e3.poo.projet.blox.modele;

import java.util.Random;

public class Tas {
    private Element[][] elements;

    private Puits puits;

    public Tas(Puits puits) {
        this.puits = puits;
        this.elements = new Element[puits.getProfondeur()][puits.getLargeur()];
    }

    public Tas(Puits puits, int nbElements) {
        this(puits, nbElements, nbElements/puits.getLargeur()+1);
    }

    public Tas(Puits puits, int nbElements, int nbLigne) throws IllegalArgumentException {
        System.out.println("NbElements : " + nbElements + " NbLigne : " + nbLigne);
        if (nbElements != 0 && nbElements > puits.getProfondeur()*puits.getLargeur()) {
            throw new IllegalArgumentException("L'un des paramètres est incorrect");
        }
        this.puits = puits;
        this.elements = new Element[puits.getProfondeur()][puits.getLargeur()];
        this.construireTas(nbElements, nbLigne, new Random());
    }

    public Puits getPuits() {
        return this.puits;
    }

    public Element[][] getElements() {
        return this.elements;
    }

    private void construireTas(int nbElements, int nbLignes, Random rand) throws IllegalArgumentException {
        if (nbElements != 0 && nbElements > this.puits.getProfondeur()*this.puits.getLargeur()) {
            throw new IllegalArgumentException("L'un des paramètres est incorrect");
        }
        int nbElementsPlace = 0;
        while (nbElementsPlace != nbElements) {
            int ordon = this.puits.getProfondeur() - (rand.nextInt(nbLignes) + 1);
            int absci = rand.nextInt(this.puits.getLargeur());
            if (this.elements[ordon][absci] == null) {
                int indiceCouleur = rand.nextInt(Couleur.values().length);
                this.elements[ordon][absci] = new Element(absci, ordon, Couleur.values()[indiceCouleur]);
                nbElementsPlace++;
            }
        }
    }
}

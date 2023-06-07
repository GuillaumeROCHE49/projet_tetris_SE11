package fr.eseo.e3.poo.projet.blox.modele.pieces;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

public abstract class Piece {
    private List<Element> elements;
    private Puits puits;

    public Piece(Coordonnees coordonnees, Couleur couleur) {
        elements = new ArrayList<>();
        setElements(coordonnees, couleur);
    }

    protected abstract void setElements(Coordonnees coordonnees, Couleur couleur);

    public List<Element> getElements() {
        return this.elements;
    }

    public void setPosition(int abscisse, int ordonnee) {
        setElements(new Coordonnees(abscisse, ordonnee),
                    this.getElements().get(0).getCouleur());
    }

    public Puits getPuits() {
        return this.puits;
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    public void deplacerDe(int deltaX, int deltaY) throws IllegalArgumentException, BloxException {
        // Verifier si le deplacement est possible
        if (deltaX < -1 || deltaX > 1 || deltaY < 0 || deltaY > 1)
            throw new IllegalArgumentException("Deplacement impossible");
        
        if (this.sortieDePuits(this.elements, deltaX))
            throw new BloxException("Sortie de puits", BloxException.BLOX_SORTIE_PUITS);
        
        if (this.collision(this.elements, deltaX, deltaY))
            throw new BloxException("Collision", BloxException.BLOX_COLLISION);
        
        this.translater(this.elements, deltaX, deltaY);
    }

    private boolean collision(List<Element> elementAVerifier, int deltaX, int deltaY) {
        if(this.getPuits() != null){
            for (Element element : elementAVerifier) {
                int newAbscisse = element.getCoordonnees().getAbscisse() + deltaX;
                int newOrdonnee = element.getCoordonnees().getOrdonnee() + deltaY;
                // Si l'element sort du puits, continue
                if (newOrdonnee < 0)
                    continue;
                if ((newOrdonnee >= this.puits.getProfondeur()) || 
                        (this.puits.getTas().getElements()[newOrdonnee][newAbscisse] != null))
                    return true;
            }
        }
        return false;
    }

    private boolean sortieDePuits(List<Element> elementAVerifier, int deltaX) {
        if(this.getPuits() == null)
            return false;
        
        for (Element element : elementAVerifier) {
            int newAbscisse = element.getCoordonnees().getAbscisse() + deltaX;
            if (newAbscisse < 0 || newAbscisse >= this.puits.getLargeur())
                return true;
        }
        return false;
    }

    private void translater(List<Element> elementsATranslater, int deltaX, int deltaY) {
        for (Element element : elementsATranslater)
            element.deplacerDe(deltaX, deltaY);
    }
    
    public void tourner(boolean sensHoraire) throws BloxException {
        // Copie element
        List<Element> elementsCopy = this.copy();
        
        // Tourner la copie
        this.tournerElements(elementsCopy, sensHoraire);
        
        // Verifier si la copie est bonne
        if (this.sortieDePuits(elementsCopy, 0))
            throw new BloxException("Sortie de puits", BloxException.BLOX_SORTIE_PUITS);
        if (this.collision(elementsCopy, 0, 0))
            throw new BloxException("Collision", BloxException.BLOX_COLLISION);

        // Tourner l'original
        this.tournerElements(this.elements, sensHoraire);
    }

    private void tournerElements(List<Element> elementsATourner, boolean sensHoraire){
        int dx = -elementsATourner.get(0).getCoordonnees().getAbscisse();
        int dy = -elementsATourner.get(0).getCoordonnees().getOrdonnee();
        // Translater les Elements de la Piece d’un vecteur (dx, dy) afin que l’Element de référence de la
        // Piece (le premier element) se trouve à l’origine du repère.
        this.translater(elementsATourner, dx, dy);
        
        for (Element element : elementsATourner)
            if (element != elementsATourner.get(0))
                // definir les nouvelles coordonnees de l'element
                element.setCoordonnees(new Coordonnees(
                    sensHoraire ? -element.getCoordonnees().getOrdonnee() : element.getCoordonnees().getOrdonnee(),
                    sensHoraire ? element.getCoordonnees().getAbscisse() : -element.getCoordonnees().getAbscisse()
                ));
        
        // Translater les Elements de la Piece d’un vecteur (−dx, −dy) afin de revenir dans le repère initial.
        this.translater(elementsATourner, -dx, -dy);
    }

    public List<Element> copy(){
        List<Element> elementsCopy = new ArrayList<>();
        for (Element element : this.elements){
            Element elementCopy = new Element(new Coordonnees(element.getCoordonnees().getAbscisse(),
                                                              element.getCoordonnees().getOrdonnee()),
                                              element.getCouleur());
            elementsCopy.add(elementCopy);
        }
        return elementsCopy;
    }

    @Override
    public String toString() {
        String str = "Piece :\n";
        for (Element element : this.elements)
            str += "\t" + element.toString() + "\n";
        return str;
    }
}

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
        
        if (this.sortieDePuits(deltaX))
            throw new BloxException("Sortie de puits", BloxException.BLOX_SORTIE_PUITS);
        
        if (this.collision(deltaX, deltaY))
            throw new BloxException("Collision", BloxException.BLOX_COLLISION);
        
        this.translater(deltaX, deltaY);
    }

    private boolean collision(int deltaX, int deltaY) {
        if(this.getPuits() != null){
            for (Element element : this.elements) {
                int newAbscisse = element.getCoordonnees().getAbscisse() + deltaX;
                int newOrdonnee = element.getCoordonnees().getOrdonnee() + deltaY;
                // Si l'element sort du puits, continue
                if (newAbscisse < 0 || newAbscisse >= this.puits.getLargeur() ||
                newOrdonnee < 0)
                    continue;
                if ((newOrdonnee >= this.puits.getProfondeur()) ||
                        (this.puits.getTas().getElements()[newOrdonnee][newAbscisse] != null)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean sortieDePuits(int deltaX) {
        if(this.getPuits() == null)
            return false;
        
        for (Element element : this.elements) {
            int newAbscisse = element.getCoordonnees().getAbscisse() + deltaX;
            if (newAbscisse < 0 || newAbscisse >= this.puits.getLargeur())
                return true;
        }
        return false;
    }

    private boolean verifierTournerSortie(boolean sensHoraire, int dx) {
        // Return true si le tourner est possible
        if (this.getPuits() != null){
            for (Element element : this.elements){
                // Verifie seulement la sortie de puit
                int newAbscisse = element.getCoordonnees().getAbscisse() + dx;
                if (element != this.elements.get(0))
                    newAbscisse = sensHoraire ? -newAbscisse : newAbscisse;
                newAbscisse -= dx;
                System.out.println(newAbscisse);
                if (newAbscisse < 0 || newAbscisse >= this.puits.getLargeur())
                    return false;
            }
        }
        return true;
    }

    private boolean verifierTournerCollision(boolean sensHoraire) {
        // Return true si le tourner est possible
        if (this.getPuits() != null){
            for (Element element : this.elements){
                int newAbscisse = sensHoraire ? -element.getCoordonnees().getOrdonnee() : element.getCoordonnees().getOrdonnee();
                int newOrdonnee = sensHoraire ? element.getCoordonnees().getAbscisse() : -element.getCoordonnees().getAbscisse();
                if (newAbscisse < 0 || newAbscisse >= this.puits.getLargeur() ||
                newOrdonnee < 0)
                    continue;
                if ((newOrdonnee >= this.puits.getProfondeur()) ||
                        (this.puits.getTas().getElements()[newOrdonnee][newAbscisse] != null))
                    return true;
            }
        }
        return false;
    }

    private void translater(int deltaX, int deltaY) {
        for (Element element : this.elements)
            element.deplacerDe(deltaX, deltaY);
    }
    
    public void tourner(boolean sensHoraire) throws BloxException {
        int dx = -this.elements.get(0).getCoordonnees().getAbscisse();
        int dy = -this.elements.get(0).getCoordonnees().getOrdonnee();

        // Verifier les sorties de puits et les collisions
        if (!this.verifierTournerSortie(sensHoraire, dx))
            throw new BloxException("Sortie de puits", BloxException.BLOX_SORTIE_PUITS);
                
        // Translater les Elements de la Piece d’un vecteur (dx, dy) afin que l’Element de référence de la
        // Piece (le premier element) se trouve à l’origine du repère.
        this.translater(dx, dy);
        
        for (Element element : this.elements)
            if (element != this.elements.get(0))
                // definir les nouvelles coordonnees de l'element
                element.setCoordonnees(new Coordonnees(
                    sensHoraire ? -element.getCoordonnees().getOrdonnee() : element.getCoordonnees().getOrdonnee(),
                    sensHoraire ? element.getCoordonnees().getAbscisse() : -element.getCoordonnees().getAbscisse()
                ));
        
        // Translater les Elements de la Piece d’un vecteur (−dx, −dy) afin de revenir dans le repère initial.
        this.translater(-dx, -dy);
    }

    @Override
    public String toString() {
        String str = "Piece :\n";
        for (Element element : this.elements)
            str += "\t" + element.toString() + "\n";
        return str;
    }
}

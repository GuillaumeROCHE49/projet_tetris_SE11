package fr.eseo.e3.poo.projet.blox.modele.pieces;

import java.util.ArrayList;
import java.util.List;

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

    public void deplacerDe(int deltaX, int deltaY) throws IllegalArgumentException {
        // Verifier si le deplacement est possible
        if (deltaX < -1 || deltaX > 1 || deltaY < 0 || deltaY > 1) {
            throw new IllegalArgumentException("Deplacement impossible");
        } else {
            for (Element element: this.elements) {
                element.deplacerDe(deltaX, deltaY);
            }
        }
    }

    public void tourner(boolean sensHoraire){
        // Translater les Elements de la Piece d’un vecteur (dx, dy) afin que l’Element de référence de la
        // Piece (le premier element) se trouve à l’origine du repère.
        int dx = -this.elements.get(0).getCoordonnees().getAbscisse();
        int dy = -this.elements.get(0).getCoordonnees().getOrdonnee();
        for (Element element : this.elements)
            element.deplacerDe(dx, dy);
        
        // Effectuer la rotation des Elements de la Piece (sauf l’Element de référence) avec l’origine du repère
        // comme centre de la rotation
        // (utiliser les formules de rotation du plan).
        // Sens horraire definit par le signe du parametre sensHoraire
        for (Element element : this.elements)
            if (element != this.elements.get(0))
                // definir les nouvelles coordonnees de l'element
                element.setCoordonnees(new Coordonnees(
                    sensHoraire ? -element.getCoordonnees().getOrdonnee() : element.getCoordonnees().getOrdonnee(),
                    sensHoraire ? element.getCoordonnees().getAbscisse() : -element.getCoordonnees().getAbscisse()
                ));
        
        // Translater les Elements de la Piece d’un vecteur (−dx, −dy) afin de revenir dans le repère initial.
        for (Element element : this.elements)
            element.deplacerDe(-dx, -dy);
    }

    @Override
    public String toString() {
        String str = "Piece :\n";
        for (Element element : this.elements)
            str += "\t" + element.toString() + "\n";
        return str;
    }
}

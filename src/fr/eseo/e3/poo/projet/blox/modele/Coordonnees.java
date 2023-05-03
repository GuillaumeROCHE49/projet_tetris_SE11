package fr.eseo.e3.poo.projet.blox.modele;

import java.util.Objects;

public class Coordonnees {
    // The class Coordonnees is used to store the coordinates of a block in the grid.
    private int abscisse;
    private int ordonnee;

    public Coordonnees(int x, int y) {
        this.abscisse = x;
        this.ordonnee = y;
    }

    public int getAbscisse() {
        return this.abscisse;
    }

	public int getOrdonnee() {
        return this.ordonnee;
    }

    public void setAbscisse(int x) {
        this.abscisse = x;
    }

    public void setOrdonnee(int y) {
        this.ordonnee = y;
    }

    @Override
    public String toString() {
        return "(" + this.abscisse + ", " + this.ordonnee + ")";
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(abscisse, ordonnee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Coordonnees other = (Coordonnees) obj;
		return abscisse == other.abscisse && ordonnee == other.ordonnee;
	}
}

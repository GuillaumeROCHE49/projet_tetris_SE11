package fr.eseo.e3.poo.projet.blox.modele;

public class Element {
    private static final Couleur COULEUR_PAR_DEFAUT = Couleur.ROUGE;
    private Coordonnees coordonnees;
    private Couleur couleur;

    public Element(Coordonnees coordonnees) {
        this(coordonnees, COULEUR_PAR_DEFAUT);
    }

    public Element(int x, int y) {
        this(new Coordonnees(x, y));
    }

    public Element(int x, int y, Couleur couleur) {
        this(new Coordonnees(x, y), couleur);
    }

    public Element(Coordonnees coordonnees, Couleur couleur) {
        this.coordonnees = coordonnees;
        this.couleur = couleur;
    }

    public Coordonnees getCoordonnees() {
        return this.coordonnees;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void deplacerDe(int deltaX, int deltaY) {
        this.coordonnees.setAbscisse(this.coordonnees.getAbscisse() + deltaX);
        this.coordonnees.setOrdonnee(this.coordonnees.getOrdonnee() + deltaY);
    }

    @Override
    public String toString() {
        return this.coordonnees.toString() + " - " + this.couleur.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.coordonnees == null) ? 0 : this.coordonnees.hashCode());
        result = prime * result + ((this.couleur == null) ? 0 : this.couleur.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        Element other = (Element) obj;
        if (!this.coordonnees.equals(other.coordonnees) || this.couleur != other.couleur)
            return false;
        return true;
    }
}

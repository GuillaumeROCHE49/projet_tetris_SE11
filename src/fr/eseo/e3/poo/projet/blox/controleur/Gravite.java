package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class Gravite implements ActionListener{
    private final VuePuits vuePuits;
    private final Puits puits;

    private Timer timer;

    public Gravite(VuePuits vuePuits){
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
        this.timer = new Timer(1000, this);
    }

    public int getPeriodicite(){
        return this.timer.getDelay();
    }

    public void setPeriodicite(int periodicite){
        this.timer.setDelay(periodicite);
    }

    public void actionPerformed(java.awt.event.ActionEvent event){
        this.puits.gravite();
    }
}

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
        this.timer.setInitialDelay(0);
        this.start();
    }

    public int getPeriodicite(){
        return this.timer.getDelay();
    }

    public void setPeriodicite(int periodicite){
        this.timer.setDelay(periodicite);
    }

    public void start(){
        this.timer.start();
    }

    public void actionPerformed(java.awt.event.ActionEvent event){
        System.out.println("Gravite");
        this.puits.gravite();
        this.vuePuits.repaint();
    }
}

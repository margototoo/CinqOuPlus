package com.example.cinqouplus.jeu;

import com.example.cinqouplus.geometrie.Geometrie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grille implements Cloneable{
    private List<Geometrie> grille;
    private List<Geometrie> next;

    public Grille(List<Geometrie> liste) {
        this.grille = liste;
        this.next = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            next.add(null);
        }
    }

    // Recuperer la forme qui est à l'emplacement demandé
    private Geometrie getGeo(int  lignes) {
        return this.grille.get(lignes);
    }

    // Changer la forme qui est à l'emplacement demandé
    private void setGeo (int ligne, Geometrie geo) {
        this.grille.set(ligne, geo);
    }

    public List<Geometrie> getNext () {
        return this.next;
    }

    // retourner vrai si il y a une forme géométrique dans cette emplacement
    public boolean isGeo (int lignes) {
        return grille.get(lignes) != null;
    }

    public void faireDeplacment (ArrayList<float[]> cases, int deplacementSource, int deplacementDestination) {
        Geometrie source = this.getGeo(deplacementSource);
        this.setGeo(deplacementSource, null);
        this.setGeo(deplacementDestination, source);

        float[] pos = cases.get(deplacementDestination);
        source.set_position(pos);
    }

    public int[] prochaineBille() {
        final Random random = new Random();
        int[] next = new int[3];
        for (int i = 0; i < next.length; i++) {
            next[i] = random.nextInt(7 - 1 + 1) + 1;
        }
        return next;
    }

    public int[] prochainEmplacement() {
        final Random random = new Random();
        int[] emp = new int[3];
        for (int i = 0; i < emp.length; i++) {
            int j = random.nextInt(81 - 1 + 1) + 1;
            while (grille.get(j-1) != null) {
                j = random.nextInt(81 - 1 + 1) + 1;
            }
            emp[i] = j-1;
        }
        return emp;
    }

    public void dessinerGeo (float[] scratch){
        for (Geometrie geo: this.grille) {
            if (geo != null ) {
                geo.draw(scratch);
            }
        }
    }

    public void dessinerGeoNext (float[] scratch) {
        for (Geometrie geome: this.next) {
            if (geome != null ) {
                geome.draw(scratch);
            }
        }
    }

    public List<Geometrie> getGrille () {
        return this.grille;
    }
}

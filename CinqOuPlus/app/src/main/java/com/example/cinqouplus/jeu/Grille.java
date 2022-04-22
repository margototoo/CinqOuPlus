package com.example.cinqouplus.jeu;

import android.util.Log;

import com.example.cinqouplus.geometrie.Geometrie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Grille implements Cloneable{
    private List<Geometrie> grille;
    private List<Geometrie> next;
    private List<Geometrie> lignes;
    private final int nbLignes;

    public Grille(List<Geometrie> liste) {
        ArrayList<float[]> lines = new ArrayList<>(Arrays.asList(
                new float[]{-9.0f, -9.f, 0.0f,
                        -9.f, 9.f, 0.0f},
                new float[]{-7.0f, -9.f, 0.0f,
                        -7.f, 9.f, 0.0f},
                new float[]{-5.0f, -9.f, 0.0f,
                        -5.f, 9.f, 0.0f},
                new float[]{-3.0f, -9.f, 0.0f,
                        -3.f, 9.f, 0.0f},
                new float[]{-1.0f, -9.f, 0.0f,
                        -1.f, 9.f, 0.0f},
                new float[]{1.0f, -9.f, 0.0f,
                        1.f, 9.f, 0.0f},
                new float[]{3.0f, -9.f, 0.0f,
                        3.f, 9.f, 0.0f},
                new float[]{5.0f, -9.f, 0.0f,
                        5.f, 9.f, 0.0f},
                new float[]{7.0f, -9.f, 0.0f,
                        7.f, 9.f, 0.0f},
                new float[]{9.0f, -9.f, 0.0f,
                        9.f, 9.f, 0.0f},

                new float[]{9.0f, 9.f, 0.0f,
                        -9.f, 9.f, 0.0f},
                new float[]{9.0f, 7.f, 0.0f,
                        -9.f, 7.f, 0.0f},
                new float[]{9.0f, 5.f, 0.0f,
                        -9.f, 5.f, 0.0f},
                new float[]{9.0f, 3.f, 0.0f,
                        -9.f, 3.f, 0.0f},
                new float[]{9.0f, 1.f, 0.0f,
                        -9.f, 1.f, 0.0f},
                new float[]{9.0f, -1.f, 0.0f,
                        -9.f, -1.f, 0.0f},
                new float[]{9.0f, -3.f, 0.0f,
                        -9.f, -3.f, 0.0f},
                new float[]{9.0f, -5.f, 0.0f,
                        -9.f, -5.f, 0.0f},
                new float[]{9.0f, -7.f, 0.0f,
                        -9.f, -7.f, 0.0f},
                new float[]{9.0f, -9.f, 0.0f,
                        -9.f, -9.f, 0.0f}));

        this.nbLignes = 9;
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
    private void setGeo (int ligne, int colonne, Geometrie geo) {
        //this.grille.set(ligne + nbLignes + colonne, geo);
        this.grille.set(ligne, geo);
    }

    public List<Geometrie> getNext () {
        return this.next;
    }

    // savoir si le déplacement est possible
    public boolean deplacement (int lignes) {
        return true;
    }

    // retourner vrai si il y a une forme géométrique dans cette emplacement
    public boolean isGeo (int lignes) {
        if (grille.get(lignes) == null)
            return false;
        return true;
    }

    public void faireDeplacment (ArrayList<float[]> cases, int deplacementSource, int deplacementDestination) {
        Geometrie source = this.getGeo(deplacementSource);
        this.setGeo(deplacementSource, 0, null);
        this.setGeo(deplacementDestination, 0, source);

        float[] pos = cases.get(deplacementDestination);
        source.set_position(pos);
        Log.d("forme", String.valueOf(this.grille.get(deplacementSource)));
    }

    public void prochaineVague() {

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
        for (Geometrie geome: this.next) {
            if (geome != null )
                geome.draw(scratch);
        }
    }

    public void dessinerGeoNext (float[] scratch) {
        for (Geometrie geome: this.next) {
            if (geome != null )
                geome.draw(scratch);
        }
    }

    public List<Geometrie> getGrille () {
        return this.grille;
    }
}

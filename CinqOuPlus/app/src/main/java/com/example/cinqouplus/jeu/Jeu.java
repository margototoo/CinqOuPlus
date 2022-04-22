package com.example.cinqouplus.jeu;

import android.util.Log;

import com.example.cinqouplus.geometrie.Carres;
import com.example.cinqouplus.geometrie.Geometrie;
import com.example.cinqouplus.geometrie.Hexagone;
import com.example.cinqouplus.geometrie.Lines;
import com.example.cinqouplus.geometrie.Losange;
import com.example.cinqouplus.geometrie.Octogone;
import com.example.cinqouplus.geometrie.Pentagone;
import com.example.cinqouplus.geometrie.Plateau;
import com.example.cinqouplus.geometrie.Triangle;
import com.example.cinqouplus.geometrie.TriangleInverse;

import java.util.ArrayList;
import java.util.Arrays;

public class Jeu {

    private Plateau mPlateau;
    private Grille mGrille;
    private float[] mPlateauPosition = {0.0f, 0.0f};
    private Score score;

    private float[] case1 = {-8.0f,8.0f};
    private float[] case2 = {-6.0f,8.0f};
    private float[] case3 = {-4.0f,8.0f};
    private float[] case4 = {-2.0f,8.0f};
    private float[] case5 = {0.0f,8.0f};
    private float[] case6 = {2.0f,8.0f};
    private float[] case7 = {4.0f,8.0f};
    private float[] case8 = {6.0f,8.0f};
    private float[] case9 = {8.0f,8.0f};

    private float[] case10 = {-8.0f,6.0f};
    private float[] case11 = {-6.0f,6.0f};
    private float[] case12 = {-4.0f,6.0f};
    private float[] case13 = {-2.0f,6.0f};
    private float[] case14 = {0.0f,6.0f};
    private float[] case15 = {2.0f,6.0f};
    private float[] case16 = {4.0f,6.0f};
    private float[] case17 = {6.0f,6.0f};
    private float[] case18 = {8.0f,6.0f};

    private float[] case19 = {-8.0f,4.0f};
    private float[] case20 = {-6.0f,4.0f};
    private float[] case21 = {-4.0f,4.0f};
    private float[] case22 = {-2.0f,4.0f};
    private float[] case23 = {0.0f,4.0f};
    private float[] case24 = {2.0f,4.0f};
    private float[] case25 = {4.0f,4.0f};
    private float[] case26 = {6.0f,4.0f};
    private float[] case27 = {8.0f,4.0f};

    private float[] case28 = {-8.0f,2.0f};
    private float[] case29 = {-6.0f,2.0f};
    private float[] case30 = {-4.0f,2.0f};
    private float[] case31 = {-2.0f,2.0f};
    private float[] case32 = {0.0f,2.0f};
    private float[] case33 = {2.0f,2.0f};
    private float[] case34 = {4.0f,2.0f};
    private float[] case35 = {6.0f,2.0f};
    private float[] case36 = {8.0f,2.0f};

    private float[] case37 = {-8.0f,0.0f};
    private float[] case38 = {-6.0f,0.0f};
    private float[] case39 = {-4.0f,0.0f};
    private float[] case40 = {-2.0f,0.0f};
    private float[] case41 = {0.0f,0.0f};
    private float[] case42 = {2.0f,0.0f};
    private float[] case43 = {4.0f,0.0f};
    private float[] case44 = {6.0f,0.0f};
    private float[] case45 = {8.0f,0.0f};

    private float[] case46 = {-8.0f,-2.0f};
    private float[] case47 = {-6.0f,-2.0f};
    private float[] case48 = {-4.0f,-2.0f};
    private float[] case49 = {-2.0f,-2.0f};
    private float[] case50 = {0.0f,-2.0f};
    private float[] case51 = {2.0f,-2.0f};
    private float[] case52 = {4.0f,-2.0f};
    private float[] case53 = {6.0f,-2.0f};
    private float[] case54 = {8.0f,-2.0f};

    private float[] case55 = {-8.0f,-4.0f};
    private float[] case56 = {-6.0f,-4.0f};
    private float[] case57 = {-4.0f,-4.0f};
    private float[] case58 = {-2.0f,-4.0f};
    private float[] case59 = {0.0f,-4.0f};
    private float[] case60 = {2.0f,-4.0f};
    private float[] case61 = {4.0f,-4.0f};
    private float[] case62 = {6.0f,-4.0f};
    private float[] case63 = {8.0f,-4.0f};

    private float[] case64 = {-8.0f,-6.0f};
    private float[] case65 = {-6.0f,-6.0f};
    private float[] case66 = {-4.0f,-6.0f};
    private float[] case67 = {-2.0f,-6.0f};
    private float[] case68 = {0.0f,-6.0f};
    private float[] case69 = {2.0f,-6.0f};
    private float[] case70 = {4.0f,-6.0f};
    private float[] case71 = {6.0f,-6.0f};
    private float[] case72 = {8.0f,-6.0f};

    private float[] case73 = {-8.0f,-8.0f};
    private float[] case74 = {-6.0f,-8.0f};
    private float[] case75 = {-4.0f,-8.0f};
    private float[] case76 = {-2.0f,-8.0f};
    private float[] case77 = {0.0f,-8.0f};
    private float[] case78 = {2.0f,-8.0f};
    private float[] case79 = {4.0f,-8.0f};
    private float[] case80 = {6.0f,-8.0f};
    private float[] case81 = {8.0f,-8.0f};

    private float[] caseNext1 = {-9.3f, 4};
    private float[] caseNext2 = {-9.3f, 0};
    private float[] caseNext3 = {-9.3f, -4};

    private static ArrayList<float[]> cases;
    private static ArrayList<float[]> casesNext;
    private ArrayList<float[]> lines;
    ArrayList<Geometrie> liste_Lignes;

    private boolean estDemarrer = false;

    private boolean condition = false;

    private int[] prochaineBille;

    private int derniereCaseTouche;

    private int[][] grid;
    private int[][] map;

    public Jeu (){
        this.mPlateau = new Plateau(mPlateauPosition,0.93f,0.93f,0.82f);
        this.prochaineBille = new int[3];
        this.score = new Score();
        /*for (int i = 0; i < 3; i++) {
            next.add(null);
        }*/

        this.lines = new ArrayList<>(Arrays.asList(
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

        this.liste_Lignes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Lines l = new Lines();
            l.setCoords(this.lines.get(i));
            liste_Lignes.add(l);
        }

        this.cases = new ArrayList<>(Arrays.asList(case1, case2, case3, case4, case5, case6, case7, case8, case9, case10, case11, case12, case13, case14, case15, case16, case17, case18, case19, case20, case21, case22, case23, case24, case25, case26, case27, case28, case29, case30, case31, case32, case33, case34, case35, case36, case37, case38, case39, case40, case41, case42, case43, case44, case45, case46, case47, case48, case49, case50, case51, case52, case53, case54, case55, case56, case57, case58, case59, case60, case61, case62, case63, case64, case65, case66, case67, case68, case69, case70, case71, case72, case73, case74, case75, case76, case77, case78, case79, case80, case81));
        this.casesNext = new ArrayList<>(Arrays.asList(caseNext1, caseNext2, caseNext3));

        //this.lines1 = new Lines(ligne1);

        ArrayList<Geometrie> liste_forme = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            //TriangleInverse losange = new TriangleInverse(case1, 1f, 0f, 0f);
            //liste_forme.add(losange);
            liste_forme.add(null);
        }
        //ArrayList<Geometrie> liste_forme = new ArrayList<>(Arrays.asList(losange1,losange2,losange3,null,null,null,triangle1,triangle2,null));

        this.mGrille = new Grille(liste_forme);

        for (int i = 0; i<81; i++) {
            if (mGrille.getGrille().get(i) != null) {
                mGrille.getGrille().get(i).set_position(this.cases.get(i));
            }
        }

        this.map = new int[9][9];
        this.grid = new int[9][9];
    }

    public Plateau getmPlateau() {
        return mPlateau;
    }

    public void dessinerJeu(float[] scratch) {
        mPlateau.draw(scratch);
        Log.d("Jeu", "DessinerJeu");
        for(Geometrie l : this.liste_Lignes) {
            l.draw(scratch);
        }
        mGrille.dessinerGeo(scratch);
    }

    public void dessinerNext(float[] scratch) {
        mGrille.dessinerGeoNext(scratch);
    }

    // Pour gérer la couleur du clignottement
    public void setPlateauColors(float red, float green, float blue) {
        mPlateau.setPlateauColors(red, green, blue);
    }

    public boolean estDemarrer () {
        return estDemarrer;
    }

    public void setEstDemarrer (boolean demarrer) {
        this.estDemarrer = demarrer;
    }

    public boolean getCondition () {
        return this.condition;
    }

    public void setCondition (boolean c) {
        this.condition = c;
    }

    public float[] getmPlateauPosition() {
        return mPlateauPosition;
    }

    public Grille getmGrille() {
        return this.mGrille;
    }

    public int getDerniereCaseTouche() {
        return this.derniereCaseTouche;
    }

    public void setDerniereCaseTouche(int i) {
        this.derniereCaseTouche = i;
    }

    public Score getScore() {
        return score;
    }

    public void commencer() {
        int[] nextBille = mGrille.prochaineBille();
        int[] nextEmp = mGrille.prochainEmplacement();
        mettreGeo(nextBille, nextEmp);
        nextBille = mGrille.prochaineBille();
        setProchaineBille(nextBille);
        mettreGeoNext(this.prochaineBille);
        for (int g: prochaineBille) {
            Log.d("prochaine bille", String.valueOf(g));
        }
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public boolean siDeplacement(int indiceSource, int indiceDestination){
        int[][] m = new int[9][9];
        // on faire une nouvelle representation de la grille pour faciliter le fait de trouver un déplacement
        for(int i = 0; i < 9; i ++) {
            for(int j = 0; j < 9; j++) {
                if(mGrille.isGeo(i * 9 + j)) { // 1 si il y un objet
                    m[i][j] = 1;
                    Log.d("je passe dans "+(i * 9 + j), "heuuu");
                } else { // 0 si il n'y a pas d'objet
                    m[i][j] = 0;
                }
                /*if(i * 9 + j == indiceSource) // 3 pour le depart
                    m[i][j] = 3;
                if(i * 9 + j == indiceDestination) // 4 pour l'arriver
                    m[i][j] = 4;
                //m[i][j] = mGrille.getGrille().get(i * 9 + j);*/
            }
        }
        setGrid(m);
        for(int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid.length; j++) {
                Log.d("matrice map" + i + " " +j, String.valueOf(grid[i][j]));
            }
        }

        int ligneP = (indiceSource/9) + 1;
        int colonneP = (indiceSource % 9)+1;
        boolean b = traverse((indiceSource/9) + 1, (indiceSource % 9)+1, (indiceDestination/9) + 1, (indiceDestination % 9)+1);

        for(int k = 0; k < map.length; k ++) {
            for (int j = 0; j < map.length; j++) {
                Log.d("matrice " + k + " " +j, String.valueOf(map[k][j]));
            }
        }
        return b;
    }

    private void dijkstra () {

    }

    private void setMap (int i, int j, int x) {
        this.map[i][j] = x;
    }

    private boolean traverse(int id, int jd, int ia, int ja) {
        if (!isValid(id,jd)) {
            return false;
        }if ( isEnd(id,jd,ia,ja) ) {
            setMap(id, jd, 3);
            //map[id][jd] = 3;
            return true;
        } else {
            setMap(id, jd, 2);
            //map[id][jd] = 2;
        }
        //North
        if (traverse(id - 1, jd, ia, ja)) {
            setMap(id-1, jd, 3);
            //map[id-1][jd] = 3;
            //return true;
        }
        //East
        if (traverse(id, jd + 1, ia, ja)) {
            setMap(id, jd+1, 3);
            //map[id][jd + 1] = 3;
            //return true;
        }
        //South
        if (traverse(id + 1, jd, ia, ja)) {
            setMap(id+1, jd, 3);
            //map[id + 1][jd] = 3;
            //return true;
        }
        //West
        if (traverse(id, jd - 1, ia, ja)) {
            setMap(id, jd-1, 3);
            //map[id][jd - 1] = 3;
            //return true;
        }
        /*if (!isValid(id,jd)) {
            return false;
        }
        if ( isEnd(id,jd,ia,ja) ) {
            setMap(id, jd, 3);
            //map[id][jd] = 3;
            for(int k = 0; k < map.length; k ++) {
                for (int j = 0; j < map.length; j++) {
                    Log.d("matrice " + k + " " +j, String.valueOf(map[k][j]));
                }
            }
            return true;
        } else {
            setMap(id, jd, 2);
            map[id][jd] = 2;
        }
        //North
        if (traverse(id - 1, jd, ia, ja)) {
            map[id-1][jd] = 3;
            //return true;
        }
        //East
        if (traverse(id, jd + 1, ia, ja)) {
            map[id][jd + 1] = 3;
            //return true;
        }
        //South
        if (traverse(id + 1, jd, ia, ja)) {
            map[id + 1][jd] = 3;
            //return true;
        }
        //West
        if (traverse(id, jd - 1, ia, ja)) {
            map[id][jd - 1] = 3;
            //return true;
        }*/
        return false;
    }

    private boolean isEnd(int id, int jd, int ia, int ja) {
        //return i == 9 - 1 && j == 9 - 1;
        return id == ia && jd == id;
    }
    private boolean isValid(int i, int j) {
        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
            return true;
        }
        return false;
    }
    private boolean isOpen(int i, int j) {
        return grid[i][j] == 1;
    }
    private boolean isTried(int i, int j) {
        return map[i][j] == 2;
    }
    private boolean inRange(int i, int j) {
        return inHeight(i) && inWidth(j);
    }
    private boolean inHeight(int i) {
        return i >= 0 && i < 9;
    }
    private boolean inWidth(int j) {
        return j >= 0 && j < 9;
    }

    // voir si le déplacement est possible
    /*public boolean deplacement (int indiceSource, int indiceDestination) {
        ArrayList<Integer> path = new ArrayList<>();
        path.add(indiceSource);
        int ligneP = (indiceSource/9) + 1;
        int colonneP = (indiceSource % 9)+1;

        int min = 253;
        do {
            float[] pos = {ligneP, colonneP};
            if(pos[1] + 1 <= 9) {
                if(mGrille.getGrille().get((int)((pos[0]-1)*9+(pos[1]+1)-1)) < min ) {

                }
            }
            if(pos[1] - 1 > 0) {
                if(mGrille.getGrille().get((int)((pos[0]-1)*9+(pos[1]-1)-1)) < min) {

                }
            }
            if(pos[0] + 1 <= 9) {
                if(mGrille.getGrille().get((int)(((pos[0]+1)-1)*9+pos[1]-1)) == null ) {

                }
            }
            if(pos[0] - 1 > 0) {
                if(mGrille.getGrille().get((int)(((pos[0]-1)-1)*9+pos[1]-1)) == null ) {

                }
            }
        }
        return false;
    }*/

    public void pendantDeplacement() {
        int[] nextEmp = mGrille.prochainEmplacement();
        for (int g: prochaineBille) {
            Log.d("prochaine bille", String.valueOf(g));
        }
        mettreGeo(this.prochaineBille, nextEmp);
        int[] p = mGrille.prochaineBille();
        setProchaineBille(p);
        mettreGeoNext(this.prochaineBille);
        for (int g: prochaineBille) {
            Log.d("prochaine bille", String.valueOf(g));
        }
    }

    public int[] getProchaineBille() {
        return prochaineBille;
    }

    public void setProchaineBille(int[] prochaineBille) {
        this.prochaineBille = prochaineBille;
    }

    public void mettreGeo(int[] nextBille, int[] nextEmp) {
        for(int i = 0; i < 3; i++){
            if (nextBille[i] == 1)
                mGrille.getGrille().set(nextEmp[i], new Triangle(cases.get(nextEmp[i]), 0.9f, 0f, 0.1f));
            if (nextBille[i] == 2)
                mGrille.getGrille().set(nextEmp[i], new Pentagone(cases.get(nextEmp[i]), 1f, 0.9f, 0f));
            if (nextBille[i] == 3)
                mGrille.getGrille().set(nextEmp[i], new Carres(cases.get(nextEmp[i]), 1f, 0f, 0.9f));
            if (nextBille[i] == 4)
                mGrille.getGrille().set(nextEmp[i], new Losange(cases.get(nextEmp[i]), 0.5f, 0.5f, 0.5f));
            if (nextBille[i] == 5)
                mGrille.getGrille().set(nextEmp[i], new TriangleInverse(cases.get(nextEmp[i]), 0f, 1f, 1f));
            if (nextBille[i] == 6)
                mGrille.getGrille().set(nextEmp[i], new Hexagone(cases.get(nextEmp[i]), 0.5f, 0f, 1f));
            if (nextBille[i] == 7)
                mGrille.getGrille().set(nextEmp[i], new Octogone(cases.get(nextEmp[i]), 0.1f, 1f, 0f));
// METTRE UNE AUTRE FORME
        }
    }

    public void mettreGeoNext(int[] nextBille) {
        for(int i = 0; i < 3; i++){
            if (nextBille[i] == 1)
                mGrille.getNext().set(i, new Triangle(casesNext.get(i), 0.9f, 0f, 0.1f));
            if (nextBille[i] == 2)
                mGrille.getNext().set(i, new Pentagone(casesNext.get(i), 1f, 0.9f, 0f));
            if (nextBille[i] == 3)
                mGrille.getNext().set(i, new Carres(casesNext.get(i), 1f, 0f, 0.9f));
            if (nextBille[i] == 4)
                mGrille.getNext().set(i, new Losange(casesNext.get(i), 0.5f, 0.5f, 0.5f));
            if (nextBille[i] == 5)
                mGrille.getNext().set(i, new TriangleInverse(casesNext.get(i), 0f, 1f, 1f));
            if (nextBille[i] == 6)
                mGrille.getNext().set(i, new Hexagone(casesNext.get(i), 0.5f, 0f, 1f));
            if (nextBille[i] == 7)
                mGrille.getNext().set(i, new Octogone(casesNext.get(i), 0.1f, 1f, 0f));
// METTRE UNE AUTRE FORME
        }
    }

    public ArrayList<float[]> getCases() {
        return cases;
    }

    public boolean getEstTermine () {
        for(Geometrie g : this.mGrille.getGrille()) {
            if (g == null) {
                return false;
            }
        }
        return true;
    }

    // voir si on peut enlever une ligne
    public void  enleverLigne(int indice) {
        ArrayList<float[]> positionAEnlever = trouverLigne(indice);
        Log.d("enleverLigne", "je passe pars la");
        if(!positionAEnlever.isEmpty()) {
            Log.d("ya des trucs a enlever","c'est pas vide");
            for (int i = 0; i < positionAEnlever.size(); i++) {
                float[] pos = positionAEnlever.get(i);
                Log.d("ya des trucs a enlever","position " + (((pos[0]-1)*9+pos[1])-1));
                this.mGrille.getGrille().set((int)(((pos[0]-1)*9+pos[1])-1), null);
            }
            this.score.setScore(10);
        }
    }

    //voir si le joueur à fait une ligne
    public ArrayList<float[]> trouverLigne (int indice) {
        ArrayList<float[]> positionAEnlever = new ArrayList<>();

        boolean horizontal = true;
        boolean horizontal2 = true;

        boolean vertical = true;
        boolean vertical2 = true;

        boolean diagonal = true;
        boolean diagonal2 = true;

        boolean opposite = true;
        boolean opposite2 = true;

        final ArrayList<float[]> horizontals = new ArrayList<>();
        final ArrayList<float[]> verticals = new ArrayList<>();
        final ArrayList<float[]> diagonals = new ArrayList<>();
        final ArrayList<float[]> opposites = new ArrayList<>();

        int i = 1;
        Geometrie forme = this.mGrille.getGrille().get(indice);
        int ligneP = (indice/9) + 1;
        int colonneP = (indice % 9)+1;

        float[] pos = {ligneP,colonneP};
        positionAEnlever.add(pos);

        Log.d("ligne colonne", "ligneP : " + ligneP+ " colonneP : "+colonneP);
        Log.d("geo depart", this.mGrille.getGrille().get(((ligneP - 1) * 9 + colonneP) - 1) + " emplacement : " + ((ligneP-1)*9 + colonneP) + "   "+ forme);

        while(horizontal || vertical || diagonal || opposite ||
                horizontal2 || vertical2 || diagonal2 || opposite2) {

            int x,y;
            if (vertical) {
                x = ligneP + i;
                y = colonneP;
                if (x <= 9) {
                    Log.d("pour voir dans la case", String.valueOf((x-1)*9 + y));
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    //if (this.mGrille.getGrille().get(((x-1)*9 + y)-1) == forme) {
                    //if (this.mGrille.getGrille().get(((x-1)*9 + y)-1).equals(forme)) {
                    if (forme.equals(formeTest)) {
                        Log.d("horizontal", "rentre dedans");
                        float[] xy = {x,y};
                        verticals.add(xy);
                    } else {
                        vertical = false;
                    }
                }else {
                    vertical = false;
                }
            }

            if (vertical2) {
                x = ligneP - i;
                y = colonneP;
                if (x > 0) {
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    if (forme.equals(formeTest)) {
                        float[] xy = {x,y};
                        verticals.add(xy);
                    } else {
                        vertical2 = false;
                    }
                } else {
                    vertical2 = false;
                }
            }

            if (horizontal) {
                x = ligneP;
                y = colonneP + i;
                if (y <= 9) {
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    if (forme.equals(formeTest)) {
                        horizontals.add(new float[]{x, y});
                    } else {
                        horizontal = false;
                    }
                } else {
                    horizontal = false;
                }
            }

            if (horizontal2) {
                x = ligneP;
                y = colonneP - i;
                if (y > 0) {
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    if (forme.equals(formeTest)) {
                        horizontals.add(new float[]{x, y});
                    } else {
                        horizontal2 = false;
                    }
                } else {
                    horizontal2 = false;
                }
            }

            if (diagonal) {
                x = ligneP + i;
                y = colonneP + i;
                if (y <= 9 && x <= 9) {
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    if (forme.equals(formeTest)) {
                        diagonals.add(new float[]{x, y});
                    } else {
                        diagonal = false;
                    }
                } else {
                    diagonal = false;
                }
            }

            if (diagonal2) {
                x = ligneP - i;
                y = colonneP - i;
                if (y > 0 && x > 0) {
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    if (forme.equals(formeTest)) {
                        diagonals.add(new float[]{x, y});
                    } else {
                        diagonal2 = false;
                    }
                } else {
                    diagonal2 = false;
                }
            }

            if (opposite) {
                x = ligneP - i;
                y = colonneP + i;
                if (y <= 9 && x > 0) {
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    if (forme.equals(formeTest)) {
                        opposites.add(new float[]{x, y});
                    } else {
                        opposite = false;
                    }
                } else {
                    opposite = false;
                }
            }

            if (opposite2) {
                x = ligneP + i;
                y = colonneP - i;
                if (y > 0 && x <= 9) {
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    if (forme.equals(formeTest)) {
                        opposites.add(new float[]{x, y});
                    } else {
                        opposite2 = false;
                    }
                } else {
                    opposite2 = false;
                }
            }

            i++;
            if ( i >= 10) {
                break;
            }
        }

        Log.d("taille horizontals", String.valueOf(horizontals.size()));
        Log.d("taille verticals", String.valueOf(verticals.size()));
        Log.d("taille diagonals", String.valueOf(diagonals.size()));
        Log.d("taille opposites", String.valueOf(opposites.size()));

        add(positionAEnlever, horizontals, 5 - 1);
        add(positionAEnlever, verticals, 5 - 1);
        add(positionAEnlever, diagonals, 5 - 1);
        add(positionAEnlever, opposites, 5 - 1);

        Log.d("taille positionAEnlever", String.valueOf(positionAEnlever.size()));
        for (int j = 0; j < positionAEnlever.size(); j++){
            float[] m = positionAEnlever.get(j);
            Log.d("position a enlever", m[0] +", "+m[1]);
        }

        if (positionAEnlever.size() < 5) {
            positionAEnlever.clear();
            Log.d("chercher la position", "je passe aussi par la");
        }

        return positionAEnlever;
    }

    private static void add(ArrayList<float[]> source, ArrayList<float[]> collection, int count) {
        if (collection.size() >= count) {
            source.addAll(collection);
        }
    }

}

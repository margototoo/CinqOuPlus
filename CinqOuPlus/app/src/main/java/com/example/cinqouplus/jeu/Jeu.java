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

    private float[] caseNext1 = {-15.0f, 0};
    private float[] caseNext2 = {0.0f, 0};
    private float[] caseNext3 = {15.0f, 0};

    private static ArrayList<float[]> cases;
    private static ArrayList<float[]> casesNext;
    private ArrayList<float[]> lines;
    ArrayList<Geometrie> liste_Lignes;

    private boolean estDemarrer = false;

    private boolean condition = false;

    private int[] prochaineBille;

    private int derniereCaseTouche;

    private int[] billeSelection;
    private int[] billeArrive;

    // lorsque qu'on recherche si le chemin est possible
    // on utilise des tableau de tableau pour que la recherche soit plus simple
    // donc grid est un tableau de tableau qui prend les informations de la grille
    // et map sert à rentre les chemins possible
    private int[][] grid;
    private int[][] map;

    public Jeu (){
        this.mPlateau = new Plateau(mPlateauPosition,0.93f,0.93f,0.82f);
        this.prochaineBille = new int[3];
        this.score = new Score();

        this.billeSelection = new int[2];

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

        ArrayList<Geometrie> liste_forme = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            liste_forme.add(null);
        }

        this.mGrille = new Grille(liste_forme);

        for (int i = 0; i<81; i++) {
            if (mGrille.getGrille().get(i) != null) {
                mGrille.getGrille().get(i).set_position(this.cases.get(i));
            }
        }

        this.map = new int[9][9];
        this.grid = new int[9][9];
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
                } else { // 0 si il n'y a pas d'objet
                    m[i][j] = 0;
                }
            }
        }
        setGrid(m);
        int[] depart = {(indiceSource/9), (indiceSource % 9)};
        int[] arrive = {(indiceDestination/9), (indiceDestination % 9)};
        this.billeSelection = depart;
        this.billeArrive = arrive;
        return deplacementPossible();
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean deplacementPossible(){
        int[] coordonneDepart = billeSelection;//coordonnees.get(billeSelectionner.get_position());
        int[] coordonneArrive = billeArrive;//coordonnees.get(arrive);

        // on instancie les differentes listes nécessaire
        ArrayList<int[]> liste_sommets_a_explorer = new ArrayList<>();
        ArrayList<int[]> prochain_sommets_a_explorer = new ArrayList<>();
        ArrayList<int[]> liste_sommets_deja_explorer = new ArrayList<>();

        liste_sommets_a_explorer.add(coordonneDepart);
        while(true){
            // on regarde si il y a des cases à traiter
            if(liste_sommets_a_explorer.size()>0) {
                for (int[] positionSommet : liste_sommets_a_explorer) {
                    // si la case est la case d'arrivée
                    if (coordonneArrive[0] == positionSommet[0] && coordonneArrive[1] == positionSommet[1]) {
                        return true;
                    }

                    // on get les voisins de la case qu'on explore
                    int[] sommetHaut = {positionSommet[0] - 1, positionSommet[1]};
                    int[] sommetBas = {positionSommet[0] + 1, positionSommet[1]};
                    int[] sommetGauche = {positionSommet[0], positionSommet[1] - 1};
                    int[] sommetDroite = {positionSommet[0], positionSommet[1] + 1};

                    //on regarde le sommet Haut si il est valide si il est pas déja explorer et si oui on l'ajoute pour l'explorer sur la prochaine itération
                    if (isValideDeplacementPossible(sommetHaut)) {
                        if (!contient(liste_sommets_deja_explorer,sommetHaut) && !contient(prochain_sommets_a_explorer,sommetHaut)) {
                            prochain_sommets_a_explorer.add(sommetHaut);
                        }
                    }
                    // la même chose pour le sommet Bas
                    if (isValideDeplacementPossible(sommetBas)) {
                        if (!contient(liste_sommets_deja_explorer,sommetBas) && !contient(prochain_sommets_a_explorer,sommetBas)) {
                            prochain_sommets_a_explorer.add(sommetBas);
                        }
                    }
                    // la même chose pour le sommet gauche
                    if (isValideDeplacementPossible(sommetGauche)) {
                        if (!contient(liste_sommets_deja_explorer,sommetGauche) && !contient(prochain_sommets_a_explorer,sommetGauche)) {
                            prochain_sommets_a_explorer.add(sommetGauche);
                        }
                    }
                    // la même chose pour le sommet droite
                    if (isValideDeplacementPossible(sommetDroite)) {
                        if (!contient(liste_sommets_deja_explorer,sommetDroite) && !contient(prochain_sommets_a_explorer,sommetDroite)) {
                            prochain_sommets_a_explorer.add(sommetDroite);
                        }
                    }

                    //on dit que la case que l'on vient de traiter à déja été exploré pour éviter les boucles infinies
                    liste_sommets_deja_explorer.add(positionSommet);

                }

                // on prépare les listes pour la prochaines itérations
                liste_sommets_a_explorer.clear();
                liste_sommets_a_explorer.addAll(prochain_sommets_a_explorer);
                prochain_sommets_a_explorer.clear();
            }
            // si il y a aucun sommet a explorer c'est qu'il n'y a pas de chemin possible
            else{
                for(int j = 0; j < liste_sommets_deja_explorer.size(); j ++) {
                    int[] sommet = liste_sommets_deja_explorer.get(j);
                }
                return false;
            }
        }
    }

    // on regarde si une case de la matrice est vide (vaut 0) et si elle est à l'intérieur de la grille
    private boolean isValideDeplacementPossible(int[] sommet){
        if(sommet[0]<=8 && sommet[0]>=0 && sommet[1]<=8 && sommet[1]>=0){
            return this.grid[sommet[0]][sommet[1]] == 0;
        }
        return false;
    }

    // on regarde si une case est dans la liste passé en paramètre
    private static boolean contient(ArrayList<int[]> liste,int[]sommet){
        for(int[] iterationSommet : liste){
            if(iterationSommet[0]== sommet[0] && iterationSommet[1]== sommet[1]){
                return true;
            }
        }
        return false;
    }

    public void pendantDeplacement() {
        int[] nextEmp = mGrille.prochainEmplacement();
        mettreGeo(this.prochaineBille, nextEmp);
        int[] p = mGrille.prochaineBille();
        setProchaineBille(p);
        mettreGeoNext(this.prochaineBille);
    }

    public int[] getProchaineBille() {
        return prochaineBille;
    }

    public void setProchaineBille(int[] prochaineBille) {
        this.prochaineBille = prochaineBille;
    }

    // pour mettre les formes géométrique dans une liste, qui représente celle du tableau
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
        }
    }

    // pour mettre les formes géométrique suivante dans une liste
    // on initialise aussi les coordonnées de la forme
    public void mettreGeoNext(int[] nextBille) {
        for(int i = 0; i < 3; i++){
            if (nextBille[i] == 1)
                mGrille.getNext().set(i, new Triangle(casesNext.get(i), new float[]{0.0f, 5.0f, 0.0f,
                                                                                    -5.0f, -5.0f, 0.0f,
                                                                                    5.0f, -5.0f, 0.0f}, 0.9f, 0f, 0.1f));
            if (nextBille[i] == 2)
                mGrille.getNext().set(i, new Pentagone(casesNext.get(i), new float[]{0f,5.f, 0.0f,
                                                                                    -5.f,1.f, 0.0f,
                                                                                    5.f,1.f, 0.0f,
                                                                                    -3.f,-5f, 0.0f,
                                                                                    3.f,-5f, 0.0f}, 1f, 0.9f, 0f));
            if (nextBille[i] == 3)
                mGrille.getNext().set(i, new Carres(casesNext.get(i), new float[]{-5.f,   5.f, 0.0f,
                                                                                -5.f,  -5.f, 0.0f,
                                                                                5.f,  -5.f, 0.0f,
                                                                                5.f,  5.f, 0.0f }, 1f, 0f, 0.9f));
            if (nextBille[i] == 4)
                mGrille.getNext().set(i, new Losange(casesNext.get(i),new float[]{0.0f,5.f, 0.0f,
                                                                                -4.f,  0.0f, 0.0f,
                                                                                0.0f,  -5.f, 0.0f,
                                                                                4f,  0.0f, 0.0f}, 0.5f, 0.5f, 0.5f));
            if (nextBille[i] == 5)
                mGrille.getNext().set(i, new TriangleInverse(casesNext.get(i),new float[]{0.0f,   -5.f, 0.0f,
                                                                                        -5.f,  5.f, 0.0f,
                                                                                        5.f,  5.f, 0.0f}, 0f, 1f, 1f));
            if (nextBille[i] == 6)
                mGrille.getNext().set(i, new Hexagone(casesNext.get(i),new float[]{0.0f,   5.f, 0.0f,
                                                                                -5.f,  2.5f, 0.0f,
                                                                                5.f,  2.5f, 0.0f,
                                                                                -5.f,  -2.5f, 0.0f,
                                                                                5.f,  -2.5f, 0.0f,
                                                                                0.0f,   -5.f, 0.0f}, 0.5f, 0f, 1f));
            if (nextBille[i] == 7)
                mGrille.getNext().set(i, new Octogone(casesNext.get(i),new float[]{0.0f,   5.f, 0.0f,
                                                                                -3.5f,  3.5f, 0.0f,
                                                                                3.5f,  3.5f, 0.0f,
                                                                                -5.f,  0.0f, 0.0f,
                                                                                5.f,  0.0f, 0.0f,
                                                                                -3.5f,  -3.5f, 0.0f,
                                                                                3.5f,  -3.5f, 0.0f,
                                                                                0.0f,   -5.f, 0.0f}, 0.1f, 1f, 0f));
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
        if(!positionAEnlever.isEmpty()) {
            for (int i = 0; i < positionAEnlever.size(); i++) {
                float[] pos = positionAEnlever.get(i);
                Log.d("EnleverLigne","position " + (((pos[0]-1)*9+pos[1])-1));
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

        while(horizontal || vertical || diagonal || opposite ||
                horizontal2 || vertical2 || diagonal2 || opposite2) {

            int x,y;
            if (vertical) {
                x = ligneP + i;
                y = colonneP;
                if (x <= 9) {
                    Geometrie formeTest = this.mGrille.getGrille().get(((x-1)*9 + y)-1);
                    //if (this.mGrille.getGrille().get(((x-1)*9 + y)-1) == forme) {
                    //if (this.mGrille.getGrille().get(((x-1)*9 + y)-1).equals(forme)) {
                    if (forme.equals(formeTest)) {
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
        add(positionAEnlever, horizontals);
        add(positionAEnlever, verticals);
        add(positionAEnlever, diagonals);
        add(positionAEnlever, opposites);

        for (int j = 0; j < positionAEnlever.size(); j++){
            float[] m = positionAEnlever.get(j);
        }

        if (positionAEnlever.size() < 5) {
            positionAEnlever.clear();
        }

        return positionAEnlever;
    }

    private static void add(ArrayList<float[]> source, ArrayList<float[]> collection) {
        if (collection.size() >= 4) {
            source.addAll(collection);
        }
    }

}

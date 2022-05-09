/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cinqouplus;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* La classe MyGLSurfaceView avec en particulier la gestion des événements
  et la création de l'objet renderer

*/

public class MyGLSurfaceView extends GLSurfaceView {

    /* Un attribut : le renderer (GLSurfaceView.Renderer est une interface générique disponible) */
    /* MyGLRenderer va implémenter les méthodes de cette interface */

    private MyGLRenderer mRenderer;
    private MyGLSurfaceViewNext surfaceViewNext;
    private Vibrator vibrator;

    public MyGLSurfaceView(Context context) {
        super(context);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Vibrator vibrator, MyGLSurfaceViewNext viewNext){
        this.vibrator = vibrator;
        this.surfaceViewNext = viewNext;

        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        // Création d'un context OpenGLES 3.0
        setEGLContextClientVersion(3);

        // Création du renderer qui va être lié au conteneur View créé
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        // Option pour indiquer qu'on redessine uniquement si les données changent
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public MyGLRenderer getmRenderer() {
        return mRenderer;
    }

    /* pour gérer la translation */
    private boolean condition = false;
    private boolean fini = false;

    /* Comment interpréter les événements sur l'écran tactile */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // Les coordonnées du point touché sur l'écran
        float x = e.getX();
        float y = e.getY();

        /* accès aux paramètres du rendu (cf MyGLRenderer.java)
        soit la position courante du centre du carré
         */
        float[] pos = mRenderer.getPosition();

        /* Conversion des coordonnées pixel en coordonnées OpenGL
        Attention l'axe x est inversé par rapport à OpenGLSL
        On suppose que l'écran correspond à un carré d'arête 2 centré en 0
         */

        float x_opengl = 20.0f*x/getWidth() - 10.0f;
        float y_opengl = -20.0f*y/getHeight() + 10.0f;

        Log.d("message","x_opengl="+Float.toString(x_opengl));
        Log.d("message","y_opengl="+Float.toString(y_opengl));

        /* Le carré représenté a une arête de 2 (oui il va falloir changer cette valeur en dur !!)
        /* On teste si le point touché appartient au carré ou pas car on ne doit le déplacer que si ce point est dans le carré
        */

        boolean test_square = ((x_opengl < pos[0]+1.0) && (x_opengl > pos[0]-1.0) && (y_opengl < pos[1]+1.0) && (y_opengl > pos[1]-1.0));

        Log.d("message","test_square="+Boolean.toString(test_square));
        Log.d("message","condition="+Boolean.toString(condition));

        // Les cases de ma grille sont initialiser ici, en fonction de ou l'on touche dans la view, on voit dans quelle case on est
        boolean test_1 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));
        boolean test_2 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));
        boolean test_3 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));
        boolean test_4 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));
        boolean test_5 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));
        boolean test_6 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));
        boolean test_7 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));
        boolean test_8 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));
        boolean test_9 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]+7.1) && (y_opengl < pos[1]+9.1));

        boolean test_10 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));
        boolean test_11 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));
        boolean test_12 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));
        boolean test_13 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));
        boolean test_14 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));
        boolean test_15 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));
        boolean test_16 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));
        boolean test_17 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));
        boolean test_18 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]+4.9) && (y_opengl < pos[1]+7.1));

        boolean test_19 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));
        boolean test_20 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));
        boolean test_21 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));
        boolean test_22 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));
        boolean test_23 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));
        boolean test_24 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));
        boolean test_25 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));
        boolean test_26 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));
        boolean test_27 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]+2.9) && (y_opengl < pos[1]+4.9));

        boolean test_28 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));
        boolean test_29 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));
        boolean test_30 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));
        boolean test_31 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));
        boolean test_32 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));
        boolean test_33 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));
        boolean test_34 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));
        boolean test_35 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));
        boolean test_36 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]+1) && (y_opengl < pos[1]+2.9));

        boolean test_37 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));
        boolean test_38 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));
        boolean test_39 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));
        boolean test_40 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));
        boolean test_41 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));
        boolean test_42 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));
        boolean test_43 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));
        boolean test_44 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));
        boolean test_45 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]-1) && (y_opengl < pos[1]+1));

        boolean test_46 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));
        boolean test_47 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));
        boolean test_48 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));
        boolean test_49 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));
        boolean test_50 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));
        boolean test_51 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));
        boolean test_52 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));
        boolean test_53 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));
        boolean test_54 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]-3) && (y_opengl < pos[1]-1));

        boolean test_55 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));
        boolean test_56 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));
        boolean test_57 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));
        boolean test_58 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));
        boolean test_59 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));
        boolean test_60 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));
        boolean test_61 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));
        boolean test_62 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));
        boolean test_63 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]-5) && (y_opengl < pos[1]-3));

        boolean test_64 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));
        boolean test_65 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));
        boolean test_66 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));
        boolean test_67 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));
        boolean test_68 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));
        boolean test_69 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));
        boolean test_70 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));
        boolean test_71 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));
        boolean test_72 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]-7) && (y_opengl < pos[1]-5));

        boolean test_73 = ((x_opengl > pos[0]-8.4) && (x_opengl < pos[0]-6.5) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));
        boolean test_74 = ((x_opengl > pos[0]-6.5) && (x_opengl < pos[0]-4.7) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));
        boolean test_75 = ((x_opengl > pos[0]-4.7) && (x_opengl < pos[0]-2.8) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));
        boolean test_76 = ((x_opengl > pos[0]-2.8) && (x_opengl < pos[0]-0.9) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));
        boolean test_77 = ((x_opengl > pos[0]-0.9) && (x_opengl < pos[0]+0.9) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));
        boolean test_78 = ((x_opengl > pos[0]+0.9) && (x_opengl < pos[0]+2.7) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));
        boolean test_79 = ((x_opengl > pos[0]+2.7) && (x_opengl < pos[0]+4.6) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));
        boolean test_80 = ((x_opengl > pos[0]+4.6) && (x_opengl < pos[0]+6.5) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));
        boolean test_81 = ((x_opengl > pos[0]+6.5) && (x_opengl < pos[0]+8.3) && (y_opengl > pos[1]-9) && (y_opengl < pos[1]-7));

        List<Boolean> test = new ArrayList<>(Arrays.asList(test_1, test_2, test_3, test_4, test_5, test_6, test_7, test_8, test_9, test_10, test_11, test_12, test_13, test_14, test_15, test_16, test_17, test_18, test_19, test_20, test_21, test_22, test_23, test_24, test_25, test_26, test_27, test_28, test_29, test_30, test_31, test_32, test_33, test_34, test_35, test_36, test_37, test_38, test_39, test_40, test_41, test_42, test_43, test_44, test_45, test_46, test_47, test_48, test_49, test_50, test_51, test_52, test_53, test_54, test_55, test_56, test_57, test_58, test_59, test_60, test_61, test_62, test_63, test_64, test_65, test_66, test_67, test_68, test_69, test_70, test_71, test_72, test_73, test_74, test_75, test_76, test_77, test_78, test_79, test_80, test_81));

        if (this.mRenderer.getJeu().estDemarrer() || (test_1 || test_2 || test_3 || test_4 || test_5 || test_6 || test_7 || test_8 || test_9 || test_10 || test_11 || test_12 || test_13 || test_14 || test_15 || test_16 || test_17 || test_18 || test_19 || test_20 || test_21 || test_22 || test_23 || test_24 || test_25 || test_26 || test_27 || test_28 || test_29 || test_30 || test_31 || test_32 || test_33 || test_34 || test_35 || test_36 || test_37 || test_38 || test_39 || test_40 || test_41 || test_42 || test_43 || test_44 || test_45 || test_46 || test_47 || test_48 || test_49 || test_50 || test_51 || test_52 || test_53 || test_54 || test_55 || test_56 || test_57 || test_58 || test_59 || test_60 || test_61 || test_62 || test_63 || test_64 || test_65 || test_66 || test_67 || test_68 || test_69 || test_70 || test_71 || test_72 || test_73 || test_74 || test_75 || test_76 || test_77 || test_78 || test_79 || test_80 || test_81)) {
            for (int i = 0; i < test.size(); i++) {
                if (test.get(i) && this.mRenderer.getJeu().getmGrille().isGeo(i)) {
                    this.mRenderer.getJeu().setDerniereCaseTouche(i);
                    this.mRenderer.getJeu().setCondition(true);
                }
            }
            if (e.getAction() == MotionEvent.ACTION_UP && this.mRenderer.getJeu().getCondition()) {
                for (int i = 0; i < test.size(); i++) {
                    if (test.get(i) && !this.mRenderer.getJeu().getmGrille().isGeo(i)) {
                        if (this.mRenderer.getJeu().siDeplacement(this.mRenderer.getJeu().getDerniereCaseTouche(), i)) {
                            this.mRenderer.getJeu().getmGrille().faireDeplacment(mRenderer.getJeu().getCases(), this.mRenderer.getJeu().getDerniereCaseTouche(), i);
                            this.mRenderer.getJeu().pendantDeplacement();

                            this.mRenderer.getJeu().enleverLigne(i);
                            this.mRenderer.getJeu().setCondition(false);
                            int score = mRenderer.getJeu().getScore().getScore();
                            OpenGLES30Activity.setTextScore(score);

                            //création de surfaceViewNext pour afficher les nouvelles formes sur une autre view
                            int[] prochaineBille = mRenderer.getJeu().getProchaineBille();
                            surfaceViewNext.bille(prochaineBille);
                            surfaceViewNext.onTouchEvent(e);
                            OpenGLES30Activity.setNext(this.surfaceViewNext);

                            if(this.mRenderer.getJeu().getEstTermine()) {
                                this.mRenderer.getJeu().setEstDemarrer(false);
                                this.fini = true;
                                OpenGLES30Activity.setTextFini();
                            }
                            this.requestRender();
                        }
                    }
                }
            }
        }else {
            vibrator.vibrate(100);
            Log.d("partie pas commencé", "vibration");
            this.requestRender();
        }
        return true;
    }

    public void startGrille () {
        this.mRenderer.startGrille();
        surfaceViewNext.bille(mRenderer.getJeu().getProchaineBille());
        this.requestRender();
    }
}

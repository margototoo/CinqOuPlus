package com.example.cinqouplus;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyGLSurfaceViewNext extends MyGLSurfaceView{

    private MyGLRendererNext mRendererNext;
    private int[] prochaineBille;

    public MyGLSurfaceViewNext(Context context) {
        super(context);
    }

    public MyGLSurfaceViewNext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        // Création d'un context OpenGLES 3.0
        setEGLContextClientVersion(3);

        // Création du renderer qui va être lié au conteneur View créé
        mRendererNext = new MyGLRendererNext();
        setRenderer(mRendererNext);

        // Option pour indiquer qu'on redessine uniquement si les données changent
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void bille (int[] prochaineBille) {
        this.prochaineBille = prochaineBille;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();

        /* Conversion des coordonnées pixel en coordonnées OpenGL
        Attention l'axe x est inversé par rapport à OpenGLSL
        On suppose que l'écran correspond à un carré d'arête 2 centré en 0
         */

        float x_opengl = 20.0f*x/getWidth() - 10.0f;
        float y_opengl = -20.0f*y/getHeight() + 10.0f;

        Log.d("message","x_opengl="+Float.toString(x_opengl));
        Log.d("message","y_opengl="+Float.toString(y_opengl));

        mRendererNext.getJeu().setProchaineBille(prochaineBille);
        mRendererNext.getJeu().mettreGeoNext(prochaineBille);

        this.requestRender();
        return true;
    }

    public void startNext() {
        mRendererNext.getJeu().setProchaineBille(prochaineBille);
        mRendererNext.getJeu().mettreGeoNext(prochaineBille);
        this.requestRender();
    }
}


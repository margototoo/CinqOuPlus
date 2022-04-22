package com.example.cinqouplus;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyGLSurfaceViewNext extends MyGLSurfaceView{

    private MyGLRendererNext mRenderer;

    public MyGLSurfaceViewNext(Context context) {
        super(context);
    }

    public MyGLSurfaceViewNext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*public static void draw() {
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }*/

    /* Un attribut : le renderer (GLSurfaceView.Renderer est une interface générique disponible) */
    /* MyGLRenderer va implémenter les méthodes de cette interface */

    /*private MyGLRenderer mRenderer;
    private Vibrator vibrator;

    public MyGLSurfaceViewNext(Context context) {
        super(context);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }*/

    public void init(){

        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        // Création d'un context OpenGLES 3.0
        setEGLContextClientVersion(3);

        // Création du renderer qui va être lié au conteneur View créé
        mRenderer = new MyGLRendererNext();
        setRenderer(mRenderer);

        // Option pour indiquer qu'on redessine uniquement si les données changent
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}


package com.example.cinqouplus;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.example.cinqouplus.jeu.Jeu;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRendererNext implements GLSurfaceView.Renderer{

    private MyGLRenderer myGLRenderer;
    private Jeu jeu;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES30.glClearColor(0.93f, 0.93f, 0.93f, 1.0f);
        this.jeu = new Jeu();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        /* ici on aurait pu se passer de cette méthode et déclarer
        la projection qu'à la création de la surface !!
         */
        GLES30.glViewport(0, 0, width, height);

        float smaller = Math.min(width, height);
        float ratio = (float) Math.max(width, height) / smaller;

        float left = smaller == width ? -10.0f : -10.0f * ratio;
        float right = smaller == width ? 10.0f : 10.0f * ratio;
        float top = smaller == height ? 10.0f : 10.0f * ratio;
        float bottom = smaller == height ? -10.0f : -10.0f * ratio;

        //Matrix.orthoM(mProjectionMatrix, 0, -10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 1.0f);
        Matrix.orthoM(mProjectionMatrix, 0, left, right, bottom, top, -1.0f, 1.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        float[] scratch = new float[16]; // pour stocker une matrice

        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        Matrix.setIdentityM(mViewMatrix,0);

        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        Matrix.setIdentityM(mModelMatrix,0);

        Matrix.translateM(mModelMatrix, 0, 0, 0, 0);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mModelMatrix, 0);

/*        if(myGLRenderer.getJeu().estDemarrer()){
            //this.jeu = myGLRenderer.getJeu();
            jeu.dessinerNext(scratch);
        }*/
    }
}

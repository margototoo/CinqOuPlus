package com.example.cinqouplus;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

/* Ce tutorial est issu d'un tutorial http://developer.android.com/training/graphics/opengl/index.html :
openGLES.zip HelloOpenGLES20
 */


public class OpenGLES30Activity extends AppCompatActivity {

    // le conteneur View pour faire du rendu OpenGL
    private MyGLSurfaceView mGLView;
    private static TextView score;
    private static TextView fini;
    private int counterScore;

    private static MyGLSurfaceViewNext mGLViewNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        setContentView(R.layout.activity_jeu);

        mGLViewNext = this.findViewById(R.id.nextForm);
        mGLViewNext.init();

        this.mGLView = this.findViewById(R.id.surfaceView);
        this.mGLView.init(v,mGLViewNext);

        score = this.findViewById(R.id.score);
        fini = this.findViewById(R.id.fini);
    }

    public static void setTextScore(int scores) {
        score.setText(String.format(String.valueOf(scores)));
    }

    public static void setTextFini() {
        fini.setText("Partie terminée");
    }

    public static void setNext(MyGLSurfaceViewNext view) {
        mGLViewNext = view;
    }

    public void start(View view) {
        this.mGLView.startGrille();
        this.mGLViewNext.startNext();
        this.counterScore = mGLView.getmRenderer().getJeu().getScore().getScore();
        this.score.setText(String.format(String.valueOf(this.counterScore)));
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void finish() {
        super.finish();
    }
}

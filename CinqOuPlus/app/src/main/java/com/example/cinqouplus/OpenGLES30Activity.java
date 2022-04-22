package com.example.cinqouplus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.cinqouplus.MainActivity;
import com.example.cinqouplus.R;
import com.example.cinqouplus.MyGLSurfaceView;
import com.example.cinqouplus.jeu.Score;

import java.util.Locale;

/* Ce tutorial est issu d'un tutorial http://developer.android.com/training/graphics/opengl/index.html :
openGLES.zip HelloOpenGLES20
 */


public class OpenGLES30Activity extends AppCompatActivity {

    // le conteneur View pour faire du rendu OpenGL
    private MyGLSurfaceView mGLView;
    private TextView score;
    private Chronometer chronometer;
    private FragmentGameOverActivity gameOver;
    private boolean onstart = false;
    private int counterScore;
    private int base;

    private MyGLSurfaceViewNext mGLViewNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Création de View et association à Activity
           MyGLSurfaceView : classe à implémenter et en particulier la partie renderer */

        /* Pour le plein écran */
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mGLView = new MyGLSurfaceView(this);
        // Définition de View pour cette activité
        setContentView(mGLView); */

        Intent intent = getIntent();

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Log.d("ya soucis là", "oncreat");
        setContentView(R.layout.activity_jeu);
        this.mGLView = this.findViewById(R.id.surfaceView);
        this.mGLView.init(v);

        this.mGLViewNext = this.findViewById(R.id.nextForm);
        this.mGLViewNext.init();

        this.score = this.findViewById(R.id.score);
        //this.score.getScore();
        //if(!this.findViewById(R.id.button).isFocusable()) {
        Log.d("onstart", String.valueOf(onstart));
        Log.d("bouton enable", String.valueOf(this.findViewById(R.id.button).isEnabled()));
        if(!this.findViewById(R.id.button).isEnabled()) {
            this.counterScore = mGLView.getmRenderer().getJeu().getScore().getScore();
            Log.d("score dans oncreate", String.valueOf(this.counterScore));
            //this.score.setText(Integer.toString(this.counterScore));
            this.score.setText(String.format(Locale.getDefault(), "%d", counterScore));
        }
        changerScore();

        //changerScore();
        this.gameOver = new FragmentGameOverActivity();
        //Mettre un chronometre
        /*this.base = 60;
        this.counter = this.base * 10;
        this.chronometer = this.findViewById(R.id.chronometer);
        this.chronometer.setText(formatCounter(this.counter));
        this.chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                onChronometerTichHandler();
            }
        });*/

    }

    public void changerScore() {
        Log.d("onstart 2", String.valueOf(onstart));
    }

    /*
    private void onChronometerTichHandler() {
        if (this.counter <= 0 || this.mGLView.isCombination_finded()) {
            this.chronometer.stop();
            this.mGLView.stopPlaying();

            if (this.counter >= 0 && this.mGLView.isCombination_finded()) { this.notification.setWinner(true); }
            else { this.notification.setWinner(false); }

            this.notification.setTime(this.base - this.counter);

            // afficher la pop-up
            this.notification.show(this.getSupportFragmentManager(), "notification");
        }
        else {
            this.counter--;
        }
        this.chronometer.setText(this.formatCounter(this.counter));
    }*/

    private String formatCounter(int time) {
        String secondes = Integer.toString(time % 60);
        String minutes = Integer.toString((time / 60) % 60);
        secondes = secondes.length() == 1 ? "0" + secondes : secondes;
        minutes = minutes.length() == 1 ? "0" + minutes : minutes;
        return minutes + ":" + secondes;
    }

    public void start(View view) {
        //this.chronometer.start();
        this.mGLView.startGrille();
        this.counterScore = mGLView.getmRenderer().getJeu().getScore().getScore();
        this.score.setText(Integer.toString(this.counterScore));
        setOnstart(true);
        view.setVisibility(View.INVISIBLE);
        //view.setEnabled(false);
    }

    public void setOnstart(boolean onstart) {
        this.onstart = onstart;
    }

    public void pause(View view) {
        //this.chronometer.stop();
        //this.mGLView.mixeGrid();
    }

    @Override
    public void finish() {
        super.finish();
        this.chronometer.stop();
        //this.overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }
}

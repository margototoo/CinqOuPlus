package com.example.cinqouplus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cinqouplus.jeu.Score;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*final Button button = findViewById(R.id.buttonJouer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }
        });*/
        setContentView(R.layout.activity_main);

        /*buttonJouer = (Button) findViewById(R.id.buttonJouer);
        buttonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToJouer();
            }
        });*/
    }

    public void jouer(View view) {
        Log.d("jouer","boutonjouer");
        Intent intent = new Intent(this, OpenGLES30Activity.class);
        startActivity(intent);
        //registerForActivityResult(this, OpenGLES30Activity.class);
    }

    public void goToJeu () {
        Log.d("jouer","boutonjouer");
        Intent intent = new Intent(this, OpenGLES30Activity.class);
        //this.registerForActivityResult();
        this.startActivityForResult(intent, 0);
        startActivity(intent);
    }

    /*public void goToJouer(){

    }*/
}
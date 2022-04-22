package com.example.cinqouplus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FragmentGameOverActivity extends DialogFragment {

    private String time;
    private String score;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View root = LayoutInflater.from(this.getActivity()).inflate(R.layout.fragment_gameover, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setView(root);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        // afficher le temps de jeu
        TextView chronometer = (TextView) root.findViewById(R.id.chronometer);
        chronometer.setText(this.time);

        Button back = (Button) root.findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return dialog;
    }

    public void setTime(int time) {
        String secondes = Integer.toString(time % 60) + "s";
        String minutes = (time / 60) % 60 == 0 ? "" : Integer.toString((time / 60) % 60) + "min ";
        this.time = "Terminé en : " + minutes + secondes;
    }

    public void setScore(int score) {

    }
}

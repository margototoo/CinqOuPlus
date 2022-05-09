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

    private int score;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View root = LayoutInflater.from(this.getActivity()).inflate(R.layout.fragment_gameover, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setView(root);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        // afficher le score de jeu
        TextView resultat = root.findViewById(R.id.score);
        resultat.setText(this.score);

        Button back = root.findViewById(R.id.button);
        back.setOnClickListener(v -> getActivity().finish());

        return dialog;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

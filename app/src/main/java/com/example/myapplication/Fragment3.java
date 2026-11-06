package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.Toast;

public class Fragment3 extends Fragment {

    private static final String ARG_NAME = "name";

    public interface OnConfirmationChangeListener {
        void onConfirmationChanged(boolean isChecked);
    }

    public Fragment3() {
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();

        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_3, container, false);

        TextView textViewName =
                view.findViewById(R.id.textViewName);

        CheckBox checkBoxConfirm =
                view.findViewById(R.id.checkBoxConfirm);

        Button buttonFinish =
                view.findViewById(R.id.buttonFinish);

        String name = "";

        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME, "");
        }

        textViewName.setText("Welcome, " + name);

        buttonFinish.setEnabled(false);
        buttonFinish.setText("Continue");

        OnConfirmationChangeListener listener =
                isChecked -> {
                    buttonFinish.setEnabled(isChecked);

                    if (isChecked) {
                        buttonFinish.setText("Finish");
                    } else {
                        buttonFinish.setText("Continue");
                    }
                };

        checkBoxConfirm.setOnCheckedChangeListener(
                (buttonView, isChecked) ->
                        listener.onConfirmationChanged(isChecked)
        );

        buttonFinish.setOnClickListener(v -> {
            Toast.makeText(requireContext(),
                    "Registration completed successfully!",
                    Toast.LENGTH_LONG).show();
        });

        return view;
    }
}
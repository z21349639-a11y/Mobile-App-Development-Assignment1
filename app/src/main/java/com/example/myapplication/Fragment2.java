package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.databinding.Fragment2Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    public interface OnContinueListener {
        void onContinue(String userName);
    }

    private OnContinueListener listener;
    @Override
    public void onAttach(android.content.Context context) {
        super.onAttach(context);

        if (context instanceof OnContinueListener) {
            listener = (OnContinueListener) context;
        } else {
            throw new ClassCastException(
                    context.toString() + " must implement OnContinueListener"
            );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static Fragment2Binding fragment2Binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment2Binding = Fragment2Binding.inflate(inflater, container, false);
        fragment2Binding.buttonContinue.setOnClickListener(v -> {
            String name = fragment2Binding.editText.getText().toString().trim();
            String email = fragment2Binding.editTextEmail.getText().toString().trim();
            int gender = fragment2Binding.spinnerGender.getSelectedItemPosition();

            if (name.isEmpty()) {
                fragment2Binding.editText.setError("Enter your name");
                return;
            }

            if (email.isEmpty() ||
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                fragment2Binding.editTextEmail.setError("Enter a valid email");
                return;
            }

            if (gender == 0) {
                android.widget.Toast.makeText(requireContext(),
                        "Select your gender",
                        android.widget.Toast.LENGTH_SHORT).show();
                return;
            }

            android.widget.Toast.makeText(requireContext(),
                    "Information saved successfully",
                    android.widget.Toast.LENGTH_SHORT).show();
            listener.onContinue(name);
        });
        return fragment2Binding.getRoot();
    }
}
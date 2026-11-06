package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnContinueListener {
    private ActivityMainBinding binding;
    private int currentFragment = 1;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            Fragment1.newInstance(null, null))
                    .commit();
        }

        binding.buttonContinue.setOnClickListener(v -> {
            if (currentFragment == 1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                Fragment2.newInstance(null, null))
                        .addToBackStack(null)
                        .commit();

                currentFragment = 2;
            }
        });
    }

    public void onContinue(String userName) {
        name = userName;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        Fragment3.newInstance(name))
                .addToBackStack(null)
                .commit();

        currentFragment = 3;
        binding.buttonContinue.setVisibility(
                android.view.View.GONE);
    }
}
package com.example.pokemon.Presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pokemon.R;

    public class Menu extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_first_page);
        }

        public void pokemon_list_activity(View view) {
            Intent randomIntent = new Intent(this, MainActivity.class);
            startActivity(randomIntent);

        }
    }
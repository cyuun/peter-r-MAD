package com.example.ferretappthree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
    }

    public void viewFerret(View view) {
        Integer ferretNumber = spinner.getSelectedItemPosition();
        Intent intent = new Intent(this, FerretActivity.class);
        intent.putExtra("ferretNumber", ferretNumber);
        startActivity(intent);
    }
}

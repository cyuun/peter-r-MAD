package com.example.ferretselector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText textEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEntry = findViewById(R.id.editText);
    }

    public void SetName(android.view.View view) {
        TextView textShown = findViewById(R.id.textView);
        ImageView imageShown = findViewById(R.id.imageView);

        String textEntered = textEntry.getText().toString();

        textShown.setText("Oh hi " + textEntered + "!");

        if (textEntered.toLowerCase().charAt(0) > 'm') {
            imageShown.setImageResource(R.drawable.ferret0);
        }
        else {
            imageShown.setImageResource(R.drawable.ferret1);
        }
    }
}

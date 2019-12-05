package com.example.ferretappthree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FerretActivity extends AppCompatActivity {

    private String ferretURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferret);

        ImageView imageView = findViewById(R.id.imageView);
        ferretURL = "https://en.wikipedia.org/wiki/Ferret";

        Intent intent = getIntent();
        int ferretNumber = intent.getIntExtra("ferretNumber", 0);
        switch (ferretNumber) {
            default:
            case 0:
                imageView.setImageResource(R.drawable.ferret_0);
                break;
            case 1:
                imageView.setImageResource(R.drawable.ferret_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ferret_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ferret_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ferret_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.ferret_5);
                ferretURL = "https://avatar.fandom.com/wiki/Pabu";
                break;
        }
    }

    public void moreInfo(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(ferretURL));
        startActivity(intent);
    }

}

package com.example.peterrosenthalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BurritoActivity extends AppCompatActivity {

    private TextView textView;
    private Burrito burrito;
    private int spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burrito);

        textView = findViewById(R.id.textView2);
        Intent intent = getIntent();
        spinnerPosition = intent.getIntExtra("spinnerPos", 0);
        burrito = new Burrito();
        textView.setText("You should eat at " + burrito.getRestaurantByNumber(spinnerPosition) + ".");
    }

    public void OpenWebsite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(burrito.getURLByNumber(spinnerPosition)));
        startActivity(intent);
    }
}

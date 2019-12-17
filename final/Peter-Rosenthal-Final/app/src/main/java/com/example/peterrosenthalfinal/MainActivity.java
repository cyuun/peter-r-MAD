package com.example.peterrosenthalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private ToggleButton fillingToggle;
    private RadioGroup radioGroup;
    private CheckBox salsaCheckBox;
    private CheckBox creamCheckBox;
    private CheckBox cheeseCheckBox;
    private CheckBox guacCheckBox;
    private Spinner locationSpinner;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.editText);
        fillingToggle = findViewById(R.id.toggleButton);
        radioGroup = findViewById(R.id.radioGroup2);
        salsaCheckBox = findViewById(R.id.checkBox);
        creamCheckBox = findViewById(R.id.checkBox2);
        cheeseCheckBox = findViewById(R.id.checkBox3);
        guacCheckBox = findViewById(R.id.checkBox4);
        locationSpinner = findViewById(R.id.spinner);
        outputText = findViewById(R.id.textView);
    }

    public void BuildBurrito(View view) {
        String name = nameEditText.getText().toString();
        String form = "";
        String filling = "";
        String salsa = "";
        String cream = "";
        String cheese = "";
        String guac = "";
        String location = "";

        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == -1) {
            Context context = getApplicationContext();
            CharSequence toastText = "Please choose a burrito or taco";
            int toastDuration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, toastText, toastDuration);
            toast.show();
            return;
        }
        switch (checkedRadioButtonId) {
            case R.id.radioButton:
                form = "burrito";
                break;
            default:
            case R.id.radioButton2:
                form = "taco";
                break;
        }

        if (fillingToggle.isChecked()) {
            filling = "meat";
        }
        else {
            filling = "veggie";
        }

        if (salsaCheckBox.isChecked()){
            salsa = "salsa ";
        }

        if (creamCheckBox.isChecked()) {
            cream = "cream ";
        }

        if (cheeseCheckBox.isChecked()) {
            cheese = "cheese ";
        }

        if (guacCheckBox.isChecked()) {
            guac = "guac";
        }

        //Burrito burrito = new Burrito();
        //int spinnerPosition = locationSpinner.getSelectedItemPosition();
        //location = burrito.getRestaurantByNumber(spinnerPosition);


        outputText.setText(name + " wants a " + form + " with " + filling + ", and " + salsa + cream + cheese + guac + "."/* You should eat at " + location + "."*/);
    }

    public void OpenRestaurant(View view) {
        int spinnerPosition = locationSpinner.getSelectedItemPosition();
        Intent intent = new Intent(this, BurritoActivity.class);
        intent.putExtra("spinnerPos", spinnerPosition);
        startActivity(intent);
    }
}

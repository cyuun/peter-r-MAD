package com.example.fsplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private EditText nameEnter;
    private ToggleButton lrToggle;
    private RadioGroup colorGroup;
    private CheckBox blueeyesCheckBox;
    private CheckBox twotoneCheckBox;
    private TextView outputText;
    private ImageView ferretView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEnter = findViewById(R.id.editText);
        lrToggle = findViewById(R.id.toggleButton);
        colorGroup = findViewById(R.id.radioGroup);
        blueeyesCheckBox = findViewById(R.id.checkBox);
        twotoneCheckBox = findViewById(R.id.checkBox2);
        outputText = findViewById(R.id.textView);
        ferretView = findViewById(R.id.imageView);
    }

    public void showFerret (View view) {
        String name = nameEnter.getText().toString();
        boolean isLeft = lrToggle.isChecked();
        int color_id = colorGroup.getCheckedRadioButtonId();
        boolean blueEyes = blueeyesCheckBox.isChecked();
        boolean twoTone = twotoneCheckBox.isChecked();

        if (name.isEmpty()) {
            Context context = getApplicationContext();
            CharSequence nameToastText = "Please enter a name";
            int nameToastDuration = Toast.LENGTH_SHORT;

            Toast nameToast = Toast.makeText(context, nameToastText, nameToastDuration);
            nameToast.show();
        }
        else if (color_id == -1) {
            Context context = getApplicationContext();
            CharSequence colorToastText = "Please select a color";
            int colorToastDuration = Toast.LENGTH_SHORT;

            Toast colorToast = Toast.makeText(context, colorToastText, colorToastDuration);
            colorToast.show();
        }
        else {
            String color = "";
            String direction = "";
            int index;

            if (color_id == R.id.radioButton) {
                color = "white";
            }
            else {
                color = "brown";
            }

            if (isLeft) {
                direction = "left";
            }
            else {
                direction = "right";
            }

            if (blueEyes && twoTone) {
                index = 3;
            }
            else if (twoTone) {
                index = 2;
            }
            else if (blueEyes) {
                index = 1;
            }
            else {
                index = 0;
            }

            outputText.setText("Oh hi " + name + "!");

            switch (color + "_" + direction + "_" + "ferret" + "_" + String.valueOf(index)) {
                case "brown_left_ferret_0":
                    ferretView.setImageResource(R.drawable.brown_left_ferret_0);
                    break;
                case "brown_left_ferret_1":
                    ferretView.setImageResource(R.drawable.brown_left_ferret_1);
                    break;
                case "brown_left_ferret_2":
                    ferretView.setImageResource(R.drawable.brown_left_ferret_2);
                    break;
                case "brown_left_ferret_3":
                    ferretView.setImageResource(R.drawable.brown_left_ferret_3);
                    break;
                case "brown_right_ferret_0":
                    ferretView.setImageResource(R.drawable.brown_right_ferret_0);
                    break;
                case "brown_right_ferret_1":
                    ferretView.setImageResource(R.drawable.brown_right_ferret_1);
                    break;
                case "brown_right_ferret_2":
                    ferretView.setImageResource(R.drawable.brown_right_ferret_2);
                    break;
                case "brown_right_ferret_3":
                    ferretView.setImageResource(R.drawable.brown_right_ferret_3);
                    break;
                case "white_left_ferret_0":
                    ferretView.setImageResource(R.drawable.white_left_ferret_0);
                    break;
                case "white_left_ferret_1":
                    ferretView.setImageResource(R.drawable.white_left_ferret_1);
                    break;
                case "white_left_ferret_2":
                    ferretView.setImageResource(R.drawable.white_left_ferret_2);
                    break;
                case "white_left_ferret_3":
                    ferretView.setImageResource(R.drawable.white_left_ferret_3);
                    break;
                case "white_right_ferret_0":
                    ferretView.setImageResource(R.drawable.white_right_ferret_0);
                    break;
                case "white_right_ferret_1":
                    ferretView.setImageResource(R.drawable.white_right_ferret_1);
                    break;
                case "white_right_ferret_2":
                    ferretView.setImageResource(R.drawable.white_right_ferret_2);
                    break;
                case "white_right_ferret_3":
                    ferretView.setImageResource(R.drawable.white_right_ferret_3);
                    break;
            }
        }
    }
}

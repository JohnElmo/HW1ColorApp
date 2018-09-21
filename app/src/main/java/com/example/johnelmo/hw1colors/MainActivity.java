package com.example.johnelmo.hw1colors;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button buttonColor;
    Button toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        textView = (TextView) findViewById(R.id.textView);

        buttonColor = (Button) findViewById(R.id.button);

        buttonColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Random rnd = new Random();
                int red = rnd.nextInt(256);
                int green = rnd.nextInt(256);
                int blue = rnd.nextInt(256);
                String htmlColorCode = "#" + intToHexString(red) + intToHexString(green) + intToHexString(blue);
                textView.setText("COLOR: " + red + "r, " + green + "g, " + blue + "b, " + htmlColorCode);
                editText.setTextColor(Color.parseColor(htmlColorCode));
            }
        });

        toggle = (Button) findViewById(R.id.button2);

        toggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeActivity(v);
            }
        });
    }

    public void changeActivity(View view) {
        Intent intent = new Intent(this, DrawingPanelActivity.class);
        startActivity(intent);
    }

    public String intToHexString(int color) {
        String hexString = Integer.toHexString(color);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }
}

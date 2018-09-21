package com.example.johnelmo.hw1colors;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.byox.drawview.enums.DrawingCapture;
import com.byox.drawview.views.DrawView;
import com.jaredrummler.android.colorpicker.ColorPanelView;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.jaredrummler.android.colorpicker.ColorPickerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrawingPanelActivity extends AppCompatActivity implements ColorPickerDialogListener {

    DrawView drawView;
    Button clear;
    Button save;
    Button changeColor;
    Button toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_panel);

        drawView = (DrawView) findViewById(R.id.draw_view);

        clear = (Button) findViewById(R.id.button4);

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawView.restartDrawing();
            }
        });

        save = (Button) findViewById(R.id.button5);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                requestAppPermissions();
                save();
            }
        });

        changeColor = (Button) findViewById(R.id.button6);

        changeColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorPicker(v);
            }
        });

        toggle = (Button) findViewById(R.id.button3);

        toggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeActivity(v);
            }
        });
    }

    private void colorPicker(View view) {
        ColorPickerDialog.newBuilder().setColor(Color.BLACK).show(this);
    }

    private void changeActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void requestAppPermissions() {
        if (hasReadPermissions() && hasWritePermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 112); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void save() {
        File filePath = Environment.getExternalStorageDirectory();
        File imageFile = new File(filePath, "image.png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            Object[] returnedObj = drawView.createCapture(DrawingCapture.BITMAP);
            Bitmap previewBitmap = (Bitmap) returnedObj[0];
            previewBitmap.compress(Bitmap.CompressFormat.PNG,100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "Save Successful", Toast.LENGTH_SHORT).show();
            return;
        } catch (IOException e1) {
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        drawView.setDrawColor(color);
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }
}

package com.example.timepicker;

import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TimePicker czas;
    TextView textView1;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);

        czas = findViewById(R.id.zegar1);
        czas.setIs24HourView(true);
    }
    public void Pobierz(View view) {
        int hour, minute;
        hour = czas.getHour();
        minute = czas.getMinute(); 
        textView1.setText("Wybrana godzina " + hour + ":"+ minute);
    }
    public void Zapisz(View view) {
        int hour, minute;
        hour = czas.getHour();
        minute = czas.getMinute();
        try {
            FileOutputStream file = openFileOutput("plik1.txt", MODE_PRIVATE);
            OutputStreamWriter stream = new OutputStreamWriter(file);
            stream.write("Zapisany czas: " + hour + ":" + minute);
            stream.close();
        }
        catch (IOException wyjatek) {
            wyjatek.printStackTrace();
        }
    }
    public void Odczytaj(View view) {
        try {
            FileInputStream file2 = openFileInput("plik1.txt");
            InputStreamReader stream = new InputStreamReader(file2);
            BufferedReader reader = new BufferedReader(stream);
            StringBuilder tekst = new StringBuilder();
            String linia;
            while ((linia = reader.readLine()) != null) {
                tekst.append(linia).append("\n");
            }
            reader.close();
            textView2.setText(tekst.toString());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
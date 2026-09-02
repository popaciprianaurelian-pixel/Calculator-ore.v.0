package com.example.calculatorore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText etStart, etEnd;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etStart = findViewById(R.id.etStart);
        etEnd = findViewById(R.id.etEnd);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateHours();
            }
        });
    }

    private void calculateHours() {
        String startStr = etStart.getText().toString().trim();
        String endStr = etEnd.getText().toString().trim();

        try {
            String[] startParts = startStr.split(":");
            String[] endParts = endStr.split(":");

            int startHour = Integer.parseInt(startParts[0]);
            int startMinute = Integer.parseInt(startParts[1]);

            int endHour = Integer.parseInt(endParts[0]);
            int endMinute = Integer.parseInt(endParts[1]);

            int startTotalMinutes = startHour * 60 + startMinute;
            int endTotalMinutes = endHour * 60 + endMinute;

            if (endTotalMinutes < startTotalMinutes) {
                endTotalMinutes += 24 * 60;
            }

            int diffMinutes = endTotalMinutes - startTotalMinutes;
            int hours = diffMinutes / 60;
            int minutes = diffMinutes % 60;

            tvResult.setText("Total ore: " + hours + "h " + minutes + "m");
        } catch (Exception e) {
            tvResult.setText("Introduceți ore valide (HH:MM)");
        }
    }
}


package com.example.calculatorore;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Construim interfata direct din Java (fara fisiere XML de layout)
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(60, 80, 60, 60);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setBackgroundColor(Color.WHITE);

        TextView tvTitle = new TextView(this);
        tvTitle.setText("Calculator Ore Munca");
        tvTitle.setTextSize(22);
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setPadding(0, 0, 0, 40);
        layout.addView(tvTitle);

        final EditText etStart = new EditText(this);
        etStart.setHint("Ora inceput (ex: 07:25)");
        etStart.setTextColor(Color.BLACK);
        etStart.setHintTextColor(Color.GRAY);
        layout.addView(etStart);

        final EditText etEnd = new EditText(this);
        etEnd.setHint("Ora sfarsit (ex: 19:30)");
        etEnd.setTextColor(Color.BLACK);
        etEnd.setHintTextColor(Color.GRAY);
        layout.addView(etEnd);

        Button btnCalc = new Button(this);
        btnCalc.setText("Calculeaza");
        layout.addView(btnCalc);

        final TextView tvResult = new TextView(this);
        tvResult.setTextSize(18);
        tvResult.setTextColor(Color.BLACK);
        tvResult.setGravity(Gravity.CENTER);
        tvResult.setPadding(0, 40, 0, 0);
        layout.addView(tvResult);

        setContentView(layout);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startStr = etStart.getText().toString().trim();
                String endStr = etEnd.getText().toString().trim();

                try {
                    String[] startParts = startStr.split(":");
                    String[] endParts = endStr.split(":");

                    int startHour = Integer.parseInt(startParts[0]);
                    int startMinute = Integer.parseInt(startParts[1]);

                    int endHour = Integer.parseInt(endParts[0]);
                    int endMinute = Integer.parseInt(endParts[1]);

                    int startTotal = startHour * 60 + startMinute;
                    int endTotal = endHour * 60 + endMinute;

                    if (endTotal < startTotal) {
                        endTotal += 24 * 60;
                    }

                    int diff = endTotal - startTotal;
                    int h = diff / 60;
                    int m = diff % 60;

                    tvResult.setText("Total lucrat: " + h + " ore si " + m + " minute");
                } catch (Exception e) {
                    tvResult.setText("Format invalid! Folositi formatul HH:MM (ex: 07:25)");
                }
            }
        });
    }
}

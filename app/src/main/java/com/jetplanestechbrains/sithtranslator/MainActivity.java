package com.jetplanestechbrains.sithtranslator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private TextView sithText;

    public MainActivity() {
        // Empty constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        final EditText englishText = findViewById(R.id.enterEnglishEditText);
        final Button translateButton = findViewById(R.id.translateButton);
        sithText = findViewById(R.id.sithTranslation);

        translateButton.setOnClickListener(view -> {
            // When the translate button is clicked, call API and update text with the translation
            SithTranslatorRequest request = new SithTranslatorRequest(englishText.getText().toString(), MainActivity.this);
            request.execute();
        });
    }

    /**
     * Update the Text View to display translated text
     *
     * @param output translated Sith Text
     */
    @Override
    public void onRequestComplete(String output) {
        sithText.setText(output);
    }
}

package com.example.notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.domain.NoteEntity;

public class NoteEditActivity extends AppCompatActivity {

    private NoteEntity noteEntity;

    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        initTextView();

        saveButton.setOnClickListener(v -> {
            NoteEntity noteEntity = new NoteEntity(
                    titleEditText.getText().toString(),
                    detailEditText.getText().toString());

        });
    }

    private void initTextView(){
        titleEditText = findViewById(R.id.title_edit_text);
        detailEditText = findViewById(R.id.detail_edit_text);
        saveButton = findViewById(R.id.save_button);
    }

}
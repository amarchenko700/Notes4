package com.example.notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.domain.NoteEntity;

public class NoteEditActivity extends AppCompatActivity {

    private NoteEntity noteEntity;
    private Integer id;

    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        initTextView();
        openNote();

        saveButton.setOnClickListener(v -> {
            NoteEntity noteEntity = new NoteEntity(
                    titleEditText.getText().toString(),
                    detailEditText.getText().toString());
            noteEntity.setId(id);
            Intent resultIntent = new Intent();
            resultIntent.putExtra(NotesListActivity.KEY_ITEM, noteEntity);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();

        });
    }

    private void initTextView(){
        titleEditText = findViewById(R.id.title_edit_text);
        detailEditText = findViewById(R.id.detail_edit_text);
        saveButton = findViewById(R.id.save_button);
    }

    private void openNote(){
        noteEntity = getIntent().getParcelableExtra(NotesListActivity.KEY_ITEM);
        titleEditText.setText(noteEntity.getTitle());
        detailEditText.setText(noteEntity.getDescription());
        id = noteEntity.getId();
    }

}
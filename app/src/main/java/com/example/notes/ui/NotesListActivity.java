package com.example.notes.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.notes.R;
import com.example.notes.domain.NoteEntity;
import com.example.notes.domain.NotesRepo;
import com.example.notes.impl.NotesRepoImpl;

public class NotesListActivity extends AppCompatActivity {

    public static final String KEY_ITEM = "KEY_ITEM";
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private NotesRepo notesRepo = new NotesRepoImpl();
    private NotesAdapter adapter = new NotesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        fillRepoByTestValuesRepo();
        initToolbar();
        initRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.notes_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_note_menu) {
            openNoteScreen(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openNoteScreen(@Nullable NoteEntity item){
        Intent intent = new Intent(this, NoteEditActivity.class);
        intent.putExtra(KEY_ITEM, item);
        startActivity(intent);
    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.setData(notesRepo.getNotes());
    }

    private void onItemClick(NoteEntity item){
        openNoteScreen(item);
    }

    private void fillRepoByTestValuesRepo(){
        notesRepo.createNote(new NoteEntity("Заметка 1", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 2", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 3", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 4", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 5", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 6", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 7", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 8", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 9", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 10", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 11", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 12", "Текст заметки"));
        notesRepo.createNote(new NoteEntity("Заметка 13", "Текст заметки"));
    }
}
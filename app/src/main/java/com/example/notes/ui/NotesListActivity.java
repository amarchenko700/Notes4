package com.example.notes.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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

    private ActivityResultLauncher<Intent> noteLauncher;
    private NoteEntity noteEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        fillRepoByTestValuesRepo();
        initToolbar();
        initRecyclerView();
        initNoteLauncher();

    }

    private void initNoteLauncher() {
        noteLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    noteEntity = result.getData().getParcelableExtra(KEY_ITEM);
                    notesRepo.editNote(noteEntity.getId(), noteEntity);
                    adapter.setData(notesRepo.getNotes());
                }
            }
        });
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
            NoteEntity newNote = notesRepo.createNote(new NoteEntity());
            openNoteScreen(notesRepo.getNote(newNote));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openNoteScreen(@Nullable NoteEntity item) {
        Intent intent = new Intent(this, NoteEditActivity.class);
        intent.putExtra(KEY_ITEM, item);
        noteLauncher.launch(intent);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.setData(notesRepo.getNotes());
    }

    private void onItemClick(NoteEntity item) {
        openNoteScreen(item);
    }

    private void fillRepoByTestValuesRepo() {
        notesRepo.createNote(new NoteEntity("???????? 1", "?????????? ???????????????????? ??????????????????"));
        notesRepo.createNote(new NoteEntity("???????? 2", "?????????????????? ???? GeekBrains"));
        notesRepo.createNote(new NoteEntity("???????? 3", "?? ?????????? ????????"));
        notesRepo.createNote(new NoteEntity("???????? 4", "???????????? ???????? ?????????????? ????????????????"));
        notesRepo.createNote(new NoteEntity("???????? 5", "???????????? ???? ?? ??????????, ?????? ??????-???? ????????????, ???????????? ?? ????????????"));
        notesRepo.createNote(new NoteEntity("???????? 6", "???????????? ?????????????? ????????????????"));
        notesRepo.createNote(new NoteEntity("???????? 7", "???? ?? ?????????? ?????????????? - ?????????? ??????????"));
        notesRepo.createNote(new NoteEntity("???????? 8", "?????????? ???? Java, ?????????? Kotlin - ?? ?????????? ???? ???????????? ?????????? "));
        notesRepo.createNote(new NoteEntity("???????? 9", "?????? ??????????????"));
        notesRepo.createNote(new NoteEntity("???????? 10", "?????? ????????????????????????"));
        notesRepo.createNote(new NoteEntity("???????? 11", "?????? ?????????? ???????? ??????????????"));
        notesRepo.createNote(new NoteEntity("???????? 12", "?????????? ??????"));
        notesRepo.createNote(new NoteEntity("???????? 13", "?????? ???? ??????"));
        notesRepo.createNote(new NoteEntity("???????? 14", "???????????? ??????"));
    }
}
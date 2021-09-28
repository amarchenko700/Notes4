package com.example.notes.domain;

import androidx.annotation.Nullable;

import java.util.List;

public interface NotesRepo {
    List<NoteEntity> getNotes();
    @Nullable
    Integer createNote(NoteEntity note);
    boolean removeNote(Integer id);
    boolean editNote(Integer id, NoteEntity note);
}

package com.example.notes.impl;

import androidx.annotation.Nullable;

import com.example.notes.domain.NoteEntity;
import com.example.notes.domain.NotesRepo;
import com.example.notes.ui.NotesListActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NotesRepoImpl implements NotesRepo {
    private final ArrayList<NoteEntity> cache = new ArrayList<>();
    private int counter = 0;

    @Override
    public List<NoteEntity> getNotes() {
        return new ArrayList<>(cache);
    }

    @Nullable
    @Override
    public Integer createNote(NoteEntity note) {
        int newId = ++counter;
        note.setId(newId);
        cache.add(note);
        return newId;
    }

    @Override
    public boolean removeNote(Integer id) {
        for (int i = 0; i < cache.size(); i++) {
            if(cache.get(i).getId() == id){
                cache.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editNote(Integer id, NoteEntity note) {
        removeNote(id);
        note.setId(id);
        cache.add(note);
        return true;
    }
}

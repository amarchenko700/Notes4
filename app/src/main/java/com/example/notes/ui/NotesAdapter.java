package com.example.notes.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.domain.NoteEntity;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<NoteEntity> data = new ArrayList<>();
    private onItemClickListener clickListener = null;

    public void setData(List<NoteEntity> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteEntity note = getItem(position);
        holder.itemView.setOnClickListener(v -> clickListener.onItemClick(note));
        holder.titleTextView.setText(note.getTitle());
        holder.detailTextView.setText(note.getDescription());
    }

    private NoteEntity getItem(int position){
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        clickListener = listener;
    }

    interface onItemClickListener{
        void onItemClick(NoteEntity item);
    }
}

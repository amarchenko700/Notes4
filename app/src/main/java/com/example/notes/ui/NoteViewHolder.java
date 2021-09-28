package com.example.notes.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;

import org.w3c.dom.Text;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public TextView titleTextView = itemView.findViewById(R.id.title_text_view);
    public TextView detailTextView = itemView.findViewById(R.id.detail_text_view);
}

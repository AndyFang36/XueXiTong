package edu.example.xuexitong.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;

import java.util.List;

import edu.example.xuexitong.R;
import edu.example.xuexitong.models.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private final List<Note> notes;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View noteView;

        TextView tvNoteId;
        TextView tvNoteTitle;
        TextView tvNoteContent;
        TextView tvNoteCreationTime;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.noteView = view;
            tvNoteId = view.findViewById(R.id.tvNoteId);
            tvNoteTitle = view.findViewById(R.id.tvNoteTitle);
            tvNoteContent = view.findViewById(R.id.tvNoteContent);
            tvNoteCreationTime = view.findViewById(R.id.tvNoteCreationTime);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.noteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Note note = notes.get(position);
                Toast.makeText(v.getContext(), "您选择的是 " + note.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tvNoteId.setText(String.valueOf(note.getNoteId()));
        holder.tvNoteTitle.setText(note.getTitle());
        holder.tvNoteContent.setText(note.getContent());
        holder.tvNoteCreationTime.setText(note.getCreationTime());
    }

    /**
     * 获取笔记数量
     */
    @Override
    public int getItemCount() {
        return notes.size();
    }
}

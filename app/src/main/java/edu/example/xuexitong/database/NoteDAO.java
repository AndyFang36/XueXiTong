package edu.example.xuexitong.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.example.xuexitong.models.Note;
import edu.example.xuexitong.utils.DbOpenHelper;

public class NoteDAO  {
    private final SQLiteDatabase sqliteDb;

    public NoteDAO(Context context) {
        sqliteDb = (new DbOpenHelper(context)).getWritableDatabase();
    }

    public void add(Note note) {
        String sql = "INSERT INTO note (user_id, title, content, creation_time) VALUES (?, ?, ?, ?)";
        sqliteDb.execSQL(sql, new Object[]{
            note.getUserId(),
            note.getTitle(),
            note.getContent(),
            note.getCreationTime()
        });
    }

    public void deleteById(int noteId) {
        String sql = "DELETE FROM note WHERE note_id = " + noteId;
        sqliteDb.execSQL(sql);
    }

    public void update(Note note) {
        String sql = "UPDATE note SET user_id = ?, title = ?, content = ?, creation_time = ? WHERE note_id = ?";
        sqliteDb.execSQL(sql, new Object[]{
            note.getUserId(),
            note.getTitle(),
            note.getContent(),
            note.getCreationTime(),
            note.getNoteId()
        });
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = sqliteDb.query("note", null, null, null, null, null, "creation_time DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int noteId = cursor.getInt(cursor.getColumnIndex("note_id"));
                int userId = cursor.getInt(cursor.getColumnIndex("user_id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String creationTime = cursor.getString(cursor.getColumnIndex("creation_time"));
                /* 添加到列表 */
                notes.add(new Note(noteId, userId, title, content, creationTime));
            }
            cursor.close();
        }
        return notes;
    }
}

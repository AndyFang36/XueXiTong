package edu.example.xuexitong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.example.xuexitong.database.NoteDAO;
import edu.example.xuexitong.models.Note;

/**
 * 添加笔记
 */
public class AddingNoteActivity extends AppCompatActivity {
    private final Intent intent = new Intent();
    private NoteDAO noteDAO;
    private SharedPreferences.Editor editor;
    private NoteDAO openHelper;
    private EditText etNoteTitle;
    private EditText etNoteContent;
    private Button btnAddNote, btnCancelAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_note);
        setTitle("添加笔记");

        noteDAO = new NoteDAO(this);

        SharedPreferences sp = getSharedPreferences("userinfo", MODE_PRIVATE);
        editor = sp.edit();

        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
        btnAddNote = findViewById(R.id.btnAddNote);
        btnCancelAdd = findViewById(R.id.btnCancelAdd);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = 0;
                if (sp.getBoolean("flag", false)) {
                    userId = sp.getInt("userId", 0);
                }
                String title = etNoteTitle.getText().toString().trim();
                String content = etNoteContent.getText().toString().trim();
                String creationTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
                if (title.equals("")) {
                    Toast.makeText(v.getContext(), "标题不能为空！", Toast.LENGTH_SHORT).show();
                    etNoteTitle.requestFocus();
                    return;
                }
                if (content.equals("")) {
                    Toast.makeText(v.getContext(), "笔记内容不能为空！", Toast.LENGTH_SHORT).show();
                    etNoteContent.requestFocus();
                    return;
                }
                noteDAO.add(new Note(userId, title, content, creationTime));
                Toast.makeText(v.getContext(), "成功添加笔记！", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(AddingNoteActivity.this, MainActivity.class);
                intent.putExtra("id", 2);
                startActivity(intent);
                finish();
            }
        });
    }
}
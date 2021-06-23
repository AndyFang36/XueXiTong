package edu.example.xuexitong;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.example.xuexitong.adapters.NoteAdapter;
import edu.example.xuexitong.database.NoteDAO;
import edu.example.xuexitong.models.Note;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {
    private NoteDAO noteDAO;
    private RecyclerView rvNotes;
    private FloatingActionButton floatingActionBtnAdd;
    private ArrayList<Note> mNotes = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //////////////////////////////////////////////////////  /////////////////////////////////////////////////////////
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        noteDAO = new NoteDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        setHasOptionsMenu(true);  // 必须设置true,菜单才会显示出来

        init();

        /* 设置布局 */
        rvNotes = view.findViewById(R.id.rvNotes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvNotes.setLayoutManager(layoutManager);
        NoteAdapter noteAdapter = new NoteAdapter(mNotes);
        rvNotes.setAdapter(noteAdapter);

        /* 跳转到添加笔记页面 */
        floatingActionBtnAdd = view.findViewById(R.id.floatingActionBtnAdd);
        floatingActionBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddingNoteActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    ///////////////////////////////////////////////////// 选项菜单 ///////////////////////////////////////////////////
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.note_edit_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getContext(), "成功 " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }

    /**
     * 初始化
     */
    public void init() {
        if (noteDAO.getAllNotes() != null) {
            mNotes = noteDAO.getAllNotes();
        }
    }
}
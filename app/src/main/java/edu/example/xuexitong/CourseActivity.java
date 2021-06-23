package edu.example.xuexitong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.example.xuexitong.adapters.CourseAdapter;
import edu.example.xuexitong.models.Course;

public class CourseActivity extends AppCompatActivity {

    private final List<Course> mCourses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setTitle("我的课程");

        init();

        RecyclerView recyclerView = findViewById(R.id.rvCourses);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        CourseAdapter courseAdapter = new CourseAdapter(mCourses);
        recyclerView.setAdapter(courseAdapter);
    }

    public void init() {
        Integer[] courseImages = {R.drawable.ic_android, R.drawable.ic_coding, R.drawable.ic_computer, R.drawable.ic_design, R.drawable.ic_java, R.drawable.ic_jquery, R.drawable.ic_math, R.drawable.ic_php, R.drawable.ic_test, R.drawable.ic_web};
        String[] courseNames = {"Android开发基础", "软件工程", "计算机基础", "UI设计", "Java程序设计", "jQuery实战", "离散数学", "PHP程序设计", "软件质量保证与测试", "WEB前端开发基础"};
        String[] courseTeachers = {"张老师", "孙老师", "孙老师", "方老师", "王老师", "周老师", "王老师", "孙老师", "孙老师", "方老师"};
        for (int i = 0; i < courseImages.length; i++) {
            Course course = new Course(courseImages[i], courseNames[i], courseTeachers[i]);
            mCourses.add(course);
        }
    }
}
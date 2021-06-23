package edu.example.xuexitong.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.example.xuexitong.R;
import edu.example.xuexitong.models.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> mCourses;

    public CourseAdapter(List<Course> courses) {
        this.mCourses = courses;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View courseView;

        ImageView courseImage;
        TextView courseName;
        TextView courseTeacher;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.courseView = view;
            courseImage = view.findViewById(R.id.courseImage);
            courseName = view.findViewById(R.id.courseName);
            courseTeacher = view.findViewById(R.id.courseTeacher);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        /* 点击事件 */
        viewHolder.courseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Course course = mCourses.get(position);
                Toast.makeText(v.getContext(), "您选择的是 " + course.getCourseName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.courseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Course course = mCourses.get(position);
                Toast.makeText(v.getContext(), "您选择的是 " + course.getCourseName(), Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        Course course = mCourses.get(position);
        holder.courseImage.setImageResource(course.getCourseImage());
        holder.courseName.setText(course.getCourseName());
        holder.courseTeacher.setText(course.getCourseTeacher());
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }
}

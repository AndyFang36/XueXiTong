package edu.example.xuexitong.models;

/**
 * 课程类
 */
public class Course {
    private Integer courseImage;
    private String courseName;
    private String courseTeacher;

    public Course(Integer courseImage, String courseName, String courseTeacher) {
        this.courseImage = courseImage;
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
    }

    public Integer getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(Integer courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
}

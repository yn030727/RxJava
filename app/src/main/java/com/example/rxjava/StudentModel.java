package com.example.rxjava;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    static List<Student> studentList;

    public static void init(){
        studentList = new ArrayList<>();
        for(int i=0 ; i<10 ; i++){
            Student s = new Student();
            List<Student.Course> courses = new ArrayList<>();
            for(int j=0 ; j<4 ; j++){
                Student.Course course = new Student.Course();
                course.setCourseName("Course " + j);
                courses.add(course);
            }
            s.setName("Student " + i);
            s.setCourseList(courses);
            studentList.add(s);
        }
    }

    public static List<Student> getStudentList() {
        return studentList;
    }
}

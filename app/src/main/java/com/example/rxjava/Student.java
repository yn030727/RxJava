package com.example.rxjava;

import java.util.List;

public class Student {
    String name;
    List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public String getName() {
        return name;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setName(String name) {
        this.name = name;
    }

    static class Course{
        String courseName;

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseName() {
            return courseName;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "courseName='" + courseName + '\'' +
                    '}';
        }
    }
}

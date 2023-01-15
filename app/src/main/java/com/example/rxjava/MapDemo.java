package com.example.rxjava;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MapDemo {
    public static void main(String[] args) {

        StudentModel.init();


        //1.常用的数据处理方式
/*        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Student> studentList = StudentModel.getStudentList();
                for(Student student : studentList){
                    List<Student.Course> courseList = student.getCourseList();
                    for(Student.Course course : courseList){
                        System.out.println(course);
                    }
                }
            }
        }).start();*/


        //2.通过RxJava中的map进行处理
        //fromIterable表示发送一个序列中的所有元素，也就是一个学生代表一个事件
        Observable.fromIterable(StudentModel.getStudentList())
                //Function里面的T是输入的类型，R是输出的类型
                //输入学生，返回学生的课程集合
                .map(new Function<Student, List<Student.Course>>() {
                    @Override
                    public List<Student.Course> apply(Student student) throws Exception {
                        return student.getCourseList();
                    }
                }).subscribe((Consumer<List<Student.Course>>) list -> {
                    for(Student.Course course : list){
                        System.out.println(course);
                    }
                });


        //3.通过flatMap来进行处理
        Observable.fromIterable(StudentModel.getStudentList())
                .flatMap(new Function<Student, ObservableSource<?>>() {
                    //返回的是一个被观察者
                    //使用被观察者重新发送事件
                    @Override
                    public ObservableSource<?> apply(Student student) throws Exception {
                        //课程被当作新的事件来一个个被发送
                        return Observable.fromIterable(student.getCourseList());
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println(o);
                    }
                });
    }
}

package com.example.rxjava.demo2;


/*
* 被观察者的顶层接口
* */
public interface ObservableSource<T> {
    void subscribe(Observer observer);
}

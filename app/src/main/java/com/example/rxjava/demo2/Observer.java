package com.example.rxjava.demo2;


//观察者
public interface Observer<T> {

    //和被观察者建立订阅回调的方法
    void onSubscribe();

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);
}

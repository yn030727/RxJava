package com.example.rxjava.demo2;



public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);
}

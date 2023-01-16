package com.example.rxjava.demo2;



//被观察者的核心抽象类
//也是我们这个框架的入口
public abstract class Observable<T> implements ObservableSource<T> {
    @Override
    public void subscribe(Observer observer) {

        //和谁建立订阅？
        //怎么建立订阅？
        //为了保证拓展性，交给具体的开发人员去实现，这里提供了一个抽象的方法
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer observer);

    public static <T>  Observable<T> create(ObservableOnSubscribe<T> source){
        return new ObservableCreate<T>(source);
    }
}

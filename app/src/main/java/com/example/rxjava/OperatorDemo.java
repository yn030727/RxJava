package com.example.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OperatorDemo {
    //创建操作符
    public static void main(String[] args) {
        System.out.println("======================");
        OperatorDemo  createOperatorDemo = new OperatorDemo ();
        createOperatorDemo.test1();
        System.out.println("======================");
    }
    private void test1(){
        //观察者和被观察者之间的订阅关系（被观察者被订阅）
        //被观察者.subscribe(观察者)
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

                //事件产生的地方
                //会被观察者的onNext和onComplete方法回调
                emitter.onNext("1");
                emitter.onNext("222");
                emitter.onNext("aaaa");
                emitter.onComplete();//表示事件结束
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe...");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext..." + o );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError...");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}

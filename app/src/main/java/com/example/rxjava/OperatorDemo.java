package com.example.rxjava;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class OperatorDemo {
    //创建操作符
    public static void main(String[] args) {
        System.out.println("======================");
        OperatorDemo  createOperatorDemo = new OperatorDemo ();
        //createOperatorDemo.test1();
        //createOperatorDemo.test2();
        //createOperatorDemo.test3();
        createOperatorDemo.test4();
        System.out.println("======================");
    }

    //定义变量-观察者
    Observer observer = new Observer<Object>() {
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
            System.out.println("onError..." + e.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete");
        }
    };
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
                //在onComplete前面调用，就不会回调onComplete
                emitter.onError(new Throwable("手动丢出异常"));
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
                System.out.println("onError..." + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        Disposable d = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("222");
                emitter.onNext("aaaa");
                //emitter.onComplete();
                emitter.onError(new Throwable("手动丢出异常"));
            }
        }).subscribe(new Consumer<Object>() {//可以多个重载（加载多个消费者）
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("accept.." + o);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("accept.." + throwable);
            }
        });
    }



    private void test2(){
        //介绍just操作符
        //它有10个重载方法，允许我们最多传入10个事件
        Observable.just("1","AAAA","2")
                .subscribe(observer);
    }


    private void test3(){
        //fromArray可以传入无限多个参数
        //很多操作符只是更方便的写法，都是重复的
        Observable.fromArray("1","AAAA","2","1","AAAA","2","1","AAAA","2","1","AAAA","2","1","AAAA","2").subscribe(observer);
    }

    private void test4(){
        //fromIterable可以操作事件集合
        ArrayList<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        Observable.fromIterable(list).subscribe(observer);

        //Future可以查看事件在运行过程当中的各个状态
        Observable.fromFuture(new Future<Object>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws ExecutionException, InterruptedException {
                return "aaa";
            }

            @Override
            public Object get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                return null;
            }
        }).subscribe(observer);
    }


}

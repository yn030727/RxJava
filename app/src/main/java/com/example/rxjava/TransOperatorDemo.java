package com.example.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TransOperatorDemo {
    public static void main(String[] args) {
        System.out.println("=========================");
        TransOperatorDemo demo = new TransOperatorDemo();
        demo.test3();
        System.out.println("=========================");
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
        //map操作符可以对我们发送过来的事件进行处理
        //并且产生新的事件

        //转化操作符针对的都是我们observable的实例(被观察者)
        Observable.just("aaa")
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(String s) throws Exception {
                        //对事件进行处理，并返回新的值
                        return "BBB";
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("accept..." + o.toString());
                    }
                });
        //map函数返回的还是我们的被观察者对象，一个新的事件，然后对它进行被订阅
    }


    //网络请求，先传入一个aaa参数，然后通过just接口()返回一个响应的数据
    //用这个数据再去调用一个接口(嵌套式请求)
    private void test2(){
        Observable.just("aaa")
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) throws Exception {
                        //这里的ObservableSource是被观察者对象的顶层父类
                        //这里我们返回的是一个全新的被观察者对象(在原来事件的基础上生成一个新的被观察者)
                        return Observable.just("BBB");
                        //这里进行第二个事件（用之前请求到的数据再次进行网络请求）
                    }
                }).subscribe(observer);
    }


    //conCatMap()和flatMap()基本上是一样的
    //只不过concatMap()转发出来的事件是有序的，而flatMap()是无序的
    //PS：不过flatMap也不一定是无序的，相反无序的概率很小
    private void test3(){
        Observable.just("111","222","333","444","555")
                .concatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) throws Exception {
                        return Observable.just(s + "ssss");
                    }
                }).subscribe(observer);
    }
}

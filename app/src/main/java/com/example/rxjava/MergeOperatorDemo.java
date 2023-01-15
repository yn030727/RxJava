package com.example.rxjava;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//组合操作符
public class MergeOperatorDemo {
    public static void main(String[] args) {
        System.out.println("=========================");
        MergeOperatorDemo demo = new MergeOperatorDemo();
        demo.test1();
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
        //concat可以将两个被观察者打包成一个，最后发出一个事件(组合在一起)
        //concat最多可以传送四个参数，按照之前的顺序发送数据
        Observable.concat(Observable.just("1111"),Observable.just("2222"))
                .subscribe(observer);

    }
}

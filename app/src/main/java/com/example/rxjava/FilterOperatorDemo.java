package com.example.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

//过滤操作符
public class FilterOperatorDemo {
    public static void main(String[] args) {
        System.out.println("======================");
        FilterOperatorDemo  Demo = new FilterOperatorDemo ();
        Demo.test1();
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
        //发送了一个范围的事件
        Observable.range(1,10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        //不满足这个条件就会被过滤
                        //返回true就不会被过滤
                        //返回false就会过滤
                        return integer<5;
                    }
                })
                .subscribe(observer);
    }
}

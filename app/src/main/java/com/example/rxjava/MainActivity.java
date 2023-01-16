package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.rxjava.demo2.ObserverActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, ObserverActivity.class);
        startActivity(intent);
        //test1();
    }
    private void test1(){
        //主动创建事件（被观察者）
        Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                        //创建事件发射事件
                        System.out.println("Subscribe..." + Thread.currentThread());
                        Thread.sleep(2000);//假设我耗时两秒(模拟网络请求)
                        //然后接收过来的数据，通过emitter发送出去
                        //观察者会接收到数据，并作出相应的反馈
                        emitter.onNext("aaaa");
                        emitter.onNext("bbbb");
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.newThread())//主要来决定我执行subscribe方法所处的线程，也就是产生事件发射事件所在的线程
                .observeOn(AndroidSchedulers.mainThread())//来决定下游事件被处理时所处的线程,切换回主线程


                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe..." + Thread.currentThread());
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext..." + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError..." + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete..." + Thread.currentThread());
                    }
                });
    }
}
package com.example.rxjava.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjava.R;

public class ObserverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);


        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(Emitter<Object> emitter) {
                Log.i("TAG","subscribe: ");
                emitter.onNext("aaaa");
                emitter.onNext("bbbb");
                emitter.onNext("cccc");
                emitter.onError(new Throwable());
                emitter.onComplete();
            }
        }).subscribe(new Observer() {
            @Override
            public void onSubscribe() {
                Log.i("TAG","onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.i("TAG","onNext：" + o);
            }

            @Override
            public void onComplete() {
                Log.i("TAG","onComplete: ");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.i("TAG","onError: ");
            }
        });
    }
}
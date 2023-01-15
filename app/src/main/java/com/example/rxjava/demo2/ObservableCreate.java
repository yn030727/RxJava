package com.example.rxjava.demo2;

public class ObservableCreate<T> extends Observable<T>{


    @Override
    protected void subscribeActual(Observer observer) {
        observer.onSubscribe();


    }

    static class Createmitter<T> implements Emitter<T> {
        Observer<T> observer;
        boolean done;

        public Createmitter(Observer<T> observer){
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            if(done) return;
            observer.onNext(t);
        }

        @Override
        public void onComplete() {
            if(done) return ;
            observer.onComplete();
            done = true;
        }

        @Override
        public void onError(Throwable throwable) {
            if(done) return;
            observer.onError(throwable);
            done = true;
        }
    }
}

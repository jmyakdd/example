package com.jmy.mybbsdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jmy.mybbsdemo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Main3Activity extends AppCompatActivity {

    Observable<String> observable;
    Observer<String> observer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        observer = new Observer<String>() {
            private Disposable disposable;
            private int i = 0;
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String value) {
                Log.e("test",value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("test","onError");
            }

            @Override
            public void onComplete() {
                Log.e("test","onComplete");
            }
        };
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("test1");
                e.onNext("test2");
                e.onComplete();
                e.onNext("test2");
                e.onNext("test2");
            }
        });
        observable.subscribe(observer);
        String[] data = new String[]{"Hello","world","!"};
        Observable.just("Hello","world","!")
                .subscribe(observer);
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("test",s);
            }
        };
        Observable.fromArray(data)
                .subscribe(consumer);

    }
}

package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Random;

public class SkipFirstThree {
    public static void skip() {
        Random random = new Random();

        Observable<Integer> numberStream = Observable.range(1, 10)
                .map(i -> random.nextInt(100));

        Disposable d = numberStream.skip(3)
                .subscribe(number -> System.out.println("Number: " + number));

        d.dispose();
    }
}


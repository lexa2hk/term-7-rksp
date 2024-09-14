package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Random;

public class CombineStreams {
    public static void combine() {
        Random random = new Random();

        Observable<String> letterStream = Observable.range(1, 1000)
                .map(i -> String.valueOf((char) (random.nextInt(26) + 'A'))); // Random letter A-Z

        Observable<String> digitStream = Observable.range(1, 1000)
                .map(i -> String.valueOf(random.nextInt(10))); // Random digit 0-9

        Disposable d = Observable.zip(letterStream, digitStream, (letter, digit) -> letter + digit)
                .subscribe(combination -> System.out.println("Combined: " + combination));

        d.dispose();
    }
}


package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Random;

public class RandomSquares {
    public static void squareStream() throws InterruptedException {
        Random random = new Random();

        Disposable d = Observable.range(1, 1000)
                .map(i -> random.nextInt(1001))
                .map(number -> number * number)
                .subscribe(square -> System.out.println("Square: " + square));

        Thread.sleep(5000);
        d.dispose();
    }
}


package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileGenerator {
    public static Observable<File> generateFiles() {
        Random random = new Random();
        return Observable.<File> create(emitter -> {
            while (!emitter.isDisposed()) {
                File file = File.generateRandomFile();
                int delay = random.nextInt(901) + 100;  // Delay between 100 and 1000 ms
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignore) {}
                System.out.println("Generated: " + file);
                emitter.onNext(file);
            }
        }).subscribeOn(Schedulers.io());
    }
}


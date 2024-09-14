package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.concurrent.LinkedBlockingQueue;

public class FileQueue {
    private static final int MAX_CAPACITY = 5;
    private final LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>(MAX_CAPACITY);
    private final BehaviorSubject<File> queueSubject = BehaviorSubject.create();

    public void addFile(File file) throws InterruptedException {
        queue.put(file);  // Block if the queue is full
        queueSubject.onNext(takeFile());

    }

    public Observable<File> getQueueObservable() {
        return queueSubject;

    }

    public File takeFile() throws InterruptedException {
        return queue.take(); // Block if queue is empty
    }
}


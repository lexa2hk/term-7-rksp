package org.example;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class FileProcessingSystem {
    public static void processQueue() throws InterruptedException {
        FileQueue fileQueue = new FileQueue();
        FileHandler xmlHandler = new FileHandler("XML");
        FileHandler jsonHandler = new FileHandler("JSON");
        FileHandler xlsHandler = new FileHandler("XLS");

        FileGenerator.generateFiles()
                .observeOn(Schedulers.io())
                .subscribe(file -> {
                    try {
                        fileQueue.addFile(file);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        // File handlers subscribe to the queue observable
        xmlHandler.process(fileQueue.getQueueObservable()).subscribe();
        jsonHandler.process(fileQueue.getQueueObservable()).subscribe();
        xlsHandler.process(fileQueue.getQueueObservable()).subscribe();

        Thread.sleep(10000);
    }
}


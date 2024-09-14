package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FileHandler {
    private final String fileType;

    public FileHandler(String fileType) {
        this.fileType = fileType;
    }

    public void processFile(File file) throws InterruptedException {
        if (file.getType().equalsIgnoreCase(fileType)) {
            int processingTime = file.getSize() * 7;  // Processing time is size * 7ms
            System.out.println("Processing " + file + " by " + fileType + " handler for " + processingTime + "ms");
            Thread.sleep(processingTime);
        }
    }

    public Observable<File> process(Observable<File> fileObservable) {
        return fileObservable.filter(file -> file.getType().equalsIgnoreCase(fileType))
                .doOnNext(file -> {
                    try {
                        processFile(file);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).subscribeOn(Schedulers.io());
    }
}

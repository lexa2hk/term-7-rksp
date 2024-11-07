package org.example.practice1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileProcessingSystem {
    public static void main(String[] args) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(5);

        FileGenerator generator = new FileGenerator(queue);
        Thread generatorThread = new Thread(generator);
        generatorThread.start();

        Thread xmlHandler = new Thread(new FileHandler(queue, "XML"));
        Thread jsonHandler = new Thread(new FileHandler(queue, "JSON"));
        Thread xlsHandler = new Thread(new FileHandler(queue, "XLS"));

        xmlHandler.start();
        jsonHandler.start();
        xlsHandler.start();
    }
}

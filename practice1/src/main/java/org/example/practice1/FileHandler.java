package org.example.practice1;

import java.util.concurrent.BlockingQueue;

class FileHandler implements Runnable {
    private final BlockingQueue<File> queue;
    private final String fileType;

    public FileHandler(BlockingQueue<File> queue, String fileType) {
        this.queue = queue;
        this.fileType = fileType;
    }

    @Override
    public void run() {
        try {
            while (true) {
                File file = queue.take();
                if (file.getType().equals(fileType)) {
                    int processingTime = file.getSize() * 7;
                    System.out.println("Обработка файла: " + file.getType() + " размер: " + file.getSize());
                    Thread.sleep(processingTime);
                    System.out.println("Файл обработан: " + file.getType() + " размер: " + file.getSize());
                } else {
                    queue.put(file);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

package org.example.practice1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

class FileGenerator implements Runnable {
    private final BlockingQueue<File> queue;
    private final String[] fileTypes = {"XML", "JSON", "XLS"};
    private final Random random = new Random();

    public FileGenerator(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String type = fileTypes[random.nextInt(fileTypes.length)];
                int size = random.nextInt(91) + 10;
                File file = new File(type, size);
                queue.put(file);
                System.out.println("Сгенерирован файл: " + type + " размер: " + size);
                Thread.sleep(random.nextInt(901) + 100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

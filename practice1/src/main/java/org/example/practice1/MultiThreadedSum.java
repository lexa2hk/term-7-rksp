package org.example.practice1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadedSum {
    private static final int NUM_THREADS = 4; // Можно изменить в зависимости от числа потоков

    public static long findSum(int[] array) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        int chunkSize = array.length / NUM_THREADS;
        Future<Long>[] futures = new Future[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            final int start = i * chunkSize;
            final int end = (i == NUM_THREADS - 1) ? array.length : (i + 1) * chunkSize;
            futures[i] = executor.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    sum += array[j];
                    Thread.sleep(1); // Задержка 1 мс
                }
                return sum;
            });
        }

        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        executor.shutdown();
        return totalSum;
    }
}
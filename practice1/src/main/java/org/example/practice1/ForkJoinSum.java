package org.example.practice1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSum extends RecursiveTask<Long> {
    private static final int THRESHOLD = 1000; // Порог, после которого происходит деление задачи
    private final int[] array;
    private final int start;
    private final int end;

    public ForkJoinSum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            try {
                for (int i = start; i < end; i++) {
                    sum += array[i];
                    Thread.sleep(1); // Задержка 1 мс
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            ForkJoinSum leftTask = new ForkJoinSum(array, start, mid);
            ForkJoinSum rightTask = new ForkJoinSum(array, mid, end);
            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }

    public static long findSum(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ForkJoinSum(array, 0, array.length));
    }
}
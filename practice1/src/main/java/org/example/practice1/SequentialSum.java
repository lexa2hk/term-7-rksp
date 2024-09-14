package org.example.practice1;

public class SequentialSum {
    public static long findSum(int[] array) throws InterruptedException {
        long sum = 0;
        for (int value : array) {
            sum += value;
            Thread.sleep(1); // Задержка 1 мс
        }
        return sum;
    }
}
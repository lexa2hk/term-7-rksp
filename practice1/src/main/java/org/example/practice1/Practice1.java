package org.example.practice1;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Practice1 { // Черномуров Семён Андреевич ИКБО-10-21

    public static void runCode() throws InterruptedException, ExecutionException {
        int[] array = new int[10000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        long startTime = System.currentTimeMillis();
        long sequentialSum = SequentialSum.findSum(array);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential sum: " + sequentialSum);
        System.out.println("Sequential time: " + (endTime - startTime) + " ms");
        System.out.print("\n");

        startTime = System.currentTimeMillis();
        long multiThreadedSum = MultiThreadedSum.findSum(array);
        endTime = System.currentTimeMillis();
        System.out.println("Multithreaded sum: " + multiThreadedSum);
        System.out.println("Multithreaded time: " + (endTime - startTime) + " ms");
        System.out.print("\n");


        startTime = System.currentTimeMillis();
        long forkJoinSum = ForkJoinSum.findSum(array);
        endTime = System.currentTimeMillis();
        System.out.println("ForkJoin sum: " + forkJoinSum);
        System.out.println("ForkJoin time: " + (endTime - startTime) + " ms");
    }
}

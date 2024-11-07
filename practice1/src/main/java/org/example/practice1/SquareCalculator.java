package org.example.practice1;

import java.util.Scanner;
import java.util.concurrent.*;

public class SquareCalculator {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число для возведения в квадрат:");

        while (true) {
            String input = scanner.nextLine();

            try {
                int number = Integer.parseInt(input);
                Future<Integer> future = executor.submit(() -> {
                    int delay = ThreadLocalRandom.current().nextInt(1, 6);
                    Thread.sleep(delay * 1000L);
                    return number * number;
                });

                try {
                    Integer result = future.get();
                    System.out.println("Результат: " + result);
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println("Ошибка при вычислении: " + e.getMessage());
                }

            } catch (NumberFormatException e) {
                System.err.println("Пожалуйста, введите корректное число.");
            }
        }
    }
}


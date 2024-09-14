package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SensorSystem {

    // Constants for thresholds
    private static final int TEMPERATURE_THRESHOLD = 25;
    private static final int CO2_THRESHOLD = 70;

    public static void startListening() {

        // Observable for temperature sensor
        Observable<Integer> temperatureSensor = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> new Random().nextInt(16) + 15) // Generates a random temperature between 15 and 30
                .subscribeOn(Schedulers.io());

        // Observable for CO2 sensor
        Observable<Integer> co2Sensor = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> new Random().nextInt(71) + 30) // Generates a random CO2 value between 30 and 100
                .subscribeOn(Schedulers.io());

        // Combine the two sensors and check for conditions
        Disposable disposable = Observable.combineLatest(
                        temperatureSensor,
                        co2Sensor,
                        SensorReadings::new)
                .subscribe(readings -> {
                    System.out.println("Temperature: " + readings.temperature() + "°C, CO2: " + readings.co2() + "ppm");

                    boolean tempExceeded = readings.temperature() > TEMPERATURE_THRESHOLD;
                    boolean co2Exceeded = readings.co2() > CO2_THRESHOLD;

                    if (tempExceeded && co2Exceeded) {
                        System.out.println("ALARM!!! Both temperature and CO2 levels exceeded safe limits.");
                    } else if (tempExceeded) {
                        System.out.println("Warning: Temperature exceeded safe limit.");
                    } else if (co2Exceeded) {
                        System.out.println("Warning: CO2 level exceeded safe limit.");
                    } else {
                        System.out.println("All readings are within safe limits.");
                    }
                });

        // Keep the program running
        try {
            Thread.sleep(20000); // Run for 20 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Dispose of the subscription after done
        disposable.dispose();
    }

    // Class to hold sensor readings
        record SensorReadings(int temperature, int co2) {
    }
}


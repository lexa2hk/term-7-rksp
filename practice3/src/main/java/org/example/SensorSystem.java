package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SensorSystem {

    private static final int TEMPERATURE_THRESHOLD = 25;
    private static final int CO2_THRESHOLD = 70;

    public static void startListening() {

        Observable<Integer> temperatureSensor = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> new Random().nextInt(16) + 15)
                .subscribeOn(Schedulers.io());

        Observable<Integer> co2Sensor = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> new Random().nextInt(71) + 30)
                .subscribeOn(Schedulers.io());

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
                        //Черномуров Семён Андреевич ИКБО-10-21
                        System.out.println("All readings are within safe limits.");
                    }
                });

        try {
            Thread.sleep(20000);
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


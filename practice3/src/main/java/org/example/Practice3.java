package org.example;

public class Practice3 {
    public static void runCode() throws InterruptedException {
        SensorSystem.startListening();

        RandomSquares.squareStream();
        CombineStreams.combine();
        SkipFirstThree.skip();

        UserFriendExample.runCode();

        FileProcessingSystem.processQueue();
    }
}
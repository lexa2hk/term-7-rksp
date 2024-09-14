package org.example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckSumCalculator {
    private static int calculateChecksum(Path filePath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(filePath);
        ByteBuffer buffer = ByteBuffer.wrap(fileBytes);
        int checksum = 0;

        while (buffer.hasRemaining()) {
            checksum += buffer.get() & 0xFF;
            checksum = (checksum & 0xFFFF) + (checksum >> 16);
        }

        return checksum & 0xFFFF;
    }

    public static void calculateCheckSum() throws IOException {
        Path filePath = Paths.get("prac2_2src.txt");
        int checksum = calculateChecksum(filePath);
        System.out.println("\nChecksum: " + checksum);
    }
}

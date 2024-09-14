package org.example;

import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class FileReaderNIO {
    public static void readFile() {
        Path filePath = Paths.get("prac2_1ReadNio.txt");
        System.out.println("\nFile Reader NIO: " + filePath.toAbsolutePath());
        try(Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

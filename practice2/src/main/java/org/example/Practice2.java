package org.example;

import java.io.IOException;

public class Practice2 {
    public static void runCode() throws IOException {
        FileReaderNIO.readFile();

        FileCopyStream.copyFile();
        FileCopyChannel.copyFile();
        FileCopyApache.copyFile();
        FileCopyNIO.copyFile();

        CheckSumCalculator.calculateCheckSum();

//        DirectoryWatcher.watchDirectory(); // Запустить отдельно, бесконечный поллинг
    }
}
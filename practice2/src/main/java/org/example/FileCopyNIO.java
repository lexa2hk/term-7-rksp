package org.example;

import java.io.IOException;
import java.nio.file.*;

public class FileCopyNIO {
    public static void copyFile() throws IOException {
        Path source = Paths.get("prac2_2src.txt");
        Path dest = Paths.get("prac2_2_4dest.txt");

        Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
    }
}


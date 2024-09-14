package org.example;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryWatcher {
    public static void watchDirectory() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path dir = Paths.get("dir_to_watch");
        dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                Path fileName = (Path) event.context();
//                if (fileName.getFileName().toString().endsWith("~")) {
//                    continue;
//                }
                List<String> previousFileContent;
                try {
                    previousFileContent = readFile(Path.of(dir.toString(), fileName.getFileName().toString()));
                } catch (Exception e ) {
                    previousFileContent = readFile(Path.of(dir.toString(), fileName.getFileName().toString()+"~"));
                }
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("File created: " + fileName);

                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    try {
                        System.out.println("File modified: " + fileName);
                        List<String> currentFileContent = readFile(Path.of(dir.toString(), fileName.getFileName().toString()));
                        compareFileContents(previousFileContent, currentFileContent);
                    } catch (Exception ignore) {
                        List<String> currentFileContent = readFile(Path.of(dir.toString(), fileName.getFileName().toString()+"~"));
                        compareFileContents(previousFileContent, currentFileContent);
                    }

                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    // Невозможно получить файл, т.к. он удален
                    // Невозможно вывести контрольную сумму, т.к. файл уже был удален
                }
            }
            key.reset();
        }
    }

    // Метод для чтения файла в виде списка строк
    private static List<String> readFile(Path filePath) throws IOException {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.collect(Collectors.toList());
        }
    }

    private static void compareFileContents(List<String> oldContent, List<String> newContent) {
        System.out.println("Changes detected:");
        newContent.forEach(System.out::println);
//        for (String line : newContent) {
//            if (!oldContent.contains(line)) {
//                System.out.println("Added: " + line);
//            }
//        }
//
//        for (String line : oldContent) {
//            if (!newContent.contains(line)) {
//                System.out.println("Removed: " + line);
//            }
//        }
    }
}


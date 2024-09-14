package org.example;

import java.util.Random;

public class File {
    private final String type;
    private final int size;

    public File(String type, int size) {
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "File{" +
                "type='" + type + '\'' +
                ", size=" + size +
                '}';
    }

    // Static method to generate random files
    public static File generateRandomFile() {
        String[] fileTypes = {"XML", "JSON", "XLS"};
        Random random = new Random();
        String type = fileTypes[random.nextInt(fileTypes.length)];
        int size = random.nextInt(91) + 10;  // Size between 10 and 100
        return new File(type, size);
    }
}


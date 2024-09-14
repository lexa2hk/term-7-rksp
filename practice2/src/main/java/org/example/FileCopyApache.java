package org.example;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class FileCopyApache {
    public static void copyFile() throws IOException {
        File source = new File("prac2_2src.txt");
        File dest = new File("prac2_2_3dest.txt");

        FileUtils.copyFile(source, dest);
    }
}


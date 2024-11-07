package org.example;

import java.io.*;

public class FileCopyStream {
    public static void copyFile() throws IOException {
        File source = new File("prac2_2src.txt");
        File dest = new File("prac2_2_1dest.txt");
        // Черномуров Семён Андреевич ИКБО-10-21
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }
}


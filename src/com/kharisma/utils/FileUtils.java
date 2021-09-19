package com.kharisma.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    public static List<String> scanFile(String fileName) {
        List<String> content = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                content.add(sc.nextLine());
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static List<String> getDirectoryFileNames(String dir) {
        List<String> results = new ArrayList<>();
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }
}

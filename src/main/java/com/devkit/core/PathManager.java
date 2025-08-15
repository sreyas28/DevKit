package com.devkit.core;

import java.io.File;
import java.util.Arrays;

public class PathManager {

    public void viewPaths() {
        String separator = File.pathSeparator; // ";" on Windows, ":" on Unix
        String rawPath = System.getenv("PATH");

        if (rawPath == null || rawPath.isEmpty()) {
            System.out.println("No PATH environment variable found.");
            return;
        }
        String[] paths = rawPath.split(separator);

        System.out.println("🔍 Detected PATH entries for OS: " + new InstallerContext().getOS());
        System.out.println("Total entries: " + paths.length);
        System.out.println("----------------------------------");

        Arrays.stream(paths)
                .map(String::trim)
                .filter(p -> !p.isEmpty())
                .distinct()
                .forEach(path -> System.out.println("• " + path));
    }
}


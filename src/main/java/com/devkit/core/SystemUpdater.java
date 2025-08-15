package com.devkit.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemUpdater {

    public void updateSystem() {
        OSDetector.OS os = new InstallerContext().getOS();
        PackageManagerDetector.PackageManagers pkg = new InstallerContext().getPackageManager();

        switch (os) {
            case Windows -> System.out.println("⚠️ Please update your system manually on Windows.");
            case Linux -> {
                switch (pkg) {
                    case apt -> runCommand("sudo apt update && sudo apt upgrade -y");
                    case pacman -> runCommand("sudo pacman -Syu");
                    case dnf -> runCommand("sudo dnf upgrade --refresh -y");
                    default -> System.out.println("❌ Unsupported package manager: " + pkg);
                }
            }
            case MacOS -> runCommand("brew update && brew upgrade");
            default -> System.out.println("❌ Unsupported OS: " + os);
        }
    }

    private void runCommand(String command) {
        System.out.println("🔧 Running: " + command);
        try {
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", command);
            builder.inheritIO(); // Merge stderr into stdout
            Process process = builder.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("✅ Process exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Error running command: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
    }
}

package com.devkit;

import com.devkit.core.InstallerContext;
import com.devkit.core.OSDetector;
import com.devkit.core.PackageManagerDetector;
import com.devkit.core.PathManager;

public class DevKitInstaller {

    private static final String VERSION = "DevKit Installer v1.0.0";

    public static void main(String[] args) {
        InstallerContext installerContext = new InstallerContext();
        PathManager pathManager = new PathManager();

        if (args.length == 0 || args[0].equals("--help") || args[0].equals("-h")) {
            printHelp();
            return;
        }

        switch (args[0]) {
            case "-v":
            case "--version":
                System.out.println(VERSION);
                break;

            case "--detect-os":
                System.out.println("Detected OS: " + installerContext.getOS());
                break;

            case "--detect-pkg":
                System.out.println("Detected Package Manager: " + installerContext.getPackageManager());
                break;

            case "--paths":
                pathManager.viewPaths();
                break;

            default:
                System.err.println("Unknown option: " + args[0]);
                printHelp();
                break;
        }
    }

    private static void printHelp() {
        System.out.println("""
            Usage: java -jar devkit-installer.jar [option]

            Options:
              -v, --version         Show version info
              --detect-os           Print detected operating system
              --detect-pkg          Print detected package manager
              --paths               Prints all the paths present in your Operating System
              -h, --help                Show this help message
            """);
    }

}

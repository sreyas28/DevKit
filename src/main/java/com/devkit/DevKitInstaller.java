package com.devkit;

import com.devkit.core.*;
import com.devkit.tools.*;

public class DevKitInstaller {

    private static final String VERSION = "DevKit Installer v1.0.0-SnapShot";

    public static void main(String[] args) {
        InstallerContext installerContext = new InstallerContext();
        PathManager pathManager = new PathManager();
        ToolConfigLoader toolConfigLoader = new ToolConfigLoader();

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

            case "--update":
                new SystemUpdater().updateSystem();
                break;

            case "--tools":
                toolConfigLoader.loadToolConfig();
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
              --update              Updates Your PC
              --tools               Tells what you have selected and Install it
              -h, --help            Show this help message
            """);
    }

}

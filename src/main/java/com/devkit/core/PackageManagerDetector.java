package com.devkit.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class PackageManagerDetector {

    public enum pakageManagers{
        winglet, choco, apt, dnf, pacman, brew, none
    }

    public pakageManagers DetectPackageManager(OSDetector.OS os){
        switch (os){
            case Windows:
                if (isCommandAvailable("winget")) return pakageManagers.winglet;
                if (isCommandAvailable("choco")) return pakageManagers.choco;
                break;

            case Linux:
                if (isCommandAvailable("apt")) return pakageManagers.apt;
                if (isCommandAvailable("dnf")) return pakageManagers.dnf;
                if (isCommandAvailable("pacman")) return pakageManagers.pacman;
                break;

            case MacOS:
                if (isCommandAvailable("brew")) return pakageManagers.brew;
                break;
        }

        return pakageManagers.none;
    }

    private boolean isCommandAvailable(String command) {
        try{
            Process process = new ProcessBuilder("which", command).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine() != null;
        }
        catch (IOException e){
            return false;
        }
    }

}
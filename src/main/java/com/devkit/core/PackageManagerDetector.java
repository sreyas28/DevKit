package com.devkit.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class PackageManagerDetector {

    public enum PackageManagers{
        winget, choco, apt, dnf, pacman, brew, none
    }

    public PackageManagers DetectPackageManager(OSDetector.OS os){
        switch (os){
            case Windows:
                if (isCommandAvailable("winget")) return PackageManagers.winget;
                if (isCommandAvailable("choco")) return PackageManagers.choco;
                break;

            case Linux:
                if (isCommandAvailable("apt")) return PackageManagers.apt;
                if (isCommandAvailable("dnf")) return PackageManagers.dnf;
                if (isCommandAvailable("pacman")) return PackageManagers.pacman;
                break;

            case MacOS:
                if (isCommandAvailable("brew")) return PackageManagers.brew;
                break;
        }

        return PackageManagers.none;
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
package com.devkit.core;

public class OSDetector {

    public enum OS{
        Windows, Linux, MacOS, UNKNOWN
    }

    public OS detectOs(){
        String osName = System.getProperty("os.name").toLowerCase();

        if(osName.contains("win")){
            return OS.Windows;
        }
        else if(osName.contains("linux")){
            return OS.Linux;
        }
        else if(osName.contains("mac")){
            return OS.MacOS;
        }
        else {
            return OS.UNKNOWN;
        }
    }
}

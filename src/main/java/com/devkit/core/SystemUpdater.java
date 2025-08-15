package com.devkit.core;

public class SystemUpdater {

    public void updateSystem(){
        OSDetector.OS os = new InstallerContext().getOS();

        switch (os){
            case WINDOWS:

                break;

            case LINUX:

                break;

            case MACOS:


                break;

            case UNKNOWN:


                break;
        }

    }

}

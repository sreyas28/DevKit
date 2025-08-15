package com.devkit.core;

public class InstallerContext {
    private final OSDetector.OS os;
    private final PackageManagerDetector.pakageManagers packageManager;

    public InstallerContext(){
        this.os = new OSDetector().detectOs();
        this.packageManager = new PackageManagerDetector().DetectPackageManager(os);
    }

    public OSDetector.OS getOS() {
        return os;
    }

    public PackageManagerDetector.pakageManagers getPackageManager() {
        return packageManager;
    }
}

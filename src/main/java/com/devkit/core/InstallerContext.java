package com.devkit.core;

public class InstallerContext {
    private final OSDetector.OS os;
    private final PackageManagerDetector.PackageManagers packageManager;

    public InstallerContext(){
        this.os = new OSDetector().detectOs();
        this.packageManager = new PackageManagerDetector().DetectPackageManager(os);
    }

    public OSDetector.OS getOS() {
        return os;
    }

    public PackageManagerDetector.PackageManagers getPackageManager() {
        return packageManager;
    }
}

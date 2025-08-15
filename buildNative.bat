@echo off
setlocal

mvn clean package

if exist DevKitInstaller (
    rmdir /s /q DevKitInstaller
)

jpackage ^
    --name DevKitInstaller ^
    --input target ^
    --main-jar DevKit_new-1.0-SNAPSHOT-jar-with-dependencies.jar ^
    --main-class com.devkit.DevKitInstaller ^
    --type app-image ^
    --app-version 1.0.0

endlocal

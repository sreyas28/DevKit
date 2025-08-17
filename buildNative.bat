@echo off
SETLOCAL

REM Clean and build the Maven project
echo Building project with Maven...
mvn clean package

REM Remove existing DevKitInstaller folder if it exists
IF EXIST DevKitInstaller (
    echo Removing old DevKitInstaller directory...
    rmdir /s /q DevKitInstaller
)

REM Package the application using jpackage
echo Packaging with jpackage...
jpackage --name DevKitInstaller ^
         --input target ^
         --main-jar DevKit_new-1.0-SNAPSHOT-jar-with-dependencies.jar ^
         --main-class com.devkit.DevKitInstaller ^
         --type app-image ^
         --app-version 1.0.0
         REM --icon icon.ico
         REM --runtime-image custom-jre
         REM --license-file LICENSE.txt
         REM --dest output

REM Copy config folder into the output
echo Copying config folder...
xcopy /E /I /Y installer-resources\config DevKitInstaller\bin\config

echo Done!
ENDLOCAL
pause

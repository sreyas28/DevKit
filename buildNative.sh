#!/bin/bash

# Clean and build the Maven project
mvn clean package

# Check if DevKitInstaller directory exists and remove it
if [ -d "./DevKitInstaller" ]; then
    sudo rm -r ./DevKitInstaller
fi

# Package the application using jpackage
jpackage --name DevKitInstaller \
         --input target \
         --main-jar DevKit_new-1.0-SNAPSHOT-jar-with-dependencies.jar \
         --main-class com.devkit.DevKitInstaller \
         --type app-image \
         --app-version 1.0.0
        #  --main-jar DevKit_new-1.0-SNAPSHOT.jar \
        #  --icon icon.png
        #  --runtime-image custom-jre 
        #  --license-file LICENSE.txt
        #  --dest output

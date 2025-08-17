package com.devkit.tools;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ToolConfigLoader {

    public void loadToolConfig() {
        Yaml yaml = new Yaml();

        // Get current working directory (where the app is launched)
        Path configPath = Paths.get(System.getProperty("user.dir"), "config", "tools.yaml");

        try (InputStream in = Files.newInputStream(configPath)) {
            Map<String, Boolean> tools = yaml.load(in);
            printTools(tools);

            new ToolsInstaller(tools);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load tools.yaml from " + configPath, e);
        }
    }

    private void printTools(Map<String, Boolean> tools){
        System.out.println("Following Tools Will be Installed :");
        for(String key: tools.keySet()){
            if(tools.get(key)) System.out.println("• " + key);
        }
        System.out.println("________________________________________________\n");
    }
}

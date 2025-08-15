package com.devkit.tools;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ToolConfigLoader {

    public void loadToolConfig() {
        Yaml yaml = new Yaml();
        try (InputStream in = ToolConfigLoader.class.getClassLoader().getResourceAsStream("tools.yaml")) {
            Map<String, Boolean> tools = yaml.load(in);
            printTools(tools);

            new ToolsInstaller(tools);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load tools.yaml", e);
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

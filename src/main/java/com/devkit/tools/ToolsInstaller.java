package com.devkit.tools;

import com.devkit.core.InstallerContext;
import com.devkit.core.PackageManagerDetector;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class ToolsInstaller {

    final PackageManagerDetector.PackageManagers packageManager = new InstallerContext().getPackageManager();
    final Map<String, Boolean> tools ;

    public ToolsInstaller(Map<String, Boolean> tools){
        this.tools = tools;
        installerRunner();
    }

    private void installerRunner(){
        Yaml yaml = new Yaml();

        try(InputStream in = ToolConfigLoader.class.getClassLoader().getResourceAsStream("ToolRegistry.yaml")){
            if (in == null) {
                throw new RuntimeException("ToolRegistry.yaml not found in classpath");
            }
            Map<String, Map<String, String>> registry = yaml.load(in);

            for(String key: tools.keySet()){
                if(tools.get(key)){
                    installer(registry.get(key).get(packageManager.toString()));
                }
            }

        }
        catch (Exception e){
            throw new RuntimeException("Failed to load ToolRegistry.yaml", e);
        }
    }

    private void installer(String command) {
        System.out.println("🔧 Running: " + command);
        ProcessBuilder processBuilder;

        if(packageManager == PackageManagerDetector.PackageManagers.winget){
            processBuilder = new ProcessBuilder("cmd", "/c", command);
        }
        else {
            processBuilder = new ProcessBuilder("bash", "-c", command);
        }

        processBuilder.inheritIO();  // Merge stderr into stdout

        try {
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("  ↪ " + line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("✅ Success: Command executed cleanly.\n");
            } else {
                System.err.println("❌ Error: Command failed with exit code " + exitCode + "\n");
            }

        } catch (IOException e) {
            System.err.println("🚫 IO Error while executing command: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("⚠️ Interrupted while waiting for process: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("🔥 Unexpected error: " + e.getMessage());
        }
    }


}

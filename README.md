# 🛠️ DevKit Installer

**DevKit Installer** is a cross-platform, registry-driven CLI tool that installs essential development tools and languages using your system's native package manager. Built with modular Java architecture, YAML-driven configuration, and shell abstraction — it's designed to be portable, predictable, and user-friendly.

---

## 🚀 Features

- ✅ Cross-platform support: Linux, macOS, Windows (`winget`)
- 🧠 Registry-driven installs via `ToolRegistry.yaml`
- 📦 Dynamic config loading from external `tools.yaml`
- 🧰 Shell abstraction (`bash` or `cmd`) based on package manager
- 🔍 Clear feedback with success/failure indicators
- 🧪 Minimal dependencies: Java + SnakeYAML

---

## 📁 Project Structure

```
src/
├── com.devkit.core/
│   ├── InstallerContext.java
│   ├── PackageManagerDetector.java
│   └── OSDetector.java
├── com.devkit.tools/
│   ├── ToolsInstaller.java
│   └── ToolConfigLoader.java
resources/
├── ToolRegistry.yaml
└── tools.yaml
```

---

## 🧑‍💻 Usage

### 1. Create `tools.yaml`

```yaml
git: true
docker: true
kubectl: false
```

### 2. Run Installer

```bash
java -jar DevKitInstaller.jar tools.yaml
```

Or if packaged with `jpackage`:

```bash
./DevKitInstaller tools.yaml
```

---

## 🧩 Supported Package Managers

- `apt` (Debian/Ubuntu)
- `dnf` (Fedora/RHEL)
- `pacman` (Arch)
- `brew` (macOS)
- `winget` (Windows)

---

## 🛠️ Build & Package

### Build with Maven

```bash
mvn clean package
```

### Package with jpackage

```bash
jpackage --input target --name DevKitInstaller \
  --main-jar DevKitInstaller.jar --type exe \
  --java-options "-Xmx512m"
```

---

## 📜 License

MIT — feel free to fork, extend, and contribute.

---

## 🙌 Contributing

Pull requests welcome! If you’d like to add support for more tools, package managers, or GUI config editors — let’s build it together.

---

## ✨ Author

Crafted with care by **Sreyas Sharma** — passionate about modular architecture, user empowerment, and clean abstraction.

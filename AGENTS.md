# Coding Agent Instructions

## Java and Gradle

- Use JDK 17 for all Gradle commands in this repository. The Gradle wrapper is pinned to Gradle
  7.2, which cannot run on newer JDKs such as JDK 25.
- Before running Gradle, verify the active runtime with `java -version`. If necessary in the Codex
  environment, select its installed JDK 17 with:

  ```bash
  export JAVA_HOME=/root/.local/share/mise/installs/java/17.0.2
  export PATH="$JAVA_HOME/bin:$PATH"
  ```

- Run the normal build from the repository root with `./gradlew build`. The root project itself has
  only group and version metadata, but `settings.gradle.kts` includes the `structure` subproject.
  Gradle resolves the unqualified `build` task to `:structure:build`.
- Do not conclude that a root `./gradlew build` skips the implementation. The Java plugin,
  dependencies, Spotless configuration, and JUnit Platform setup intentionally live in
  `structure/build.gradle.kts`, next to the sources under `structure/src`.
- If Gradle reports that the Spotless plugin or another dependency cannot be resolved, first check
  access to the Gradle Plugin Portal and Maven Central. A proxy or restricted network can cause
  this failure before compilation; it does not mean the subproject is wired incorrectly.
- When diagnosing build behavior, prefer `./gradlew clean build --console=plain` and report whether
  failure occurred during plugin/dependency resolution, compilation, testing, or formatting.

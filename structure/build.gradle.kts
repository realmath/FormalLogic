plugins {
  java
  id("com.diffplug.spotless") version "6.25.0"
}

repositories {
  mavenCentral()
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(25)
  }
}

dependencies {
  testImplementation("com.google.guava:guava-testlib:33.5.0-jre")
  testImplementation("com.google.truth:truth:1.1.4")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
  testImplementation("org.mockito:mockito-core:4.7.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.9.0")
}

spotless {
  java {
    googleJavaFormat("1.30.0")
  }
}

tasks {
  test {
    useJUnitPlatform()
  }
}

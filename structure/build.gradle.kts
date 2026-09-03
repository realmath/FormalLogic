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
  testImplementation(platform("org.junit:junit-bom:6.0.3"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testImplementation("org.mockito:mockito-core:4.7.0")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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

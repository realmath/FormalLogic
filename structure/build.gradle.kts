import net.ltgt.gradle.errorprone.errorprone

plugins {
  java
  id("com.diffplug.spotless") version "6.10.0"
  id("net.ltgt.errorprone") version "2.0.2"
}

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  annotationProcessor("org.projectlombok:lombok:1.18.24")
  compileOnly("org.projectlombok:lombok:1.18.24")
  errorprone("com.google.errorprone:error_prone_core:2.15.0")
  implementation("com.google.code.findbugs:jsr305:3.0.2")
  testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
  testImplementation("com.google.guava:guava-testlib:31.1-jre")
  testImplementation("com.google.truth:truth:1.1.3")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
  testImplementation("org.mockito:mockito-core:4.7.0")
  testCompileOnly("org.projectlombok:lombok:1.18.24")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

spotless {
  java {
    googleJavaFormat()
  }
}

tasks.withType<JavaCompile>().configureEach {
  options.errorprone {
    disable(
      "CanIgnoreReturnValueSuggester"
    )
  }
}

tasks {
  test {
    useJUnitPlatform()
  }
}
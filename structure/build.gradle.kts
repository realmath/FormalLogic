plugins {
  java
  id("com.diffplug.spotless") version "6.10.0"
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
  testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
  testImplementation("com.google.guava:guava-testlib:32.0.0-jre")
  testImplementation("com.google.truth:truth:1.1.4")
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

tasks {
  test {
    useJUnitPlatform()
  }
}
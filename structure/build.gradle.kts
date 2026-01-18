plugins {
  java
  id("com.diffplug.spotless") version "6.10.0"
}

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  annotationProcessor("org.projectlombok:lombok:1.18.42")
  compileOnly("org.projectlombok:lombok:1.18.42")
  testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
  testImplementation("com.google.guava:guava-testlib:33.5.0-jre")
  testImplementation("com.google.truth:truth:1.1.4")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
  testImplementation("org.mockito:mockito-core:4.7.0")
  testCompileOnly("org.projectlombok:lombok:1.18.42")
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
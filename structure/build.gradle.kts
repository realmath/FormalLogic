plugins {
  java
  id("com.github.sherter.google-java-format") version "0.9"
  id("net.ltgt.errorprone") version "1.1.1"
}

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  annotationProcessor("org.projectlombok:lombok:1.18.12")
  compileOnly("org.projectlombok:lombok:1.18.12")
  errorprone("com.google.errorprone:error_prone_core:2.3.4")
  implementation("com.google.code.findbugs:jsr305:3.0.2")
  testAnnotationProcessor("org.projectlombok:lombok:1.18.12")
  testImplementation("com.google.guava:guava-testlib:31.0-jre")
  testImplementation("com.google.truth:truth:1.0.1")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
  testImplementation("org.mockito:mockito-core:3.3.3")
  testCompileOnly("org.projectlombok:lombok:1.18.12")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

tasks {
  test {
    useJUnitPlatform()
  }
}
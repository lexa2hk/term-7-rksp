plugins {
    application
    java
    id("org.springframework.boot") version "3.2.5"
}

apply(plugin = "io.spring.dependency-management")
apply(plugin = "java")

val mainClassName = "org.example.Main"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.minio:minio:8.5.12")

    implementation("org.projectlombok:lombok:1.18.28")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.example.Main"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }
}
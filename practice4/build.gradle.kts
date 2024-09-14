plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.rsocket:rsocket-core:1.1.4")
    implementation("io.rsocket:rsocket-transport-netty:1.1.4")
    implementation("org.springframework.boot:spring-boot-starter:3.3.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.3")
    implementation("com.h2database:h2:2.3.232")

    implementation("org.projectlombok:lombok:1.18.28")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    implementation("org.springframework.boot:spring-boot-starter-test:3.3.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.3")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
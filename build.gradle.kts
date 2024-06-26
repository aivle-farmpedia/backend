plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.farm"
version = "0.0.1-SNAPSHOT"

tasks.getByName<Jar>("jar") {
    enabled = false
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val springBootVersion = "3.2.5"
val myBatisVersion = "3.0.3"
val junitPlatformVersion = "1.8.2"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:$springBootVersion")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:$myBatisVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    // webflux
    implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:$myBatisVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")

    // thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:$springBootVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
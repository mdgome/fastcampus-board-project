# FastCampus Project For Board

# Skill
  - JAVA
  - Spring Boot
    - Security
      - Spring Security
    - Validation
    - Data
      - Spring DATA JPA
      - H2 Database
      - MySQL Driver
    - TDD
      - JUnit 5
    - Productivity
      - Lombok
      - Spring Boot Devtools
      - Spring Boot Actuator
    - JSON API
      - Rest REpositories
      - Rest Repositories HAL Explorer
    - Build Gradle
      ```
      plugins {
        id 'org.springframework.boot' version '2.7.1'
        id 'io.spring.dependency-management' version '1.0.11.RELEASE'
        id 'java'
      }
      group = 'com.example'
      version = '0.0.1-SNAPSHOT'
      sourceCompatibility = '17'
      configurations {
        compileOnly {
          extendsFrom annotationProcessor
        }
      }
      repositories {
      mavenCentral()
      }
      dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-actuator'
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        implementation 'org.springframework.boot:spring-boot-starter-data-rest'
        implementation 'org.springframework.boot:spring-boot-starter-security'
        implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
        implementation 'org.springframework.boot:spring-boot-starter-validation'
        implementation 'org.springframework.boot:spring-boot-starter-web'
        implementation 'org.springframework.data:spring-data-rest-hal-explorer'
        implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
        compileOnly 'org.projectlombok:lombok'
        developmentOnly 'org.springframework.boot:spring-boot-devtools'
        runtimeOnly 'com.h2database:h2'
        runtimeOnly 'mysql:mysql-connector-java'
        annotationProcessor 'org.projectlombok:lombok'
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
        testImplementation 'org.springframework.security:spring-security-test'
      }
      tasks.named('test') {
      useJUnitPlatform()
      }
      ```
    
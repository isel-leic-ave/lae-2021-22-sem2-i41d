/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.3/userguide/building_java_projects.html
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")
    id("me.champeau.jmh") version "0.6.6"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

// Test Logging
tasks.withType<Test> {
    testLogging {
        events("started", "passed", "skipped", "failed")
    }
}

repositories {
    // Use JCenter for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("com.squareup:javapoet:1.13.0")

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // For using the reflection features
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application.
    mainClass.set("pt.isel.AppKt")
}
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.11.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.0")
}
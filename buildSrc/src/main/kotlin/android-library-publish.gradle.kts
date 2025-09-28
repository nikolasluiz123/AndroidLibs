import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

plugins {
    id("com.android.library")
    id("maven-publish")
}

extensions.configure<com.android.build.gradle.LibraryExtension> {
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

val libraryGroupId = "br.com.androidlibs"
val libraryVersion = project.property("library.version") as String

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = libraryGroupId
            artifactId = project.name
            version = libraryVersion

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/nikolasluiz123/AndroidLibs")
            credentials {
                username = System.getenv("GPR_USER")
                password = System.getenv("GPR_TOKEN")
            }
        }
    }
}
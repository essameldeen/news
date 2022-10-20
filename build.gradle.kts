// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        

    }

    dependencies {
        classpath(Dependencies.buildGradle)
        classpath(Dependencies.kotlin)


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


plugins {
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}
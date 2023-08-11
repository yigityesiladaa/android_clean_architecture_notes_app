plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies{
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

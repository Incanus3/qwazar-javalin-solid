import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "cz.incanus"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.7.20"
    id("com.google.devtools.ksp") version "1.7.20-1.0.6"
}

repositories {
    mavenCentral()
}

dependencies {
    val koinVersion = "3.2.2"
    val koinAnnotationsVersion = "1.0.3"
    val jacksonVersion = "2.13.4"

    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-annotations:$koinAnnotationsVersion")
    testImplementation("io.insert-koin:koin-test:$koinVersion")
    ksp("io.insert-koin:koin-ksp-compiler:$koinAnnotationsVersion")

    implementation(kotlin("stdlib-jdk8"))

    implementation("io.javalin:javalin:4.6.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("org.slf4j:slf4j-simple:2.0.1")

    testImplementation(kotlin("test"))
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

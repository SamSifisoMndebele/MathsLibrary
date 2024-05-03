plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
}

group = "com.ssmnd"
version = "1.0.0-beta"

repositories {
    mavenCentral()
}

dependencies {
    implementation(group="org.tensorflow", name="tensorflow-core-platform", version="0.3.3")
    //implementation("com.google.code.gson:gson:2.10.1")
    //testImplementation("org.jetbrains.kotlin:kotlin-test")
    //testImplementation("junit:junit:3.8.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    exclude("kotlin/**/*", "org/**/*", "META-INF/**/*")
}
plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.21"
    application
}

group = "srb.akikrasic"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.formdev:flatlaf:1.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.2.0")
    implementation("org.yaml:snakeyaml:2.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")

}
tasks.withType<Test> { useJUnitPlatform() }

kotlin {
    jvmToolchain(25)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25) // Or your desired JVM version
    }
}
java {
    targetCompatibility = JavaVersion.VERSION_25 // Or your desired JVM version
    sourceCompatibility = JavaVersion.VERSION_25 // Or your desired JVM version
}
application {
    mainClass.set("srb.akikrasic.apxu.main.MainKt")
}
tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(
            listOf(
                "compileJava",
                "compileKotlin",
                "processResources"
            )
        ) // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
        from("src/main/kotlin/srb/akikrasic/apxu/resource/language/serbian.yaml") { // Explicitly include resources
            rename("serbian.yaml", "languages/serbian.yaml") // Include all files and subdirectories
        }
        from("src/main/kotlin/srb/akikrasic/apxu/resource/language/english.yaml") { // Explicitly include resources
            rename("english.yaml", "languages/english.yaml") // Include all files and subdirectories
        }
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}



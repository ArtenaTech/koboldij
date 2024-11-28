plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.artena.llm.ide.koboldij"
version = "0.0.9-RC"

repositories {
    mavenCentral()
    maven { url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies") }
}

intellij {
    version.set("2023.2.8")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("com.intellij.java"))
}


dependencies {
    implementation ("org.apache.commons:commons-text:1.11.0")
    implementation("org.apache.commons:commons-lang3:3.17.0")

    implementation ("dev.langchain4j:langchain4j:0.27.0")
    implementation ("org.springframework:spring-context:4.1.5.RELEASE")
    implementation ("org.springframework:spring-framework-bom:4.1.5.RELEASE")
    implementation ("org.springframework:spring-web:4.1.5.RELEASE")
    implementation ("org.slf4j:slf4j-api:2.0.12");

    implementation("com.vladsch.flexmark:flexmark-all:0.64.8")

    implementation ("com.fasterxml.jackson.core:jackson-core:2.15.3")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.15.3")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.15.3")

    implementation("org.apache.commons:commons-compress:1.25.0")

    implementation("org.jetbrains:marketplace-zip-signer:0.1.8")

    testImplementation ("com.intellij.remoterobot:remote-robot:0.11.23");
    testImplementation ("com.intellij.remoterobot:remote-fixtures:0.11.23");
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {

    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("243.*")
    }
}

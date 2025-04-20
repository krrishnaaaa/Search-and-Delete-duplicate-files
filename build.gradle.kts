plugins {
  kotlin("jvm") version "1.9.10"
  application
  id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.pcsalt"
version = "1.0.1-SNAPSHOT"

repositories {
  mavenCentral()
}

sourceSets {
  main {
    kotlin.srcDirs("src/main/kotlin")
    resources.srcDirs("src/main/resources")
  }
}
dependencies {
  implementation(kotlin("stdlib"))
}

application {
  mainClass.set("com.pcsalt.utility.MainKt") // Main class path
}

tasks.jar {
  manifest {
    attributes["Main-Class"] = "com.pcsalt.utility.MainKt"
  }
  from(sourceSets.main.get().output)
}

tasks {
  shadowJar {
    archiveBaseName.set("search-and-delete-duplicates")
    archiveClassifier.set("")
    archiveVersion.set("1.0-SNAPSHOT")
  }
}

tasks.named("startShadowScripts") {
  dependsOn(tasks.named("jar"))
}

tasks.named("startScripts") {
  dependsOn(tasks.named("shadowJar"))
}

tasks.named("distTar") {
  dependsOn(tasks.named("shadowJar"))
}

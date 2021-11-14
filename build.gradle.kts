buildscript {
    // __LATEST_COMPOSE_RELEASE_VERSION__
    val composeVersion = System.getenv("COMPOSE_TEMPLATE_COMPOSE_VERSION") ?: "1.0.0-beta5"

    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:$composeVersion")
        classpath("com.android.tools.build:gradle:4.2.2")
        // __KOTLIN_COMPOSE_VERSION__
        classpath(kotlin("gradle-plugin", version = "1.5.31"))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        // Apollo (snapshots, releases are on mavenCentral)
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
        maven {
            url = uri("https://packages.jetbrains.team/maven/p/skija/maven")
        }
    }
}

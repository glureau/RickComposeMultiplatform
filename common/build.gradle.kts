import org.jetbrains.compose.compose


plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.apollographql.apollo3") version "3.0.0-beta03"
}

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                // Needed only for preview.
                implementation(compose.preview)
                implementation("com.apollographql.apollo3:apollo-runtime:3.0.0-beta03")
                //implementation("com.apollographql.apollo3:apollo-runtime-kotlin:3.0.0-SNAPSHOT")

                implementation("io.ktor:ktor-client-core:1.6.5")
                // For some reasons, CIO 1.6.5 fail to download full image (EOFException) on basic/default configuration.
                implementation("io.ktor:ktor-client-java:1.6.5")
                //implementation("io.ktor:ktor-client-cio:1.6.5")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")
                implementation("io.github.kuuuurt:multiplatform-paging:0.4.5")
                //implementation("androidx.paging:paging-common:3.1.0")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.4.0")
                api("androidx.core:core-ktx:1.7.0")
                implementation("io.coil-kt:coil-compose:1.4.0")
                implementation("androidx.navigation:navigation-compose:2.4.0-beta02")
                implementation("androidx.paging:paging-compose:1.0.0-alpha14")
            }
        }

        val desktopMain by getting {
            val osName = System.getProperty("os.name")
            val targetOs = when {
                osName == "Mac OS X" -> "macos"
                osName.startsWith("Win") -> "windows"
                osName.startsWith("Linux") -> "linux"
                else -> error("Unsupported OS: $osName")
            }

            val osArch = System.getProperty("os.arch")
            val targetArch = when (osArch) {
                "x86_64", "amd64" -> "x64"
                "aarch64" -> "arm64"
                else -> error("Unsupported arch: $osArch")
            }

            val version = "0.5.3"
            val target = "${targetOs}-${targetArch}"
            dependencies {
                implementation("org.jetbrains.skiko:skiko-jvm-runtime-$target:$version")
            }
        }
    }
}

apollo {
    //schemaFile.set(File("$rootDir/common/src/commonMain/graphql/glureau/geno/schema.graphqls"))
    packageName.set("rickcompose")
}

android {
    compileSdkVersion(31)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}
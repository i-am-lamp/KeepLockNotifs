import java.util.Properties

plugins {
    id("com.android.application")
}

val signing = Properties().apply {
    val f = file(System.getProperty("user.home") + "/Desktop/KeepLockNotifs-signing/keystore.properties")
    if (f.exists()) f.inputStream().use { load(it) }
}

android {
    namespace = "com.keeplocknotifs"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.keeplocknotifs"
        minSdk = 33
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        if (signing.isNotEmpty()) {
            create("release") {
                storeFile = file(signing.getProperty("storeFile"))
                storePassword = signing.getProperty("storePassword")
                keyAlias = signing.getProperty("keyAlias")
                keyPassword = signing.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.findByName("release")
        }
    }
}

dependencies {
    compileOnly(files("libs/api-82.jar"))
}

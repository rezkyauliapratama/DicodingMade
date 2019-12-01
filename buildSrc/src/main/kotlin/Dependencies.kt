object ApplicationId {
    val id = "id.rezkyauliapratama.dicodingmade"
}

object Release {
    val versionCode = 1
    val versionName = "1.0"

    val compileSdkVersion = 28
    val targetSdkVersion = 28
    val minSdkVersion = 17
}

object Config {
    val gradle = "com.android.tools.build:gradle:3.4.2"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
}

object Version {
    // Kotlin based
    const val kotlinVersion = "1.3.41"
    const val kotlinCoreVersion = "1.0.2"

    //RxJava & RxAndroid
    const val rxkotlinVersion = "2.2.0"
    const val rxandroidVersion = "2.1.0"
    const val rxbindingVersion = "2.1.1"

    //json
    const val moshiVersion = "1.8.0"
    const val gsonVersion = "2.8.5"
    //Dagger
    const val daggerVersion = "2.18"

    //Dependency Injection
    const val koin = "2.0.1"

    //image
    const val glideVersion = "4.9.0"

    //Networking
    const val retrofitVersion = "2.4.0"
    const val okhttpLoggingVersion = "3.11.0"

    //Android jetpack
    const val appcompatVersion = "1.1.0"
    const val constraintLayoutVersion = "2.0.0-alpha5"
    const val navigationVersion = "1.0.0"
    const val lifecycleVersion = "2.0.0"
    const val materialComponentVersion = "1.0.0"
    const val legacySupportVersion = "1.0.0"
    const val pagingVersion = "2.1.0-rc01"

    //test
    const val testRunnerVersion = "1.1.1"
    const val junitVersion = "1.1.1"
    const val espressoCoreVersion = "3.1.0"
    const val fragmentTestVersion = "1.1.0"

    //unit test
    const val mockitoVersion = "2.21.0"
    const val mockitoInlineVersion = "2.19.0"
    const val coreTestingVersion = "1.1.1"
    const val mockitoKotlinVersion = "2.1.0"
    const val daggerMockVersion = "0.8.5"
    const val kotlinTestVersion = "3.3.2"

    //findBugAnnotation
    const val findBugsVersion = "3.0.2"

    const val timber = "4.7.1"
}

object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlinVersion}"
}

object Log {
    val timber = "com.jakewharton.timber:timber:${Version.timber}"
}

object Koin {
    val core = "org.koin:koin-core:${Version.koin}"
    val android = "org.koin:koin-android:${Version.koin}"
    val androidScope = "org.koin:koin-androidx-scope:${Version.koin}"
    val androidViewModel = "org.koin:koin-androidx-viewmodel:${Version.koin}"
}

object Support {
    val core = "androidx.core:core-ktx:${Version.kotlinCoreVersion}"
    val appCompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
    val lifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleVersion}"
    val materialComponent = "com.google.android.material:material:${Version.materialComponentVersion}"
    val legacySupport = "androidx.legacy:legacy-support-v4:${Version.legacySupportVersion}"
}

object Image{
    val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glideVersion}"
}

object Arch {
    val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
    val navigationKtx = "android.arch.navigation:navigation-ui-ktx:${Version.navigationVersion}"
    val paging = "androidx.paging:paging-runtime-ktx:${Version.pagingVersion}"
}

object Retrofit {
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofitVersion}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
    val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofitVersion}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLoggingVersion}"
}

object Json {
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshiVersion}"
    val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshiVersion}"
    val gson = "com.google.code.gson:gson:${Version.gsonVersion}"
}


object Reactivex {
    val android = "io.reactivex.rxjava2:rxandroid:${Version.rxandroidVersion}"
    val kotlin = "io.reactivex.rxjava2:rxkotlin:${Version.rxkotlinVersion}"
    val rxBinding = "com.jakewharton.rxbinding2:rxbinding:${Version.rxbindingVersion}"
}

object Dagger {
    val dagger = "com.google.dagger:dagger:${Version.daggerVersion}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
}

object TestLibs {
    val junit = "androidx.test.ext:junit:${Version.junitVersion}"
    val testRunner = "androidx.test:runner:${Version.testRunnerVersion}"
    val testRules = "androidx.test:rules:${Version.testRunnerVersion}"
    val espresso = "androidx.test.espresso:espresso-core:${Version.espressoCoreVersion}"
    val fragment = "androidx.fragment:fragment:${Version.fragmentTestVersion}"
    val fragmentTestVersion = "androidx.fragment:fragment-testing:${Version.fragmentTestVersion}"
}

object UnitTest {
    val mockitoCore = "org.mockito:mockito-core:${Version.mockitoVersion}"
    val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoInlineVersion}"
    val coreTesting = "android.arch.core:core-testing:${Version.coreTestingVersion}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlinVersion}"
    val daggerMock = "com.github.fabioCollini.daggermock:daggermock-kotlin:${Version.daggerMockVersion}"
    val kotlinTest = "io.kotlintest:kotlintest-runner-junit5:${Version.kotlinTestVersion}"
}

object Annotation {
    val findBugs = "com.google.code.findbugs:jsr305:${Version.findBugsVersion}"
}
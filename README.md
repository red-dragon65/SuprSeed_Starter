
# SuprSeed getting started walkthrough / guide

This starter project provides a default boilerplate setup for using the [SuprSeed engine](https://github.com/red-dragon65/SuprSeed)

**Note: It is highly recommended to take a look at the demo project under SuprSeed to get an idea of how to create a game using this starter project. There, you can see how to setup a custom engine configuration, manage scenes, create sprites, and use various engine tools.**

<br/>

## Initialize the project
- Create a new Android project with an *Empty Activity*
	- ensure the minimum sdk is set to 26 (Oreo: Android 8)

<br/>

## Add the SuprSeed dependency

1. Add `maven { url = uri("https://jitpack.io") }` to your `settings.gradle.kts` file

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        
        // Include this repo!
        maven { url = uri("https://jitpack.io") }
    }
}
```

2. Add `implementation("com.github.red-dragon65:SuprSeed:v1.0.0")` to your `build.gradle.kts` file (note: this is the _Module:app_ level gradle file)

```
dependencies {

	//...default project dependencies
    implementation(libs.androidx.core.ktx)
    //...
    androidTestImplementation(libs.androidx.espresso.core)
    
    // Add this dependency!
    implementation("com.github.red-dragon65:SuprSeed:v1.0.0")
}
```

- Refresh gradle
	- Click the `sync` option that pops up when editing gradle files

- Fixing potential gradle issues
	- If gradle is causing issues, run the two commands below:
	- Run gradle `clean` and `build` under the gradle tab on the right hand side of Android studio
	- Or, click `File -> Invalidate caches` to refresh the ide

<br/>

## Prepare the project
- Remove the base theme portions (ie `android:theme="@style/Theme.MyApplication"`) from their `AndroidManifest.xml`
	- The application will inherit the theme from the engine

- Create a `MainActivity.java` or `MainActivity.kt` file under your package

- Specify the `Activity` in the `AndroidManifest.xml`
```
<activity  
    android:name=".MainActivity"  
    android:exported="true">  
    <intent-filter>        <action android:name="android.intent.action.MAIN" />  
  
        <category android:name="android.intent.category.LAUNCHER" />  
    </intent-filter></activity>
```

```
<application  
    android:allowBackup="true"  
    android:dataExtractionRules="@xml/data_extraction_rules"  
    android:fullBackupContent="@xml/backup_rules"  
    android:icon="@mipmap/ic_launcher"  
    android:label="@string/app_name"  
    android:roundIcon="@mipmap/ic_launcher_round"  
    android:supportsRtl="true"  
    tools:targetApi="31">  
    <activity        android:name=".MainActivity"  
        android:exported="true">  
        <intent-filter>            <action android:name="android.intent.action.MAIN" />  
  
            <category android:name="android.intent.category.LAUNCHER" />  
        </intent-filter>    </activity></application>
```

- Create the `assets` folder
	- Right click the top level folder in the left hand file browser (this should be called `app`)
	- Go to `New -> Folder -> Assets folder` and click `Finish`

- Create a `MainScene` class that extends SuprSeed's `SceneManager`

- Update the `MainActivity` class to extend the SuprSeed's `EngineActivity`
	- Implement the two methods
		- `protected BaseEngineConfigurator loadEngineConfig()`
			- here, you can return a `DefaultEngineConfigurator` object
		- `public void initStartingState(RootScene rootScene)`
			- here, you can add your initialized `MainScene` to the provided `RootScene`

- The project is now setup to allow you to use the SuprSeed engine

<br/>

## Using the engine
- Note: the provided SuprSeed_Starter project does not include the following steps
- Create a package hierarchy to keep things tidy
	- Assets, Scenes, Sprites, Components
- Setup a `MySprite` class
	- Extend the `Sprite` class
	- Instantiate the behavior components you want
	- Under the `runLogic()` function, call the components `update()` function
- Setup a sprite behavior component
	- Create a `SpriteMovement` class that extends `Component`
	- Add a `Sprite` object as a constructor arg
	- In this class, specify the movement of the sprite in the `update()` function
- Setup an `AssetLoader` class
	- Extend the `BasicAssetLoader` class
	- Create a `SpriteImage` (ie, `BitmapCollection` instance with user specified parameters)
	- Add the image so it can me retrieved later `assetRegistry.add(mySpriteimage)`
- Setup the `MainScene` class
	- Instantiate the `AssetLoder` class and use the default `LocalImageFileStreamer` and `LocalFolderParser` classes as dependencies
	- Instantiate a `MySprite` class and pass in the asset retrieved from the asset loader class
- The engine should now display your moving sprite on screen!

<br/>

## Other
- Setup a custom launcher icon and app name
	- Refactor the `ic_launcher` folder names under the `mipmap` folder to something like `custom_launcher`
	- Override the inherited icon from the library by adding `tools:replace="android:icon, android:roundIcon"` in the `<application/>` section of their `AndroidManifest.xml` file
	- Specify the apps launch name by editing the `strings.xml` file under the `values` folder

<br/>

## Final notes
- SuprSeed requires the `appcompatactivity` dependency in order for the `EngineActivity` to properly work. An empty activity Android project should provide this dependency under gradle by default. If not, add the dependency follow the steps below:
	- add the `implementation(libs.androidx.appcompat)` dependency to the `build.gradle.kts` file
	- add the `appcompat = "1.7.0"` line under the `[versions]` section in the `libs.versions.toml` file
	- add the `androidx-appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }` line under the `[libraries]` section in the `libs.versions.toml` file

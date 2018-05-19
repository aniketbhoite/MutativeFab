# MutativeFab


[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![GitHub license](https://img.shields.io/github/license/aniketbhoite/MutativeFab.svg)](https://github.com/aniketbhoite/MutativeFab/blob/master/LICENSE)


![Mutative button demo gif](https://user-images.githubusercontent.com/13782400/34786063-02843f1a-f659-11e7-89d4-8507a89facbb.gif)
![New demo](https://user-images.githubusercontent.com/13782400/35433701-6307c50c-02aa-11e8-8142-698a06df529d.gif)

![Button design](https://user-images.githubusercontent.com/13782400/34787268-af5fec18-f65c-11e7-9f04-312370cd09b6.png)

## Dependency

**Step 1:** Add [Jitpack](https://jitpack.io) repository in your root build.gradle file (not your module build.gradle file):

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

**Step 2:** Add dependency to your module's build.gradle file:

```
dependencies {
    compile 'com.github.aniketbhoite:MutativeFab:1.0.7'
}
```


## Usage

Add floating text button to your layout file like this:

```xml
    <com.aniket.mutativefloatingactionbutton.MutativeFab
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:fabIcon="@drawable/ic_location"
        app:fabText="relocate"
        android:layout_alignParentBottom="true"
        app:fabBackgroundColor="@android:color/white"
        app:fabTextColor="@color/colorPrimary"
        android:layout_alignParentEnd="true"/>
```

**In Java or Kotlin**
```java
            mFab.setFabText("start chat");
            mFab.setFabTextColor(ContextCompat.getColor(context, R.color.textColor));
            mFab.setFabIcon(R.drawable.ic_chat);
            mFab.setFabBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
            mFab.setFabTextVisibility(View.VISIBLE);//or View.GONE only
            //to get TextView visibility
            mFab.getFabTextVisibility();//returns Int
            mFab.setFabTypeface(typeface);
```            
If your project do not have kotlin support follow these steps

**Step 1:** Add [Jitpack](https://jitpack.io/v/aniketbhoite/MutativeFab) kotlin gradle plugin in your root build.gradle file (not your module build.gradle file):
```
    buildscript {
    //this
    ext.kotlin_version = '1.2.10'
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
    dependencies {
        ...
        //and this line
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
```
To more info and latest version [refer this](https://kotlinlang.org/docs/reference/using-gradle.html)

**Step 2:** Apply plugins to your module's build.gradle file:

    apply plugin: 'kotlin-android'

    apply plugin: 'kotlin-android-extensions'
    ...



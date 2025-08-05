package com.example.taskapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("TaskApp", "✅ Application created")
    }
}
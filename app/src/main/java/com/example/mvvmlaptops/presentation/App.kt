package com.example.mvvmlaptops.presentation

import android.app.Application
import com.example.mvvmlaptops.DI.Dep

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Dep.init(this)
    }
}
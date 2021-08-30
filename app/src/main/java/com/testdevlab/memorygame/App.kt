package com.testdevlab.memorygame

import android.app.Application
import androidx.databinding.ktx.BuildConfig
import com.testdevlab.memorygame.injection.InjectionModule
import com.testdevlab.memorygame.common.LineNumberDebugThree
import com.testdevlab.memorygame.injection.DaggerInjectionComponent
import com.testdevlab.memorygame.injection.InjectionComponent
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(LineNumberDebugThree())
        }
        Timber.d("App created")
        component = DaggerInjectionComponent
            .builder()
            .injectionModule(InjectionModule(this))
            .build()
    }

    companion object {
        lateinit var component: InjectionComponent
            private set
    }
}
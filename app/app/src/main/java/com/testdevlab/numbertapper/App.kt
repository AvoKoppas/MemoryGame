package com.testdevlab.numbertapper

import android.app.Application

import com.testdevlab.numbertapper.common.LineNumberDebugThree
import com.testdevlab.numbertapper.injection.DaggerInjectionComponent
import com.testdevlab.numbertapper.injection.InjectionComponent
import com.testdevlab.numbertapper.injection.InjectionModule
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
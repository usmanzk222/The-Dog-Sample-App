package com.usman.mvvmsample

import android.app.Application
import com.usman.mvvmsample.dagger.components.AppComponent
import com.usman.mvvmsample.dagger.components.DaggerAppComponent
import com.usman.mvvmsample.dagger.module.AppModule

/**
 * Created by Muhammad Usman on 1/31/2019.
 */
class MVVMApplication: Application() {

    private lateinit var appComponent: AppComponent

    companion object {
        private lateinit var sInstance: MVVMApplication
        fun getInstance(): MVVMApplication {
            return sInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
        sInstance = this
    }

    fun getAppComponent(): AppComponent = appComponent
}
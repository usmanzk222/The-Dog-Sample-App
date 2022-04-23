package com.usman.mvvmsample.dagger

import androidx.lifecycle.ViewModelProvider
import com.usman.mvvmsample.dagger.module.AppModule
import com.usman.mvvmsample.dagger.module.MainActivityComponent
import com.usman.mvvmsample.dagger.module.NetworkModule
import com.usman.mvvmsample.dagger.module.SubcomponentsModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Muhammad Usman on 1/31/2019.
 */

@Singleton
@Component(modules = [AppModule::class,
    NetworkModule::class, SubcomponentsModule::class])
interface AppComponent{

    fun mainActivityComponent(): MainActivityComponent.Factory
}

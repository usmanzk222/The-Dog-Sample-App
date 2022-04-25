package com.usman.mvvmsample.dagger.components

import com.usman.mvvmsample.dagger.module.AppModule
import com.usman.mvvmsample.dagger.module.NetworkModule
import com.usman.mvvmsample.dagger.module.SubcomponentsModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,
    NetworkModule::class, SubcomponentsModule::class])
interface AppComponent{

    fun mainActivityComponent(): MainActivityComponent.Factory
}

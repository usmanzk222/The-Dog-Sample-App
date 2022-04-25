package com.usman.mvvmsample.dagger.module

import androidx.lifecycle.ViewModelProvider
import com.usman.mvvmsample.dagger.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
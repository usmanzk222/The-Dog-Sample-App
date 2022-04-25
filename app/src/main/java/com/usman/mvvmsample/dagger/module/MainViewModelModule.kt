package com.usman.mvvmsample.dagger.module

import androidx.lifecycle.ViewModel
import com.usman.mvvmsample.dagger.annotations.MainActivityScope
import com.usman.mvvmsample.dagger.annotations.ViewModelKey
import com.usman.mvvmsample.features.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Muhammad Usman on 11/15/2018.
 */

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @MainActivityScope
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

}
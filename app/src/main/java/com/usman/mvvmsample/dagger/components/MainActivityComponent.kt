package com.usman.mvvmsample.dagger.components

import com.usman.mvvmsample.DetailFragment
import com.usman.mvvmsample.dagger.annotations.MainActivityScope
import com.usman.mvvmsample.dagger.module.MainViewModelModule
import com.usman.mvvmsample.features.MainActivity
import com.usman.mvvmsample.features.ui.main.MainFragment
import dagger.Subcomponent


@MainActivityScope
@Subcomponent(modules = [MainViewModelModule::class])
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(detailFragment: DetailFragment)
}

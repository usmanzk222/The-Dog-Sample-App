package com.usman.mvvmsample.dagger.components

import com.usman.mvvmsample.features.main.ui.DetailFragment
import com.usman.mvvmsample.dagger.annotations.MainActivityScope
import com.usman.mvvmsample.dagger.module.MainViewModelModule
import com.usman.mvvmsample.features.main.MainActivity
import com.usman.mvvmsample.features.main.ui.MainFragment
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

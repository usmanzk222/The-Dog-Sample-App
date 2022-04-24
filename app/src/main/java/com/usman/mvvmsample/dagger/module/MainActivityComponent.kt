package com.usman.mvvmsample.dagger.module

import com.usman.mvvmsample.DetailFragment
import com.usman.mvvmsample.features.MainActivity
import com.usman.mvvmsample.features.ui.main.MainFragment
import dagger.Subcomponent

/**
 * Created by Muhammad Usman on 21/04/2022.
 */

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

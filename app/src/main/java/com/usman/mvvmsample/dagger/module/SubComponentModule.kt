package com.usman.mvvmsample.dagger.module

import com.usman.mvvmsample.dagger.components.MainActivityComponent
import dagger.Module

@Module(subcomponents = [MainActivityComponent::class])
class SubcomponentsModule {}
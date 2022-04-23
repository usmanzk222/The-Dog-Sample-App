package com.usman.mvvmsample.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.usman.mvvmsample.MVVMApplication
import com.usman.mvvmsample.features.model.ProductsDAO
import com.usman.mvvmsample.persistence.TestAppDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * Created by Muhammad Usman on 1/31/2019.
 */

@Module(includes = [ViewModelModule::class])
class AppModule(private val application: MVVMApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideApplication(application: MVVMApplication): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }


    @Singleton
    @Provides
    @Named("IO")
    fun provideIODispatchers(): CoroutineContext {
        return Dispatchers.IO
    }


    @Singleton
    @Provides
    fun provideDb(application: Application): TestAppDatabase {
        return Room.databaseBuilder(application, TestAppDatabase::class.java, "mvvm-sample-db").build()

    }

    @Singleton
    @Provides
    fun provideProductsDAO(database: TestAppDatabase): ProductsDAO {
        return database.productsDAO()
    }


}

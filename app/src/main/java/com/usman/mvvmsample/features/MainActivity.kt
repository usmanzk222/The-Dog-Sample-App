package com.usman.mvvmsample.features

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.usman.mvvmsample.MVVMApplication
import com.usman.mvvmsample.R
import com.usman.mvvmsample.dagger.module.MainActivityComponent
import com.usman.mvvmsample.features.ui.main.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    val mainComponent: MainActivityComponent by lazy {
        MVVMApplication.getInstance()
            .getAppComponent()
            .mainActivityComponent().create()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: MainViewModel by viewModels<MainViewModel> {viewModelFactory  }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController: NavController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }
}
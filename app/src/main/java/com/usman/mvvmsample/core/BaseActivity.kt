package com.usman.mvvmsample.core


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<DB:ViewDataBinding,VM:BaseViewModel>(mViewModelClass: Class<VM>) : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    lateinit var binding:DB

    val viewModel: VM by lazy {
        ViewModelProvider(this, getVMFactory())[mViewModelClass]
    }

    abstract fun getVMFactory(): ViewModelProvider.Factory

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        onInject()
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
    }

    abstract fun initViewModel(viewModel: VM)
}



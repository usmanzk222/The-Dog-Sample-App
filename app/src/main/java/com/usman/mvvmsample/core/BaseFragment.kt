package com.usman.mvvmsample.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

open class BaseFragment <DB: ViewDataBinding>(@LayoutRes private val layoutResId : Int) : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding : DB? = null
    val binding : DB get() = _binding!!

    // Make it open, so it can be overridden in child fragments
    open fun initialize(){
        binding.root.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        postponeEnterTransition()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Calling the extension function
        initialize()
    }

    /**
     * If you want to inject your fragment
     * you can override this.
     */
    open fun inject(context: Context) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
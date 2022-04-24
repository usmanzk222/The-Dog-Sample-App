package com.usman.mvvmsample

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.transition.TransitionInflater
import com.usman.mvvmsample.core.BaseFragment
import com.usman.mvvmsample.databinding.FragmentDetailBinding
import com.usman.mvvmsample.features.MainActivity
import com.usman.mvvmsample.features.ui.main.MainViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) { viewModelFactory }
    private val args: DetailFragmentArgs by navArgs()

    override fun inject(context: Context) {
        if (context is MainActivity) {
            context.mainComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDogDetail(args.breedId)
        sharedElementEnterTransition =
            TransitionInflater.from(requireActivity()).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(requireActivity()).inflateTransition(android.R.transition.move)
    }

    override fun initialize() {
        binding.imgDog.transitionName = "${args.breedId}"
        viewModel.detailLiveData.observe(viewLifecycleOwner) {
            it?.let { breed ->
                binding.dogBreed = breed
                startPostponedEnterTransition()
                (activity as AppCompatActivity).supportActionBar?.title = breed.name
            } ?: kotlin.run {
                startPostponedEnterTransition()
            }
        }
    }
}
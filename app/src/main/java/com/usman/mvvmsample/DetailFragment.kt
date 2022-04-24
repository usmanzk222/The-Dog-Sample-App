package com.usman.mvvmsample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.transition.TransitionInflater
import com.usman.mvvmsample.databinding.FragmentDetailBinding
import com.usman.mvvmsample.features.MainActivity
import com.usman.mvvmsample.features.ui.main.MainViewModel
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) {viewModelFactory  }
    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            context.mainComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDogDetail(args.breedId)
        sharedElementEnterTransition = TransitionInflater.from(requireActivity()).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(requireActivity()).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgDog.transitionName = "${args.breedId}"
        viewModel.detailLiveData.observe(viewLifecycleOwner){
            it?.let { breed ->
                binding.dogBreed = breed
                startPostponedEnterTransition()
                (activity as AppCompatActivity).supportActionBar?.title = breed.name
            }?: kotlin.run {
                startPostponedEnterTransition()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
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
import com.usman.mvvmsample.databinding.FragmentDetailBinding
import com.usman.mvvmsample.features.MainActivity
import com.usman.mvvmsample.features.ui.main.MainViewModel
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: MainViewModel by activityViewModels { viewModelFactory }
    val args: DetailFragmentArgs by navArgs()

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
        viewModel.detailLiveData.observe(viewLifecycleOwner){
            binding.dogBreed = it
            (activity as AppCompatActivity).supportActionBar?.title = it?.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
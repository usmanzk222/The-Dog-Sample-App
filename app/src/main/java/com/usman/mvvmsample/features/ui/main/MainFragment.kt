package com.usman.mvvmsample.features.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.usman.mvvmsample.R
import com.usman.mvvmsample.core.Status
import com.usman.mvvmsample.databinding.MainFragmentBinding
import com.usman.mvvmsample.features.MainActivity
import com.usman.mvvmsample.features.model.DogBreeds
import javax.inject.Inject

class MainFragment : Fragment(), DogsBreedAdapter.ItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: MainViewModel by activityViewModels {viewModelFactory  }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if( context is MainActivity){
            context.mainComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    inflateItems(it.data)
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                }
                else -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    private fun inflateItems(list: List<DogBreeds>?) {
        list?.let {
            val layoutManager = GridLayoutManager(context, 2)
            binding.recyclerView.layoutManager = layoutManager
            binding.recyclerView.adapter = DogsBreedAdapter(it,this)

        }
    }

    override fun onItemClicked(item: DogBreeds) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(item.id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
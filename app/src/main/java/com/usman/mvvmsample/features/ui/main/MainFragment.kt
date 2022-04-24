package com.usman.mvvmsample.features.ui.main

import android.content.Context
import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.usman.mvvmsample.R
import com.usman.mvvmsample.core.BaseFragment
import com.usman.mvvmsample.databinding.MainFragmentBinding
import com.usman.mvvmsample.features.MainActivity
import com.usman.mvvmsample.features.model.DogBreeds
import com.usman.mvvmsample.utils.handleNetworkResponse
import com.usman.mvvmsample.utils.notifyWithAction

class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment),
    DogsBreedAdapter.ItemClickListener {
    val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) { viewModelFactory }

    override fun inject(context: Context) {
        if (context is MainActivity) {
            context.mainComponent.inject(this)
        }
    }

    override fun initialize() {
        super.initialize()

        viewModel.liveData.handleNetworkResponse(viewLifecycleOwner,
            binding.progressBar,
            onSuccess = { inflateItems(it) },
            onError = {
                binding.root.notifyWithAction(it, R.string.retry) { viewModel.fetchData() }
            })
    }

    private fun inflateItems(list: List<DogBreeds>?) {
        list?.let {
            val layoutManager = GridLayoutManager(context, 2)
            binding.recyclerView.layoutManager = layoutManager
            binding.recyclerView.adapter = DogsBreedAdapter(it, this)

        }
    }

    override fun onItemClicked(item: DogBreeds, view: View) {
        val extras = FragmentNavigatorExtras(
            view to "${item.id}"
        )
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(item.id),
            extras
        )
    }
}
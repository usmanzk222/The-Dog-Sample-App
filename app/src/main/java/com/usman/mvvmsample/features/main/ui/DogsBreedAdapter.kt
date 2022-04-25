package com.usman.mvvmsample.features.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.usman.mvvmsample.R
import com.usman.mvvmsample.databinding.ItemDogBreedBinding
import com.usman.mvvmsample.features.main.model.DogBreeds

class DogsBreedAdapter(private var list: List<DogBreeds>,
                       private val itemClickListener: ItemClickListener
): RecyclerView.Adapter<DogsBreedAdapter.DogsBreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsBreedViewHolder {
        val binding: ItemDogBreedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_dog_breed,
            parent,
            false
        )

        return DogsBreedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogsBreedViewHolder, position: Int) {
       holder.bind(list[holder.absoluteAdapterPosition],itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class DogsBreedViewHolder(private val binding: ItemDogBreedBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DogBreeds, itemClickListener: ItemClickListener){
            binding.item = item
            binding.imgDog.transitionName = "${item.id}"
            binding.itemClickListener = itemClickListener
            binding.executePendingBindings()
        }
    }

    interface ItemClickListener{
        fun onItemClicked(item: DogBreeds, view: View)
    }
}
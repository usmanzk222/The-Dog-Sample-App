package com.usman.mvvmsample.features.main.ui

import androidx.lifecycle.*
import com.usman.mvvmsample.core.NetworkResponse
import com.usman.mvvmsample.features.main.model.DogBreeds
import com.usman.mvvmsample.features.main.repository.MainRepository
import com.usman.mvvmsample.utils.Event
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: MainRepository) : ViewModel() {

    private val getData = MutableLiveData<Event<Unit>>()
    private val getDetailEvent = MutableLiveData<Event<Int>>()

    val liveData: LiveData<NetworkResponse<List<DogBreeds>>> = Transformations.switchMap(getData) {
        repo.getData()
    }

    val detailLiveData: LiveData<DogBreeds?> = Transformations.switchMap(getDetailEvent) { event ->
        liveData.map {
            it.data?.find { breed -> breed.id == event.peekContent() }
        }
    }

    init {
        fetchData()
    }

    fun fetchData(){
        getData.value = Event(Unit)
    }

    fun getDogDetail(breedId: Int) {
        getDetailEvent.value = Event(breedId)
    }
}
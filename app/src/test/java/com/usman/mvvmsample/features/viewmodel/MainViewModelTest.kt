package com.usman.mvvmsample.features.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.usman.mvvmsample.core.NetworkResponse
import com.usman.mvvmsample.features.main.model.DogBreeds
import com.usman.mvvmsample.features.main.repository.MainRepository
import com.usman.mvvmsample.features.main.ui.MainViewModel
import com.usman.mvvmsample.util.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class MainViewModelTest {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var repository: MainRepository

    @MockK
    private lateinit var observer: Observer<NetworkResponse<List<DogBreeds>>>


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetDogBreedsSuccess() {

        val expected = NetworkResponse.success(listOf<DogBreeds>())
        every { repository.getData() } returns MutableLiveData(expected)

        viewModel.fetchData()
        val response = viewModel.liveData.getOrAwaitValue()
        TestCase.assertEquals(response, expected)
    }

    @Test
    fun testGetDogBreedsFailure() {

        val expected = NetworkResponse.error<List<DogBreeds>>("Something went wrong")
        every { repository.getData() } returns MutableLiveData(expected)

        viewModel.fetchData()
        val response = viewModel.liveData.getOrAwaitValue()
        TestCase.assertEquals(response, expected)
    }

    @Test
    fun testGetDogBreedsLoading() {

        val expected = NetworkResponse.loading<List<DogBreeds>>()
        every { repository.getData() } returns MutableLiveData(expected)

        viewModel.fetchData()
        val response = viewModel.liveData.getOrAwaitValue()
        TestCase.assertEquals(response, expected)
    }

    @Test
    fun testDogBreedsLiveOnChangeCalledOnSuccess() {

        val expected = NetworkResponse.success(listOf<DogBreeds>())
        every { repository.getData() } returns MutableLiveData(expected)

        viewModel.fetchData()

        viewModel.liveData.observeForever(observer)

        verify{observer.onChanged(expected)}

        viewModel.liveData.removeObserver(observer)

    }

    @Test
    fun testDogBreedsLiveOnChangeCalledOnFailure() {

        val expected = NetworkResponse.error<List<DogBreeds>>("Something went wrong")
        every { repository.getData() } returns MutableLiveData(expected)

        viewModel.fetchData()

        viewModel.liveData.observeForever(observer)

        verify(atLeast = 1){observer.onChanged(expected)}

        viewModel.liveData.removeObserver(observer)

    }
}
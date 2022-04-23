package com.usman.mvvmsample.features.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.usman.mvvmsample.core.NetworkResponse
import com.usman.mvvmsample.features.model.Product
import com.usman.mvvmsample.features.repository.MainRepository
import com.usman.mvvmsample.features.ui.main.MainViewModel
import com.usman.mvvmsample.util.getOrAwaitValue
import com.usman.mvvmsample.util.observeForTesting
import com.usman.mvvmsample.utils.Event
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class ProductsViewModelTestJ {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var repository: MainRepository

    @MockK
    private lateinit var observer: Observer<NetworkResponse<List<Product>>>


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
    }

//    @Test
//    fun testGetProductsSuccess() {
//
//        val expected = NetworkResponse.success(listOf<Product>())
//        every { repository.getProducts(listOf(), listOf()) } returns MutableLiveData(expected)
//
//        viewModel.getProducts(listOf(), listOf())
//        val response = viewModel.productLiveData.getOrAwaitValue()
//        TestCase.assertEquals(response, expected)
//    }
//
//    @Test
//    fun testGetProductsFailure() {
//
//        val expected = NetworkResponse.error<List<Product>>("Something went wrong")
//        every { repository.getProducts(listOf(), listOf()) } returns MutableLiveData(expected)
//
//        viewModel.getProducts(listOf(), listOf())
//        val response = viewModel.productLiveData.getOrAwaitValue()
//        TestCase.assertEquals(response, expected)
//    }
//
//    @Test
//    fun testGetProductsLoading() {
//
//        val expected = NetworkResponse.loading<List<Product>>()
//        every { repository.getProducts(listOf(), listOf()) } returns MutableLiveData(expected)
//
//        viewModel.getProducts(listOf(), listOf())
//        val response = viewModel.productLiveData.getOrAwaitValue()
//        TestCase.assertEquals(response, expected)
//    }
//
//    @Test
//    fun testProductsLiveOnChangeCalledOnSuccess() {
//
//        val expected = NetworkResponse.success(listOf<Product>())
//        every { repository.getProducts(listOf(), listOf()) } returns MutableLiveData(expected)
//
//        viewModel.getProducts(listOf(), listOf())
//
//        viewModel.productLiveData.observeForever(observer)
//
//        verify{observer.onChanged(expected)}
//
//        viewModel.productLiveData.removeObserver(observer)
//
//    }
//
//    @Test
//    fun testProductsLiveOnChangeCalledOnFailure() {
//
//        val expected = NetworkResponse.error<List<Product>>("Something went wrong")
//        every { repository.getProducts(listOf(), listOf()) } returns MutableLiveData(expected)
//
//        viewModel.getProducts(listOf(), listOf())
//
//        viewModel.productLiveData.observeForever(observer)
//
//        verify(atLeast = 1){observer.onChanged(expected)}
//
//        viewModel.productLiveData.removeObserver(observer)
//
//    }
}
package com.usman.mvvmsample.backend

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usman.mvvmsample.core.NetworkResponse
import com.usman.mvvmsample.util.MainCoroutineRule
import com.usman.mvvmsample.util.captureValues
import com.usman.mvvmsample.util.getOrAwaitValue
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class NetworkHelperTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var networkHelper: NetworkHelper


    interface Delegation {
        suspend fun serviceCall(): NetworkResponse<String>
        suspend fun saveResult(s: String)
        fun localResult(): MutableLiveData<String>
    }

    @MockK
    private lateinit var delegation: Delegation
    private val LOCAL_RESULT = "Local Result"
    private val REMOTE_RESULT = "Remote Result"
    private val REMOTE_ERROR = "Remote Result"


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        networkHelper = NetworkHelper(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should call local and then load data from remote and save it`()= mainCoroutineRule.runBlockingTest{

        every { delegation.localResult() } returns MutableLiveData(LOCAL_RESULT)
        coEvery { delegation.serviceCall() } returns NetworkResponse.success(REMOTE_RESULT)

        networkHelper.getDataFromService({ delegation.localResult() },{ str -> delegation.saveResult(str)},{delegation.serviceCall()} ).getOrAwaitValue()
        verify(atLeast = 1) { delegation.localResult() }
        coVerify(atLeast = 1) { delegation.serviceCall() }
        coVerify(atLeast = 1) { delegation.saveResult(REMOTE_RESULT) }
    }

    @Test
    fun `skip call to saveResult in case of error`()= mainCoroutineRule.runBlockingTest{

        every { delegation.localResult() } returns MutableLiveData(LOCAL_RESULT)
        coEvery { delegation.serviceCall() } returns NetworkResponse.error(REMOTE_ERROR)

        networkHelper.getDataFromService({ delegation.localResult() },{ str -> delegation.saveResult(str)},{delegation.serviceCall()} ).getOrAwaitValue()
        verify(exactly = 1) { delegation.localResult() }
        coVerify(exactly = 1) { delegation.serviceCall() }
        coVerify(exactly = 0) { delegation.saveResult(REMOTE_RESULT) }
    }



    @Test
    fun `emit loading and success`()= mainCoroutineRule.runBlockingTest{

        every { delegation.localResult() } returns MutableLiveData(LOCAL_RESULT)
        coEvery { delegation.serviceCall() } returns NetworkResponse.success(REMOTE_RESULT)

        val result:LiveData<NetworkResponse<String>> = networkHelper.getDataFromService({ delegation.localResult() },{ str -> delegation.saveResult(str)},{delegation.serviceCall()} )
        //then
        result.captureValues {
            assertEquals(arrayListOf(NetworkResponse.loading(),
                NetworkResponse.success(LOCAL_RESULT)), values)
        }
    }

    @Test
    fun `in case of error first emit loading then emit error and finally data from local`()= mainCoroutineRule.runBlockingTest{

        every { delegation.localResult() } returns MutableLiveData(LOCAL_RESULT)
        coEvery { delegation.serviceCall() } returns NetworkResponse.error(REMOTE_ERROR)

        val result:LiveData<NetworkResponse<String>> = networkHelper.getDataFromService({ delegation.localResult() },{ str -> delegation.saveResult(str)},{delegation.serviceCall()} )
        //then
        result.captureValues {
            assertEquals(arrayListOf(NetworkResponse.loading(),
                NetworkResponse.error("Invalid Response"),
                NetworkResponse.success(LOCAL_RESULT)
            ), values)
        }
    }
}
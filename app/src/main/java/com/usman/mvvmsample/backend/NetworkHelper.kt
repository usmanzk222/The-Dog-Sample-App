package com.usman.mvvmsample.backend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.usman.mvvmsample.core.NetworkResponse
import com.usman.mvvmsample.core.Status
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class NetworkHelper @Inject constructor(
    @Named("IO")
    private val coroutineContext: CoroutineContext
) {

    fun <T> networkCall(serviceCall: suspend () -> T): LiveData<NetworkResponse<T>> {
        return liveData(coroutineContext) {
            emit(NetworkResponse.loading())

            try {
                serviceCall.invoke()?.let {
                    emit(NetworkResponse.success(it))
                } ?: kotlin.run {
                    emit(NetworkResponse.error("Something went wrong"))
                }
            } catch (e: Exception) {
                emit(NetworkResponse.error(e.localizedMessage ?: "Something went wrong"))
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getDataFromService(
        localResult: (() -> LiveData<T>) = { MutableLiveData() },
        saveResponse: suspend (T) -> Unit = { },
        serviceCall: suspend () -> NetworkResponse<T>?
    ): LiveData<NetworkResponse<T>> {
        return liveData(coroutineContext) {
            emit(NetworkResponse.loading())
            val fromDb: LiveData<NetworkResponse<T>> = localResult.invoke().map {
                NetworkResponse.success(it)
            }
            try {
                val response = serviceCall.invoke()
                response?.data?.let { data ->
                    saveResponse(data)
                } ?: kotlin.run {
                    throw Exception("Invalid Response")
                }
            }catch (e: Exception){
                if(e is UnknownHostException) {
                    emit(NetworkResponse.error("Error in connection"))
                }else
                    emit(NetworkResponse.error(e.localizedMessage?:"Something went wrong"))
            }finally {
                emitSource(fromDb)
            }
        }
    }
}

fun <T> executeCoroutine(task: suspend () -> T, coroutineContext: CoroutineContext = Dispatchers.IO):LiveData<T> =
    liveData(coroutineContext) {
        emit(task.invoke())
    }


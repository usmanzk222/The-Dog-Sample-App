package com.usman.mvvmsample.features.repository

import com.usman.mvvmsample.apicalls.ServiceGateway
import com.usman.mvvmsample.backend.NetworkHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val networkHelper: NetworkHelper,
private val serviceGateway: ServiceGateway) {

    fun getData() = networkHelper.networkCall { serviceGateway.getDogBreeds() }
}
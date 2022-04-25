package com.usman.mvvmsample.features.main.repository

import com.usman.mvvmsample.apicalls.ServiceGateway
import com.usman.mvvmsample.backend.NetworkHelper
import com.usman.mvvmsample.features.main.model.DogBreedsDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val networkHelper: NetworkHelper,
private val serviceGateway: ServiceGateway,
private val dao: DogBreedsDao) {

    fun getData() = networkHelper.getDataFromService ( {dao.getAll()}, {dao.insertAll(it)}, {serviceGateway.getDogBreeds()} )
}
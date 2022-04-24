package com.usman.mvvmsample.core

import com.google.gson.annotations.SerializedName

data class NetworkResponse<T>(val status: Status,
                         val data: T? = null,
                         val meta: Meta? = null,
                         val message: String? = null) {

    companion object {

        fun <T> success(data: T): NetworkResponse<T> {
            return NetworkResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): NetworkResponse<T> {
            return NetworkResponse(Status.ERROR, data, message = msg)
        }

        fun <T> loading(): NetworkResponse<T> {
            return NetworkResponse(Status.LOADING)
        }

    }
}

data class Meta(
    val max_products_per_box: Int,
    val offset: Int,
    val limit: Int,
    val count: Int,
    val total: Int
)

enum class Status {
    @SerializedName("loading")
    LOADING,
    @SerializedName("ok")
    SUCCESS,
    @SerializedName("error")
    ERROR
}
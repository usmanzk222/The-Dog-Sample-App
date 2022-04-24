package com.usman.mvvmsample.utils

import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.usman.mvvmsample.core.NetworkResponse
import com.usman.mvvmsample.core.Status

fun <T> LiveData<NetworkResponse<T>>.handleNetworkResponse(
    lifecycleOwner: LifecycleOwner,
    progressBar: ProgressBar,
    onSuccess: (T?) -> Unit,
    onError: (String) -> Unit
) {
    observe(lifecycleOwner) {
        when (it.status) {
            Status.SUCCESS -> {
                progressBar.isVisible = false
                onSuccess(it.data)
            }
            Status.LOADING -> {
                progressBar.isVisible = true
            }
            Status.ERROR -> {
                progressBar.isVisible = false
                onError(it.message ?: "Something went wrong")
            }
        }
    }
}

fun View.notify(@StringRes message: Int) =
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()

fun View.notifyWithAction(
    message: String,
    @StringRes actionText: Int,
    action: () -> Any
): Snackbar {
    return Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).apply {
        setAction(actionText) { _ -> action.invoke() }
        setActionTextColor(ContextCompat.getColor(this.context, android.R.color.holo_red_dark))
        show()
    }

}
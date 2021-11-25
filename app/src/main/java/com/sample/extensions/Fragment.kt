package com.sample.extensions

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.material.snackbar.Snackbar
import com.sample.R
import com.sample.data.remote.Resource

/**
 * Extension Function
 */
fun <T : ViewModel> obtainViewModel(
    owner: ViewModelStoreOwner,
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory
) = ViewModelProvider(owner, viewModelFactory).get(viewModelClass)

@BindingAdapter("app:visible")

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}


fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}


fun Fragment.handleApiError(
    errorView: TextView,
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    errorView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
    errorView.setOnClickListener {
        errorView.visible(false)
        retry?.invoke()
    }
    errorView.visible(true)
    when {
        failure.isNetworkError -> {
            errorView.text = getString(R.string.please_check_your_internet)
        }
        failure.errorMessage != null -> {
            errorView.text = failure.errorMessage.toString()
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            errorView.text = error
        }
    }
}

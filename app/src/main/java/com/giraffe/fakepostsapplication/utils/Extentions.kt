package com.giraffe.fakepostsapplication.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> safeCall(apiCall: suspend () -> Flow<T>): Resource<T> {
    return withContext(Dispatchers.IO) {
        var resource: Resource<T> = Resource.Loading
        try {
            apiCall.invoke()
                .catch {
                    resource = Resource.Failure(0, it)
                }.collect {
                    resource = if (it is Response<*>) {
                        if (it.isSuccessful && it.body() != null) {
                            Resource.Success(it)
                        } else {
                            Resource.Failure(it.code(), it.errorBody())
                        }
                    } else {
                        Resource.Success(it)
                    }
                }
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    resource = Resource.Failure(
                        throwable.code(),
                        throwable.response()!!.errorBody() as ResponseBody
                    )
                }

                is JsonSyntaxException -> {
                    resource = Resource.Failure(
                        0,
                        throwable.localizedMessage
                    )
                }

                else -> {
                    resource = Resource.Failure(null, null)
                }
            }
        }
        resource
    }
}

fun Fragment.handleApiErrors(
    failure: Resource.Failure,
) {
    when (failure.errorCode) {
        Constants.HTTP_CODES.CODE_INTERNAL_SERVER_ERROR.code -> {
            showSnackbar("something went wrong !!")
        }
        Constants.HTTP_CODES.CODE_URL_NOT_FOUND.code -> {
            showSnackbar("something went wrong !!")
        }
        else -> {
            if (failure.errorBody is ResponseBody) {
                val error = failure.errorBody.string()
                val baseResponse = Gson().fromJson(error, ErrorResponse::class.java)
                showSnackbar(baseResponse?.message ?: "null")
            } else if (failure.errorBody is String) {
                showSnackbar(failure.errorBody)
            }
        }
    }
}

data class ErrorResponse(
    val message: String,
)

fun Fragment.showSnackbar(message: String) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG).show()
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}
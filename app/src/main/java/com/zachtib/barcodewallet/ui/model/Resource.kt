package com.zachtib.barcodewallet.ui.model

sealed class Resource<T> {
    object Loading : Resource<Nothing>()
    data class Content<T>(val data: T) : Resource<T>()
    data class Error<T>(val throwable: Throwable) : Resource<T>()
}
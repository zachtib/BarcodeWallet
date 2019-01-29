package com.zachtib.barcodewallet.ui.screens.barcodelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zachtib.barcodewallet.models.Barcode
import com.zachtib.barcodewallet.ui.model.Resource
import kotlinx.coroutines.delay

class BarcodeListViewModel : ViewModel() {
    private val _barcodes = MutableLiveData<Resource<List<Barcode>>>()

    val barcodes: LiveData<Resource<List<Barcode>>>
        get() = _barcodes

    init {
        _barcodes.value = Resource.Loading
    }

    suspend fun loadBarcodes() {
        _barcodes.postValue(Resource.Loading)
        delay(3000)
        _barcodes.postValue(Resource.Content(listOf(Barcode(1, "Kroger Plus Card"))))
    }
}
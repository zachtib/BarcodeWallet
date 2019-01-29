package com.zachtib.barcodewallet.ui.screens.barcodelist

import android.os.Bundle
import androidx.core.view.isVisible
import com.zachtib.barcodewallet.R
import com.zachtib.barcodewallet.models.Barcode
import com.zachtib.barcodewallet.ui.adapters.BarcodeListAdapter
import com.zachtib.barcodewallet.ui.base.FragmentView
import com.zachtib.barcodewallet.ui.ktx.showSnackbar
import com.zachtib.barcodewallet.ui.model.Resource
import kotlinx.android.synthetic.main.fragment_barcode_list.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class BarcodeListFragment : FragmentView(R.layout.fragment_barcode_list) {

    private val viewModel: BarcodeListViewModel by viewModel()

    private val adapter = BarcodeListAdapter(this::onBarcodeClicked)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        barcodeRecycler.attach(adapter)

        viewModel.barcodes.observe { resource ->
            when (resource) {
                Resource.Loading -> showLoading()
                is Resource.Error -> showError(resource.throwable)
                is Resource.Content -> showBarcodes(resource.data)
            }
        }

        launch {
            viewModel.loadBarcodes()
        }
    }

    private fun showBarcodes(barcodes: List<Barcode>) {
        progressSpinner.isVisible = false
        if (barcodes.isEmpty()) {
            listEmptyMessage.isVisible = true
            barcodeRecycler.isVisible = false
        } else {
            listEmptyMessage.isVisible = false
            barcodeRecycler.isVisible = true
            adapter.submitList(barcodes)
        }
    }

    private fun showLoading() {
        progressSpinner.isVisible = true
        listEmptyMessage.isVisible = false
        barcodeRecycler.isVisible = false
    }

    private fun showError(error: Throwable) {
        progressSpinner.isVisible = false
        listEmptyMessage.isVisible = false
        barcodeRecycler.isVisible = false

        Timber.e(error)
        constraintLayout.showSnackbar(error.localizedMessage)
    }

    private fun onBarcodeClicked(barcode: Barcode) {
        constraintLayout.showSnackbar(barcode.name)
    }
}
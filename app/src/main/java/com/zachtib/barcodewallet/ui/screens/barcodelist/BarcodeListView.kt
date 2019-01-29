package com.zachtib.barcodewallet.ui.screens.barcodelist

import com.zachtib.barcodewallet.R
import com.zachtib.barcodewallet.ui.base.FragmentView
import org.koin.androidx.viewmodel.ext.android.viewModel

class BarcodeListView : FragmentView(R.layout.fragment_barcode_list) {

    private val viewModel: BarcodeListViewModel by viewModel()

}
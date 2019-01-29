package com.zachtib.barcodewallet.ui

import com.zachtib.barcodewallet.ui.screens.barcodelist.BarcodeListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { BarcodeListViewModel() }
}
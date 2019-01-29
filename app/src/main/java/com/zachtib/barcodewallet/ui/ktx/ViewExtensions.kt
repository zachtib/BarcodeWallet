package com.zachtib.barcodewallet.ui.ktx

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: CharSequence, length: Int = Snackbar.LENGTH_SHORT): Snackbar {
    return Snackbar.make(this, message, length).apply { show() }
}
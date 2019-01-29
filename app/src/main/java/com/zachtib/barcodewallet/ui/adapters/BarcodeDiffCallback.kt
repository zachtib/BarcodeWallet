package com.zachtib.barcodewallet.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.zachtib.barcodewallet.models.Barcode

object BarcodeDiffCallback : DiffUtil.ItemCallback<Barcode>() {
    override fun areContentsTheSame(oldItem: Barcode, newItem: Barcode): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Barcode, newItem: Barcode): Boolean {
        return oldItem.id == newItem.id
    }
}
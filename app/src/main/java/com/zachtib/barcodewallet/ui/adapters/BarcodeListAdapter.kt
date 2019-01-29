package com.zachtib.barcodewallet.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zachtib.barcodewallet.R
import com.zachtib.barcodewallet.models.Barcode
import com.zachtib.barcodewallet.ui.ktx.inflate

class BarcodeListAdapter(private val clickListener: (Barcode) -> Unit) :
    ListAdapter<Barcode, BarcodeListAdapter.ViewHolder>(BarcodeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = parent.inflate(R.layout.item_barcode)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(barcode: Barcode, clickListener: (Barcode) -> Unit) {
            TODO()
        }
    }
}
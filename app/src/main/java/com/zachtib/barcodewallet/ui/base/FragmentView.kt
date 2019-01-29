package com.zachtib.barcodewallet.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext


abstract class FragmentView(@LayoutRes private val layoutId: Int) : Fragment(), CoroutineScope, CoroutineView {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Creating ${javaClass.simpleName}")
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onDestroy() {
        Timber.d("Destroying ${javaClass.simpleName}")
        super.onDestroy()
        job.cancel()
    }

    fun <T> LiveData<T>.observe(observer: (T) -> Unit) = observe(this@FragmentView, Observer(observer))

    fun <VH : RecyclerView.ViewHolder> RecyclerView.attach(newAdapter: RecyclerView.Adapter<VH>) {
        layoutManager = LinearLayoutManager(this@FragmentView.requireContext())
        adapter = newAdapter
    }
}
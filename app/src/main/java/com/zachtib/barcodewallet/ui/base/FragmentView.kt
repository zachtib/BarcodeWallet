package com.zachtib.barcodewallet.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass


abstract class FragmentView<out T : ViewModel>(clazz: KClass<T>) : Fragment(), CoroutineScope, MvvmView {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected val viewModel: T by viewModelByClass(clazz)

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
}
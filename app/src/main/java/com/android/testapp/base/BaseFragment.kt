package com.android.testapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.android.testapp.di.util.ViewModelFactory
import com.android.testapp.ui.main.MainActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {
    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun getViewModelType(): Class<VM>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelType())
        val view = inflater.inflate(layoutRes(), container, false)
        return view
    }

    fun navigator() : MainActivity = activity as MainActivity

    override fun onResume() {
        super.onResume()
    }
}
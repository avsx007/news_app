package com.android.testapp.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.android.testapp.di.util.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM: ViewModel> : DaggerAppCompatActivity() {
    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun getViewModelType(): Class<VM>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelType())
        setContentView(layoutRes())
    }
}
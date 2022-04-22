package com.android.testapp.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * @author Abhishek
 * @since 27/11/2019
 */
class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = com.android.testapp.di.DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component
    }

}
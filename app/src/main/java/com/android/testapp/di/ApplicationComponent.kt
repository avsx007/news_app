package com.android.testapp.di

import android.app.Application
import com.android.testapp.base.BaseApplication
import com.android.testapp.di.module.ActivityBindingModule
import com.android.testapp.di.module.ApplicationModule
import com.android.testapp.di.module.ContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [ContextModule::class,AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityBindingModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

}
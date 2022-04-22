package com.android.testapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.testapp.R
import com.android.testapp.base.BaseActivity
import com.android.testapp.ui.home.HomeFragment
import com.android.testapp.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel>() {
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null

    private var homeFragment: HomeFragment? = null

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelType(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager = supportFragmentManager
        navigateToHome()
    }

    fun navigateToHome() {
       /* if (homeFragment == null)
            homeFragment = HomeFragment()
        replaceFragment(homeFragment!!, "HomeFragment", null, true)*/
    }

    private fun replaceFragment(
        fragment: Fragment,
        name: String,
        bundle: Bundle?,
        addToBackStack: Boolean
    ) {
      /*  fragmentTransaction = fragmentManager?.beginTransaction()
        fragment.arguments = bundle
        fragmentTransaction?.replace(R.id.frame, fragment)
        if (addToBackStack) {
            fragmentTransaction?.addToBackStack(name)
            fragmentTransaction?.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up);
        }
        fragmentTransaction?.commit()*/
    }


    fun clearBackStack() {
        if (fragmentManager?.backStackEntryCount != null) {
            for (i in 0 until fragmentManager?.backStackEntryCount!!) {
                fragmentManager?.popBackStack()
            }
        }
    }


    override fun onBackPressed() {
        if (fragmentManager?.backStackEntryCount!! > 0) {
            fragmentManager?.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}

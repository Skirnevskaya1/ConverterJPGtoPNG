package com.android.converterjpgtopng.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.android.converterjpgtopng.ui.RxExample
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ImageLoading : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return RxExample.newInstance()
    }
}
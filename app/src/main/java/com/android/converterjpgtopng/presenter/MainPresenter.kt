package com.android.converterjpgtopng.presenter

import com.android.converterjpgtopng.navigation.ImageLoading
import com.android.converterjpgtopng.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(ImageLoading)
    }

    fun onBackPressed() {
        router.exit()
    }
}
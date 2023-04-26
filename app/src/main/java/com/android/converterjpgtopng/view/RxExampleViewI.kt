package com.android.converterjpgtopng.view

import android.graphics.drawable.Drawable
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RxExampleViewI : MvpView {
    fun showImage(pathName: Drawable)
    fun сonvertImageInfo(it: Boolean)
    fun showProgressBar()
    fun hideProgressBar()
}
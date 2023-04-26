package com.android.converterjpgtopng.ui.activity

import android.os.Bundle
import com.android.converterjpgtopng.ConverterJPGtoPNG
import com.android.converterjpgtopng.R
import com.android.converterjpgtopng.databinding.ActivityMainBinding
import com.android.converterjpgtopng.presenter.MainPresenter
import com.android.converterjpgtopng.view.MainView
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.containerMain)

    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter { MainPresenter(ConverterJPGtoPNG.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        ConverterJPGtoPNG.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        ConverterJPGtoPNG.instance.navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment ->
            if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }
}
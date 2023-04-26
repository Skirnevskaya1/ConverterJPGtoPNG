package com.android.converterjpgtopng.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.converterjpgtopng.ConverterJPGtoPNG
import com.android.converterjpgtopng.databinding.FragmentMainBinding
import com.android.converterjpgtopng.presenter.RxExamplePresenter
import com.android.converterjpgtopng.view.RxExampleViewI
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RxExample : MvpAppCompatFragment(), RxExampleViewI {
    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    private val presenter: RxExamplePresenter by moxyPresenter {
        RxExamplePresenter(ConverterJPGtoPNG.instance.router)
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.showImageButton.setOnClickListener {
            presenter.сonvertImage()
        }
    }

    companion object {
        fun newInstance() =
            RxExample()
    }

    override fun showImage(pathName: Drawable) {
        binding.imageView.setImageDrawable(pathName)
    }

    override fun сonvertImageInfo(it: Boolean) {
        Toast.makeText(requireContext(), "Jpg converted to Png : $it", Toast.LENGTH_SHORT).show()
    }
}
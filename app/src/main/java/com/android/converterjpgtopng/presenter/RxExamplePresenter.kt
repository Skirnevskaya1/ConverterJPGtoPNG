package com.android.converterjpgtopng.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import com.android.converterjpgtopng.ConverterJPGtoPNG
import com.android.converterjpgtopng.view.RxExampleViewI
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.File
import java.io.FileOutputStream

class RxExamplePresenter(private val router: Router) : MvpPresenter<RxExampleViewI>() {

    private val JPG_FILE_NAME = "leaves.jpg"
    private val PNG_FILE_NAME = "/out.png"

    private fun getDrawbl(): Single<Drawable> {
        return Single.create() {
            var inputStream =
                ConverterJPGtoPNG.instance.applicationContext.assets?.open(JPG_FILE_NAME)
            it.onSuccess(Drawable.createFromStream(inputStream, null))
            inputStream?.close()
        }
    }

    private fun saveJpgToPng(): Single<Boolean> {
        return Single.create() {
            val inputStream =
                ConverterJPGtoPNG.instance.applicationContext.assets?.open(JPG_FILE_NAME)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            val path = ConverterJPGtoPNG.instance.applicationContext.filesDir?.path + PNG_FILE_NAME
            val file = File(path).also {
                it.createNewFile()
            }

            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream)
            outputStream.close()
            it.onSuccess(true)
        }
    }

    //Получаем Jpg, конвертируем в Png и показываем картинку
    fun сonvertImage() {
        try {
            viewState.showProgressBar()
            getDrawbl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Thread.sleep(2000)
                    viewState.hideProgressBar()
                    viewState.showImage(it)
                }, {

                })
            saveJpgToPng()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.сonvertImageInfo(it)
                }, {})
        } catch (e: Error) {
        }
    }
}


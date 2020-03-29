package com.example.enkripdekrip.presenter.dekrip

import android.util.Log
import com.example.enkripdekrip.model.dekrip.PostDekrip
import com.example.enkripdekrip.model.dekrip.ResponseDekrip
import com.example.enkripdekrip.service.BaseApi
import com.example.enkripdekrip.view.dekrip.DekripView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class PresenterDekrip(val view: DekripView, val factory: BaseApi) {

    private var mCompositeDisposable: CompositeDisposable? = null

    fun postData(text: String, key: String) {
        view.showLoading()
        val dataDekrip = PostDekrip(text, key)
        mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(
            factory.postDayaDekrip(dataDekrip)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Response<ResponseDekrip>>() {
                    override fun onComplete() {
                        view.hideLoading()
                    }

                    override fun onNext(t: Response<ResponseDekrip>) {
                        if (t.code() == 201) {
                            view.hideLoading()
                            view.showData(t.body()?.data.toString())
                            Log.d("DataDekripsi", t.body()?.data.toString())
                        } else {
                            view.hideLoading()
                            view.showToast(t.body()?.message.toString())
                        }
                    }

                    override fun onError(e: Throwable) {
                        view.hideLoading()
                        view.showToast(e.message.toString())
                    }
                })
        )
    }
}
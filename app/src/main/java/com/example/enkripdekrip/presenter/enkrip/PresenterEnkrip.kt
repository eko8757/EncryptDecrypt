package com.example.enkripdekrip.presenter.enkrip

import android.util.Log
import com.example.enkripdekrip.model.enkrip.PostEnkrip
import com.example.enkripdekrip.model.enkrip.ResponseEnkrip
import com.example.enkripdekrip.service.BaseApi
import com.example.enkripdekrip.view.enkrip.EnkripView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class PresenterEnkrip(val view: EnkripView, val factory: BaseApi) {

    private var mCompositeDisposable: CompositeDisposable? = null

    fun postData(text: String) {
        view.showLoading()
        val dataEnkrip = PostEnkrip(text)
        mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(
            factory.postDataEnkrip(dataEnkrip)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Response<ResponseEnkrip>>() {
                    override fun onComplete() {
                        view.hideLoading()
                    }

                    override fun onNext(t: Response<ResponseEnkrip>) {
                        if (t.code() == 201) {
                            view.hideLoading()
                            view.showData(t.body()?.data.toString())
                            Log.d("DataEnkrip", t.body()?.data.toString())
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
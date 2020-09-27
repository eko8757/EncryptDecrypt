package com.example.enkripdekrip.presenter.enkrip

import android.util.Log
import com.example.enkripdekrip.model.enkrip.PostEnkrip
import com.example.enkripdekrip.model.enkrip.ResponseEnkrip
import com.example.enkripdekrip.service.BaseApi
import com.example.enkripdekrip.view.MainView
import com.example.enkripdekrip.view.enkrip.EnkripView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class PresenterEnkrip(val view: EnkripView, private val mainView: MainView, val factory: BaseApi) : EnkripView.Presenter {

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onEnkrip(txt: String) {
        mainView.showLoading()
        val dataEnkrip = PostEnkrip(txt)
        mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(
            factory.postDataEnkrip(dataEnkrip)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Response<ResponseEnkrip>>() {
                    override fun onComplete() {
                        mainView.hideLoading()
                    }

                    override fun onNext(t: Response<ResponseEnkrip>) {
                        if (t.code() == 201) {
                            mainView.hideLoading()
                            view.showData(t.body()?.data.toString())
                            Log.d("DataEnkrip", t.body()?.data.toString())
                        } else {
                            mainView.hideLoading()
                            mainView.showMessage(t.body()?.message.toString())
                        }
                    }

                    override fun onError(e: Throwable) {
                        mainView.hideLoading()
                        mainView.showMessage(e.message.toString())

                    }
                })
        )
    }
}
package com.example.enkripdekrip.presenter.dekrip

import android.util.Log
import com.example.enkripdekrip.model.dekrip.PostDekrip
import com.example.enkripdekrip.model.dekrip.ResponseDekrip
import com.example.enkripdekrip.service.BaseApi
import com.example.enkripdekrip.view.MainView
import com.example.enkripdekrip.view.dekrip.DekripView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class PresenterDekrip(val view: DekripView, val mainView: MainView, private val factory: BaseApi) :
    DekripView.Presenter {

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onDekrip(txt: String) {
        mainView.showLoading()
        val dataDekrip = PostDekrip(txt)
        mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(
            factory.postDayaDekrip(dataDekrip)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Response<ResponseDekrip>>() {
                    override fun onComplete() {
                        mainView.hideLoading()
                    }

                    override fun onNext(t: Response<ResponseDekrip>) {
                        if (t.code() == 201) {
                            mainView.hideLoading()
                            view.showData(t.body()?.data.toString())
                            Log.d("DataDekripsi", t.body()?.data.toString())
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
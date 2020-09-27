package com.example.enkripdekrip.view.enkrip

interface EnkripView {

    fun checkForm()
    fun showData(data: String)

    interface Presenter {
        fun onEnkrip(txt: String) {}
    }
}
package com.example.enkripdekrip.view.dekrip

interface DekripView {
    fun checkForm()
    fun showData(data: String)

    interface Presenter {
        fun onDekrip(txt: String) {}
    }
}
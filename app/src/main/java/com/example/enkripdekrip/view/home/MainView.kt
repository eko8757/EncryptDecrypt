package com.example.enkripdekrip.view.home

interface MainView {
    interface EnkripView {
        fun showLoading()
        fun hideLoading()
        fun checkForm()
        fun showToast(msg: String)
        fun getResultData(data: String)

    }
}
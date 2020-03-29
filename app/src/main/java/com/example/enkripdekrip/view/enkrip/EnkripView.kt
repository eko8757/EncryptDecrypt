package com.example.enkripdekrip.view.enkrip

interface EnkripView {
    fun showLoading()
    fun hideLoading()
    fun checkForm()
    fun showData(data: String)
    fun showToast(msg: String)
}
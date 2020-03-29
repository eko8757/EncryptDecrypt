package com.example.enkripdekrip.view.dekrip

interface DekripView {
    fun showLoading()
    fun hideLoading()
    fun checkForm()
    fun showData(data: String)
    fun showToast(msg: String)
}
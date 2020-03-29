package com.example.enkripdekrip.view.home

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.enkripdekrip.R
import com.example.enkripdekrip.presenter.enkrip.PresenterEnkrip
import com.example.enkripdekrip.service.BaseApi

class MainActivity : AppCompatActivity(), MainView.EnkripView, View.OnClickListener {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var presenter: PresenterEnkrip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory: BaseApi = BaseApi.create()
        presenter = PresenterEnkrip(this, factory)

        //initials
        btn_encrypt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_encrypt -> checkForm()
        }
    }

    override fun showLoading() {
        progressDialog = ProgressDialog(this)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setMessage("Please wait..")
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.dismiss()
    }

    override fun checkForm() {
        val data = ed_encrypt.text.toString()
        if (data.isNotEmpty()) {
            presenter.postData(data)
        } else {
            showToast("Text cannot be empty")
        }
    }

    override fun getResultData(data: String) {
        tv_result.setText(data)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}

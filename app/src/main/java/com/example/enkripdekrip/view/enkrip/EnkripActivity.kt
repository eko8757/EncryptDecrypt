package com.example.enkripdekrip.view.enkrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.enkripdekrip.R
import com.example.enkripdekrip.presenter.enkrip.PresenterEnkrip
import com.example.enkripdekrip.service.BaseApi
import com.example.enkripdekrip.utils.gone
import com.example.enkripdekrip.utils.visible
import kotlinx.android.synthetic.main.activity_enkrip.*

class EnkripActivity : AppCompatActivity(), EnkripView, View.OnClickListener {

    private lateinit var presenterEnkrip: PresenterEnkrip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enkrip)

        val factory: BaseApi = BaseApi.create()
        presenterEnkrip = PresenterEnkrip(this, factory)

        btn_enkrip.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_enkrip -> checkForm()
        }
    }

    override fun showLoading() {
        progress_enkrip.visible()
    }

    override fun hideLoading() {
        progress_enkrip.gone()
    }

    override fun checkForm() {
        val text = ed_text_enkrip.text.toString()

        if (text.isNotEmpty()) {
            presenterEnkrip.postData(text)
        } else {
            showToast("Cannot be empty")
        }
    }

    override fun showData(data: String) {
        tv_hasil_enkrip.text = data
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

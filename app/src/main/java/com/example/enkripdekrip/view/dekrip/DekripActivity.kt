package com.example.enkripdekrip.view.dekrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.enkripdekrip.R
import com.example.enkripdekrip.presenter.dekrip.PresenterDekrip
import com.example.enkripdekrip.service.BaseApi
import com.example.enkripdekrip.utils.Commons
import com.example.enkripdekrip.utils.gone
import com.example.enkripdekrip.utils.invisible
import com.example.enkripdekrip.utils.visible
import com.example.enkripdekrip.view.MainView
import kotlinx.android.synthetic.main.activity_dekrip.*

class DekripActivity : AppCompatActivity(), DekripView, MainView, View.OnClickListener {

    private lateinit var presenter: PresenterDekrip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dekrip)

        val factory: BaseApi = BaseApi.create()
        presenter = PresenterDekrip(this, this, factory)

        btn_dekrip.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_dekrip -> checkForm()
        }
    }

    override fun showLoading() {
        progress_dekrip.visible()
        btn_dekrip.invisible()
    }

    override fun hideLoading() {
       progress_dekrip.gone()
        btn_dekrip.visible()
    }

    override fun checkForm() {
        val text = ed_text_dekrip.text.toString()
        if (text.isNotEmpty()) {
            presenter.onDekrip(text)
        } else {
            showMessage("Cannot be empty")
        }
    }

    override fun showData(data: String) {
        tv_hasil_dekrip.text = data
    }

    override fun showMessage(msg: String) {
        Commons().setToast(this, msg)
    }
}

package com.example.enkripdekrip.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.enkripdekrip.R
import com.example.enkripdekrip.view.dekrip.DekripActivity
import com.example.enkripdekrip.view.enkrip.EnkripActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_menu_enkrip.setOnClickListener(this)
        btn_menu_dekrip.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_menu_enkrip -> {
                startActivity(Intent(this, EnkripActivity::class.java))
            }

            R.id.btn_menu_dekrip -> {
                startActivity(Intent(this, DekripActivity::class.java))
            }
        }
    }
}

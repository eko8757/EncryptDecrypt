package com.example.enkripdekrip.utils

import android.content.Context
import android.widget.Toast

class Commons {

    fun setToast(ctx: Context, msg: String) : String {
        return Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show().toString()
    }
}
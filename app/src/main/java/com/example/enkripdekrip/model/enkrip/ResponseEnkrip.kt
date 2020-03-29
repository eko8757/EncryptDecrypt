package com.example.enkripdekrip.model.enkrip

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseEnkrip {

    @SerializedName("data")
    @Expose
    var data: String? = null

    @SerializedName("message")
    @Expose
    var message : String? = null
}
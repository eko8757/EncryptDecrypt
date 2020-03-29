package com.example.enkripdekrip.model.dekrip

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseDekrip {

    @SerializedName("data")
    @Expose
    var data: String? = null

    @SerializedName("message")
    @Expose
    var message : String? = null
}
package com.example.enkripdekrip.model.enkrip

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostEnkrip(

    @SerializedName("text")
    @Expose
    var text: String?
)
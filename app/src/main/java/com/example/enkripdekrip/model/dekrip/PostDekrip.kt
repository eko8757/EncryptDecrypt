package com.example.enkripdekrip.model.dekrip

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostDekrip(

    @SerializedName("text")
    @Expose
    var text: String?
)
package com.example.mysecondapplication.objects

import com.google.gson.annotations.SerializedName

open class Item {
    @SerializedName("_id")
    var id = ""

    fun imageValue() = "https://cataas.com/cat/$id"
}
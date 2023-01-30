package com.android.acromine.data.pojo

import com.google.gson.annotations.SerializedName

data class VarsItem(

    @field:SerializedName("freq")
    val freq: Int,

    @field:SerializedName("lf")
    val lf: String,

    @field:SerializedName("since")
    val since: Int
)
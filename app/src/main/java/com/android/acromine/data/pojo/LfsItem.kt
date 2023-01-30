package com.android.acromine.data.pojo

import com.google.gson.annotations.SerializedName

data class LfsItem(

    @field:SerializedName("freq")
    val freq: Int,

    @field:SerializedName("lf")
    val lf: String,

    @field:SerializedName("vars")
    val vars: List<VarsItem>,

    @field:SerializedName("since")
    val since: Int
)
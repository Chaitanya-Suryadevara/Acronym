package com.android.acromine.data.pojo

import com.google.gson.annotations.SerializedName

data class LongFormResponse(

    @field:SerializedName("sf")
    val sf: String,

    @field:SerializedName("lfs")
    val lfs: List<LfsItem>
)
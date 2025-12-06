package com.example.schoolproj.model

import com.squareup.moshi.Json

data class School(
    val dbn : String,
    @Json(name="school_name") val schoolName : String? = null,
    val borough:String? = null,
    val website:String? = null,
    val location:String? = null,
    @Json(name="overview_paragraph") val overview:String? = null
)

package com.example.schoolproj.network

import com.example.schoolproj.model.SatResult
import com.example.schoolproj.model.School
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools():List<School>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSatResults() : List<SatResult>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSatByDbn(@Query("dbn")dbn: String):List<SatResult>

}
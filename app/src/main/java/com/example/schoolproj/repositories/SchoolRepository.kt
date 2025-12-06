package com.example.schoolproj.repositories

import com.example.schoolproj.model.SatResult
import com.example.schoolproj.model.School
import com.example.schoolproj.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton



data class SchoolWithSat (
    val school: School,
    val sat: SatResult? = null
)


@Singleton
class SchoolRepository @Inject constructor(private val api : ApiService) {

    suspend fun getSchools(): List<School> = api.getSchools()

    suspend fun getSatResults():List<SatResult> = api.getSatResults()

    suspend fun getSchoolsWithSat():List<SchoolWithSat> {
        val schools = api.getSchools()
        val sats = api.getSatResults()
        val satByDbn = sats.associateBy { it.dbn }
        return  schools.map { s -> SchoolWithSat(s,satByDbn[s.dbn]) }
    }

    suspend fun getSatForDbn(dbn: String) : SatResult?{
        val listS = api.getSatByDbn(dbn)
        val value = listS.firstOrNull()
        return value
    }

}
package com.example.schoolproj

import com.example.schoolproj.model.SatResult
import com.example.schoolproj.model.School
import com.example.schoolproj.network.ApiService
import com.example.schoolproj.repositories.SchoolRepository
import com.example.schoolproj.repositories.SchoolWithSat
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class SchoolRepositoryTest {
    private lateinit var apiService: ApiService
    private lateinit var repository: SchoolRepository

    @Before
    fun setup() {
        apiService = mockk()
        repository = SchoolRepository(apiService)
    }

    @Test
    fun `test getSchoolsWithSat returns merged data`() = runBlocking {
        val mockSchools = listOf(
            School(dbn = "01A", schoolName = "Test School A"),
            School(dbn = "02B", schoolName = "Test School B")
        )

        val mockSatResults = listOf(
            SatResult(dbn = "01A", sat_math_avg_score = "500")
        )

        coEvery { apiService.getSchools() } returns mockSchools
        coEvery { apiService.getSatResults() } returns mockSatResults

        val result: List<SchoolWithSat> = repository.getSchoolsWithSat()

        assertEquals(2, result.size)
        assertEquals("01A", result[0].school.dbn)
        assertEquals("500", result[0].sat?.sat_math_avg_score)
        assertEquals(null, result[1].sat)
    }

}
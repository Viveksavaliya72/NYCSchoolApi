package com.example.schoolproj

import com.example.schoolproj.model.SatResult
import com.example.schoolproj.model.School
import com.example.schoolproj.repositories.SchoolRepository
import com.example.schoolproj.repositories.SchoolWithSat
import com.example.schoolproj.viewModel.SchoolViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SchoolViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    private lateinit var repo: SchoolRepository
    private lateinit var viewModel: SchoolViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repo = mockk()
    }

    @Test
    fun `viewModel loads schools and emits Success`() = runTest {
        val mockData = listOf(
            SchoolWithSat(
                school = School(dbn = "01A", schoolName = "ABC"),
                sat = SatResult(dbn = "01A")
            )
        )

        coEvery { repo.getSchoolsWithSat() } returns mockData

        viewModel = SchoolViewModel(repo)

        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is SchoolViewModel.UiState.Success)
    }

    @Test
    fun `viewModel emits Error when repository throws`() = runTest {
        coEvery { repo.getSchoolsWithSat() } throws RuntimeException("API Failed")

        viewModel = SchoolViewModel(repo)

        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is SchoolViewModel.UiState.Error)
    }

}
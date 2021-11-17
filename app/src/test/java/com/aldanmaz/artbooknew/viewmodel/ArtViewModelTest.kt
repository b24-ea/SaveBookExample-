package com.aldanmaz.artbooknew.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aldanmaz.artbooknew.MainCoroutineRule
import com.aldanmaz.artbooknew.getOrAwaitValue

import com.aldanmaz.artbooknew.repo.FakeArtRepository
import com.aldanmaz.artbooknew.repository.ArtRepository
import com.aldanmaz.artbooknew.util.Status

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup() {
        //Test Doubles


        viewModel = ArtViewModel(FakeArtRepository())

    }

    @Test
    fun `insert art without year returns error`() {
           viewModel.makeArt("Mona Lisa","Da Vinci","")
        val value = viewModel.insertArtMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)


    }
    @Test
    fun `insert art without name returns error`() {
        viewModel.makeArt("","Da Vinci","1700")
        val value = viewModel.insertArtMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert art without artistName returns error`() {
        viewModel.makeArt("Mona Lisa","","1700")
        val value = viewModel.insertArtMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}
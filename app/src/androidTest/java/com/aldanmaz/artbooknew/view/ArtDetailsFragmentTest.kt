package com.aldanmaz.artbooknew.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.aldanmaz.artbooknew.R
import com.aldanmaz.artbooknew.getOrAwaitValueTest
import com.aldanmaz.artbooknew.launchFragmentInHiltContainer
import com.aldanmaz.artbooknew.repo.FakeArtRepositoryTest
import com.aldanmaz.artbooknew.roomdb.Art
import com.aldanmaz.artbooknew.viewmodel.ArtViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    lateinit var viewModel: ArtViewModel

    @Before
    fun setup() {
        hiltRule.inject()
    }
    @Test
    fun testNavigationFromArtDetailsToImageAPI() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.artImageView)).perform(click())

        Mockito.verify(navController).navigate(
            ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
        )

    }
        @Test
        fun testOnBackPress() {
            val navController = Mockito.mock(NavController::class.java)

            launchFragmentInHiltContainer<ArtDetailsFragment>(
                factory = fragmentFactory
            ) {
                Navigation.setViewNavController(requireView(),navController)
            }
            Espresso.pressBack()
            Mockito.verify(navController).popBackStack()
        }

    @Test
    fun testSave() {
        val testViewModel = ArtViewModel(FakeArtRepositoryTest())
        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ) {
             viewModel = testViewModel
        }
        Espresso.onView(withId(R.id.nameText)).perform(replaceText("Mona Lisa"))
        Espresso.onView(withId(R.id.artistText)).perform(replaceText("Da Vinci"))
        Espresso.onView(withId(R.id.yearText)).perform(replaceText("1500"))
        Espresso.onView(withId(R.id.button)).perform(click())


        assertThat(testViewModel.artList.getOrAwaitValueTest()).contains(
            Art("Mona Lisa","Da Vinci",1500, "")
        )

    }
}
package com.moqod.stackoverflowinfo

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gobuzzvault.android.viewmodel.TrampolineSchedulerRule
import com.moqod.stackoverflowinfo.network.tags.TagsRepository
import com.moqod.stackoverflowinfo.observable.SingleLiveEvent
import com.moqod.stackoverflowinfo.rx.SchedulerProvider
import com.moqod.stackoverflowinfo.ui.tags.TagsFragmentDirections
import com.moqod.stackoverflowinfo.ui.tags.TagsViewModel
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TagsViewModelTest {

    private lateinit var tagsViewModel: TagsViewModel

    @Mock
    private lateinit var mockNavigator: AppNavigator

    @Mock
    private lateinit var tagsRepository: TagsRepository


    // Replace all used schedulers with Trampoline version
    @get:Rule
    val rule = TrampolineSchedulerRule()

    // Enable MutableLiveData
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val onDataSourceCreatedMock: SingleLiveEvent<Void> = mock()

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        tagsViewModel = Mockito.spy(TagsViewModel(Mockito.mock(Application::class.java)))

        tagsViewModel.compositeDisposable = CompositeDisposable()
        tagsViewModel.schedulerProvider = SchedulerProvider()

        tagsViewModel.setNavigator(mockNavigator)

        tagsViewModel.tagsRepo = tagsRepository

        tagsViewModel.onDataSourceCreated = onDataSourceCreatedMock
    }

    @Test
    fun getTags() {

        tagsViewModel.getTags()

        verify(onDataSourceCreatedMock, Mockito.times(1)).call()
    }

    @Test
    fun onItemClick() {

        val tag = "someTag"

        tagsViewModel.onItemClick(tag)

        verify(mockNavigator, Mockito.times(1)).navigateTo(TagsFragmentDirections.actionAccountsFragmentToPostsFragment(tag))
    }


}
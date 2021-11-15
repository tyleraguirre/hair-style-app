package com.example.haircutapp

import android.app.Application
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedViewModelTest {

    @Mock
    private lateinit var mockContext: Application

    @Test
    fun whenFavoritedIsTrue(){
        val vm = SharedViewModel(mockContext)
        val hairstyle = StyleList.styleList.get(2)
        val method = vm.isFavorited(hairstyle)
        assertTrue(method)
    }
}
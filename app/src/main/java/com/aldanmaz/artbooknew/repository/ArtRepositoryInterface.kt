package com.aldanmaz.artbooknew.repository

import androidx.lifecycle.LiveData
import com.aldanmaz.artbooknew.model.ImageResponse
import com.aldanmaz.artbooknew.roomdb.Art
import com.aldanmaz.artbooknew.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art : Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>
}
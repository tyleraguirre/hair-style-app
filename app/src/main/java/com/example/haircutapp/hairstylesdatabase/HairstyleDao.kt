package com.example.haircutapp.hairstylesdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HairstyleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(style: List<Hairstyle>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingeHairstyle(style: Hairstyle)

    @Update
    suspend fun update(style: Hairstyle)


    @Query("SELECT * FROM hairstyle_table WHERE hairstyleId = :key ")
    suspend fun get(key: Long): Hairstyle

    @Query("SELECT * FROM hairstyle_table ORDER BY hairstyleId DESC")
    fun getAllHairstyles(): LiveData<List<Hairstyle>>

    @Query("SELECT * FROM hairstyle_table WHERE favorited = :favorited")
    suspend fun getFavorited(favorited: Boolean): Hairstyle
}
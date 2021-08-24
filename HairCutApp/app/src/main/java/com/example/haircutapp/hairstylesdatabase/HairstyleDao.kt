package com.example.haircutapp.hairstylesdatabase

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HairstyleDao {

    @Insert
    suspend fun insert(style: Hairstyle)

    @Update
    suspend fun update(style: Hairstyle)

    @Query("SELECT * FROM hairstyle_table WHERE hairstyleId = :key ")
    fun get(key: Long): Hairstyle

    @Query("SELECT * FROM hairstyle_table ORDER BY hairstyleId DESC")
    fun getAllHairstyles(): List<Hairstyle>
}
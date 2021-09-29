package com.example.haircutapp.dummydatabase

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface DummyDatabaseDao {
    @Insert
    suspend fun insert(dummy: Dummy)
}
package com.example.haircutapp.hairstylesdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Hairstyle::class], version = 1, exportSchema = false)
abstract class HairstyleDatabase : RoomDatabase() {

    abstract val HairstyleDao: HairstyleDao


    companion object {

        @Volatile
        private var INSTANCE: HairstyleDatabase? = null

        fun getInstance(context: Context) : HairstyleDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HairstyleDatabase::class.java,
                        "hairstyle_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}
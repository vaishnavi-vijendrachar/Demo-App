package com.vaishnavi.telstratest.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vaishnavi.telstratest.model.Facts

@Database(entities = [Facts::class], version = 1,exportSchema = false)
abstract class FactsDatabase : RoomDatabase() {
    abstract fun factsDao(): FactsDao
    companion object {
        private var INSTANCE: FactsDatabase? = null
        fun getInstance(context: Context): FactsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context
                    ).also { INSTANCE = it }
            }

        private fun buildDatabase(context : Context) =
            Room.databaseBuilder(context.applicationContext,
                FactsDatabase::class.java,"Facts.db").build()
    }
}
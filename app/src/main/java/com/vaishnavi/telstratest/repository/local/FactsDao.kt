package com.vaishnavi.telstratest.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vaishnavi.telstratest.model.Facts

@Dao
interface FactsDao{
    @Query("SELECT * FROM Facts")
     fun getFacts(): LiveData<List<Facts>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertFact(facts: Facts)
}
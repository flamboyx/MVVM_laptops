package com.example.mvvmlaptops.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LaptopsDao {
    @Insert
    suspend fun insert(laptop: Laptops)

    @Delete
    suspend fun delete(laptop: Laptops)

    @Update
    suspend fun update(laptop: Laptops)

    @Query("SELECT * FROM LAPTOPS")
    suspend fun getAllLaptops() : MutableList<Laptops>

    @Query("SELECT * FROM LAPTOPS WHERE id = :id")
    suspend fun getLaptopById(id: Int) : Laptops

    @Query("DELETE FROM LAPTOPS")
    suspend fun clearTable();
}
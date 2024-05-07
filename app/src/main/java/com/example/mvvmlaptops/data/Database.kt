package com.example.mvvmlaptops.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Laptops::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract fun getLaptopsDao() : LaptopsDao
}

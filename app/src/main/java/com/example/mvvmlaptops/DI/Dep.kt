package com.example.mvvmlaptops.DI

import android.content.Context
import androidx.room.Room
import com.example.mvvmlaptops.data.Database
import com.example.mvvmlaptops.data.Repository
import com.example.mvvmlaptops.domain.IRepository

object Dep {
    private lateinit var applicationContext: Context
    private val repository : IRepository = Repository()

    public fun getRepository() : IRepository {
        return repository
    }

    fun init(context: Context) {
        applicationContext = context
    }

    val db: Database by lazy {
        Room.databaseBuilder(applicationContext, Database::class.java, "database.db")
            .build()
    }
}

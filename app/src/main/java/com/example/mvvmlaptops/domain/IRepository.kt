package com.example.mvvmlaptops.domain

interface IRepository {
    public suspend fun add(model: LaptopModel);
    public suspend fun clearTable();
    public suspend fun getAllLaptops() : MutableList<LaptopModel>;
    public suspend fun generateTestData()
}
package com.example.mysecondapplication.datalayer

import com.example.mysecondapplication.objects.Item
import retrofit2.http.GET

interface IItemAccessor2 {
	@GET("/api/cats?skip=0&limit=100")
	suspend fun items2(): List<Item>
}
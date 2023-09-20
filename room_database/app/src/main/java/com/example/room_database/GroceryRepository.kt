package com.example.room_database

class GroceryRepository(private val db:GroceryDatabase) {
    suspend fun insert(items : GroceryItems) = db.getGroceryDao().inser(items)
    suspend fun delte(items : GroceryItems) = db.getGroceryDao().delete(items)
     fun getAllItems() = db.getGroceryDao().getAllGroceryItems()
}

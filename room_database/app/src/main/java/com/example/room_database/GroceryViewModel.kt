package com.example.room_database

import android.provider.Settings.Global
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {
     fun insert(items : GroceryItems)  {
        GlobalScope.launch {
            repository.insert(items)
        }
    }

    fun delete(items : GroceryItems) {
        GlobalScope.launch {
            repository.delte(items)
        }
    }

    fun getAllRepositoryItems() = repository.getAllItems()
}
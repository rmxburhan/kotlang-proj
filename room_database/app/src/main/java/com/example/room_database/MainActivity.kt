package com.example.room_database

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , GroceryAdapter.GroceryItemClickInterface {
    private lateinit var binding : ActivityMainBinding
    private lateinit var list : List<GroceryItems>
    private lateinit var  adapter : GroceryAdapter
    private lateinit var groceryViewModel: GroceryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList<GroceryItems>()
        adapter = GroceryAdapter(list, this)
        binding.listGrocery.adapter = adapter
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)
        groceryViewModel.getAllRepositoryItems().observe(this, Observer {
            adapter.list = it
            adapter.notifyDataSetChanged()
        })
        binding.btnAddGrocery.setOnClickListener {
            openDialog()
        }
    }

    fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_dialog)
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
        val btnAdd = dialog.findViewById<Button>(R.id.btnAdd)
        val edtItemname = dialog.findViewById<EditText>(R.id.edtItemName)
        val edtItemPrice = dialog.findViewById<EditText>(R.id.edtItemPrice)
        val edtItemQuantity = dialog.findViewById<EditText>(R.id.edtItemQuantity)
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        btnAdd.setOnClickListener {
            val itemName : String = edtItemname.text.toString()
            val itemPrice : String = edtItemPrice.text.toString()
            val itemQuantity : String = edtItemQuantity.text.toString()
            val qty : Int = itemQuantity.toInt()
            val price : Int = itemPrice.toInt()
            if (itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()) {
                groceryViewModel.insert(GroceryItems(itemName, itemPrice = price, itemQuantity = qty))
                Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Filled out all data", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show()
    }
}
package com.example.room_database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.text.DecimalFormat

class GroceryAdapter(var list: List<GroceryItems>, val groceryItemClickInterface: GroceryItemClickInterface) :
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    interface GroceryItemClickInterface {
        fun onItemClick(groceryItems: GroceryItems)
    }

    class GroceryViewHolder(itemView : View) : ViewHolder(itemView){
        var txtName = itemView.findViewById<TextView>(R.id.txtName)
        var txtRate = itemView.findViewById<TextView>(R.id.txtRate)
        var txtQuantity = itemView.findViewById<TextView>(R.id.txtQuantity)
        var txtAmount = itemView.findViewById<TextView>(R.id.txtTtotalAmt)
        var btnDelete = itemView.findViewById<ImageView>(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grocery, parent, false)
        return GroceryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val data= list.get(position)
        with(holder) {
            txtName.setText(data.itemName)
            txtQuantity.setText(data.itemQuantity.toString())
            txtRate.setText("Rp. " + DecimalFormat("#,###").format(data.itemPrice))
            val itemTotal = data.itemPrice * data.itemQuantity
            txtAmount.setText("Rp. " + itemTotal)
            btnDelete.setOnClickListener {
                groceryItemClickInterface.onItemClick(data)
            }
        }
    }
}
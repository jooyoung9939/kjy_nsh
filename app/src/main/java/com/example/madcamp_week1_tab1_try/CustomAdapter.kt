package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val context: Context,
    private val onItemLongClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val itemList = ArrayList<Item>()
    private var filteredItemList = ArrayList<Item>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameItem: TextView = itemView.findViewById(R.id.nameItem)
        val numItem: TextView = itemView.findViewById(R.id.numItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item

//        if (filteredItemList.isEmpty()) {
//            item = itemList[position]
//        } else {
//            item = filteredItemList[position]
//        }

        item = filteredItemList[position]

        holder.nameItem.text = item.name
        holder.numItem.text = item.num

        holder.itemView.setOnLongClickListener {
            onItemLongClickListener.invoke(holder.adapterPosition)
            true
        }
    }

    override fun getItemCount(): Int {
        return if (filteredItemList.isEmpty()) {
            filteredItemList.size
        } else {
            filteredItemList.size
        }
    }

    fun filter(text: String) {
        filteredItemList.clear()

        if (text.isEmpty()) {
            filteredItemList.addAll(itemList)
        } else {
            val searchText = text.toLowerCase()
            for (item in itemList) {
                if (item.name.toLowerCase().contains(searchText) || item.num.toLowerCase().contains(searchText)) {
                    filteredItemList.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }

    fun addItem(name: String, num: String) {
        itemList.add(Item(name, num))
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyDataSetChanged()
    }
    fun setFilteredItemList(items: List<Item>) {
        filteredItemList.clear()
        filteredItemList.addAll(items)
        notifyDataSetChanged()
    }
    data class Item(val name: String, val num: String)
}
package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val context: Context,
    private val onClick: (position: Int) -> (Unit)
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    data class Item(val name: String, val num: String, val centerTag: String)

    val itemList = ArrayList<Item>()
    val filteredProfileList = ArrayList<Item>()
    private var filteredItemList = ArrayList<Item>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameItem: TextView = itemView.findViewById(R.id.nameItem)
        val numItem: TextView = itemView.findViewById(R.id.numItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.member_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item

        item = filteredItemList[position]

        holder.nameItem.text = item.name
        holder.numItem.text = item.num
        Log.d("ckn","결과: $item.name")

        holder.itemView.setOnClickListener {
            onClick.invoke(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return filteredItemList.size
        Log.d("88888","결과: $filteredItemList.size")

    }

    fun filter(text: String) {
        filteredItemList.clear()

        if (text.isEmpty()) {
            filteredItemList.addAll(filteredProfileList)
        } else {
            val searchText = text.toLowerCase()
            for (item in filteredProfileList) {
                if (item.name.toLowerCase().contains(searchText) || item.num.toLowerCase().contains(searchText)) {
                    filteredItemList.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }


    fun profileFilter(centerTagFilter: String) {
        filteredProfileList.clear()

        // Filter profiles based on centerTagFilter
        filteredProfileList.addAll(itemList.filter { it.centerTag == centerTagFilter })

        filteredItemList.clear()
        filteredItemList.addAll(filteredProfileList)

        notifyDataSetChanged()
    }


    fun addItem(name: String, num: String, centerTag: String) {
        itemList.add(Item(name, num, centerTag))

        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        filteredItemList.clear()
        filteredItemList.addAll(itemList)
        notifyDataSetChanged()
    }
    fun setFilteredItemList() {
        filteredItemList.clear()
        filteredItemList.addAll(filteredProfileList)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Item {
        return filteredItemList[position]
    }
}
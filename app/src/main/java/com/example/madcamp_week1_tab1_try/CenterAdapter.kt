package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CenterAdapter(
    private val context: Context,
    private val onClick: (position: Int) -> (Unit)
) : RecyclerView.Adapter<CenterAdapter.ViewHolder>() {
    data class centerItem(val centerName: String, val centerNum: String, val centerTag: String, val centerPW: String)

    val centerItemList = ArrayList<centerItem>()
    private var filteredCenterItemList = ArrayList<centerItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameItem: TextView = view.findViewById(R.id.centerNameItem)
        val numItem: TextView = view.findViewById(R.id.centerNumItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: centerItem

        item = filteredCenterItemList[position]

        holder.nameItem.text = item.centerName
        holder.numItem.text = item.centerNum

        holder.itemView.setOnClickListener {
            onClick.invoke(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return filteredCenterItemList.size
    }

    fun filter(text: String) {
        filteredCenterItemList.clear()

        if (text.isEmpty()) {
            filteredCenterItemList.addAll(centerItemList)
        } else {
            val searchText = text.toLowerCase()
            for (item in centerItemList) {
                if (item.centerName.toLowerCase().contains(searchText) || item.centerNum.toLowerCase().contains(searchText)) {
                    filteredCenterItemList.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }

    fun addItem(centerName: String, centerNum: String, centerTag: String, centerPW: String) {
        centerItemList.add(centerItem(centerName, centerNum, centerTag, centerPW))
        notifyDataSetChanged()
    }

    fun setFilteredItemList(items: List<centerItem>) {
        filteredCenterItemList.clear()
        filteredCenterItemList.addAll(items)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): centerItem {
        return filteredCenterItemList[position]
    }
}
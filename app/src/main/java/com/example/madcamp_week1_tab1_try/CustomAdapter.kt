package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(private val context: Context) : BaseAdapter() {

    private val nameList = ArrayList<String>()
    private val numList = ArrayList<String>()

    override fun getCount(): Int {
        return nameList.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        val nameItem: TextView = itemView.findViewById(R.id.nameItem)
        val numItem: TextView = itemView.findViewById(R.id.numItem)

        nameItem.text = nameList[position]
        numItem.text = numList[position]
        return itemView
    }

    fun addItem(name: String, num: String) {
        nameList.add(name)
        numList.add(num)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        nameList.removeAt(position)
        numList.removeAt(position)
        notifyDataSetChanged()
    }
}

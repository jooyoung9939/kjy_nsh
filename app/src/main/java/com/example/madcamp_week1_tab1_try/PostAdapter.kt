package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(
    private val context: Context,
    private val onClick: (position: Int) -> (Unit)
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    data class postItem(val postTitle: String, val postText: String)

    val postItemList = ArrayList<postItem>()
    private var filteredPostItemList = ArrayList<postItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleItem: TextView = view.findViewById(R.id.postTitleItem)
        val textItem: TextView = view.findViewById(R.id.postTextItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: postItem

        item = filteredPostItemList[position]

        holder.titleItem.text = item.postTitle
        holder.textItem.text = item.postText

        holder.itemView.setOnClickListener {
            onClick.invoke(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return filteredPostItemList.size
    }

    fun filter(text: String) {
        filteredPostItemList.clear()

        if (text.isEmpty()) {
            filteredPostItemList.addAll(postItemList)
        } else {
            val searchText = text.toLowerCase()
            for (item in postItemList) {
                if (item.postTitle.toLowerCase().contains(searchText)) {
                    filteredPostItemList.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }

    fun addItem(postTitle: String, postText: String) {
        postItemList.add(postItem(postTitle, postText))
        notifyDataSetChanged()
    }

    fun setFilteredItemList() {
        filteredPostItemList.clear()
        filteredPostItemList.addAll(postItemList)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): postItem {
        return filteredPostItemList[position]
    }
}
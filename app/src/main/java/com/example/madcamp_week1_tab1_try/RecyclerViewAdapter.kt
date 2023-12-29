package com.example.madcamp_week1_tab1_try

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private val datas: JSONObject): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val listStore = datas.getJSONArray("store_list")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStore)
    }

    override fun getItemCount(): Int {
        return listStore.length()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStoreNm: TextView = view.findViewById(R.id.name)
        val tvStoreImg: ImageView = view.findViewById(R.id.img_url)

        fun bind(listStore: JSONArray) {
            val iObj = listStore.getJSONObject("$position".toInt())
            val name = iObj.getString("name")
            var imgUrl = iObj.getString("image_url")

            tvStoreNm.text = name
            Glide.with(itemView)
                .load("http:"+ imgUrl)
                .circleCrop()
                .into(tvStoreImg)
        }
    }
}
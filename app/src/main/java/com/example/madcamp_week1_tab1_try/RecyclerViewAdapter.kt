package com.example.madcamp_week1_tab1_try

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

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
        val tvStorePhone: TextView = view.findViewById(R.id.phone_Num)

        fun bind(listStore: JSONArray) {
            val iObj = listStore.getJSONObject("$position".toInt())
            val name = iObj.getString("name")
            val phoneNum = iObj.getString("phone_num")
            var imgUrl = iObj.getString("image_url")

            tvStoreNm.text = name
            tvStorePhone.text = phoneNum

            Glide.with(itemView)
                .load(imgUrl)
                .circleCrop()
                .into(tvStoreImg)
        }
    }
}

//class RecyclerViewAdapter(private val items: ArrayList<UserData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
//    override fun getItemCount(): Int {
//        return items.count()
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.name.text = items[position].name
//        holder.phone_Num.text = items[position].phone_num
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        return RecyclerViewAdapter.ViewHolder(inflatedView)
//    }
//
//    // 각 항목에 필요한 기능을 구현
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val name = itemView.findViewById<TextView>(R.id.name)
//        val phone_Num = itemView.findViewById<TextView>(R.id.phone_Num)
//    }
//}
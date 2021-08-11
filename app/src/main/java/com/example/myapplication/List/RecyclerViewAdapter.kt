package com.example.myapplication.List

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.RecyclerData
import com.squareup.picasso.Picasso

/**
 * 将数据与每一个item绑定
 *
 * 负责recycler view与数据直接交互的部分
 */
class RecyclerViewAdapter() :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    //数据源
    private var items = ArrayList<RecyclerData>()

    fun setUpdatedData(items: ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    /**
     * View Holder
     */
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val tvDesc = view.findViewById<TextView>(R.id.tvDesc)

        fun bind(data: RecyclerData) {
            tvTitle.text = data.name
            tvDesc.text = data.description

            val url = data.owner.avatar_url

            Picasso.get()
                .load(url)
                .into(imageThumb)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)

        return MyViewHolder(view)
    }

    /**
     * 返回item个数
     */
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

}


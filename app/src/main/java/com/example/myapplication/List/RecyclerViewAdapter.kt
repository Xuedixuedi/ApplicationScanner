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
 *
 * adapter 内部需要有内部类：ViewHolder
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
     *
     * 是recyclerView和adapter的中间人
     */
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // viewHolder中只进行一次findViewById,将这些作为成员变量保存
        private val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val tvDesc = view.findViewById<TextView>(R.id.tvDesc)

        //数据绑定，接受的参数是一个recyclerView的card的数据
        fun bind(data: RecyclerData) {
            tvTitle.text = data.name
            tvDesc.text = data.description

            val url = data.owner.avatar_url //处理图片

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

    /**
     * Adapter 与 ViewHolder 绑定
     *
     * 输入viewHolder和位置，viewHolder负责控制数据与位置的绑定
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

}


package com.example.infedistest.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infedistest.R
import com.example.infedistest.data.model.NewsResponse
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter(private val context: Context?, private val mData: List<NewsResponse.Articles>?) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = mData?.get(position)
        if (news != null) {
            holder.title_news.text = news.title
            holder.date_news.text = getFormattedDate(news.publishedAt)
            context?.let {
                Glide.with(it)
                    .load(news.urlToImage)
                    .placeholder(android.R.drawable.ic_media_play)
                    .into(holder.img_news)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getFormattedDate(date: String): String {
        val curFormater = SimpleDateFormat("yyyy-MM-dd")
        val dateObj: Date = curFormater.parse(date)

        val newDateStr: String = curFormater.format(dateObj)
        return newDateStr
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mData?.size!!
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title_news: TextView = itemView.findViewById(R.id.title_news)
        var date_news: TextView = itemView.findViewById(R.id.date_news)
        var img_news: ImageView = itemView.findViewById(R.id.img_news)
    }
}
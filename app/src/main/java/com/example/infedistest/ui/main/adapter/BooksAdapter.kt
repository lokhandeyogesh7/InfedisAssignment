package com.example.infedistest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infedistest.R
import com.example.infedistest.data.model.booksresponse.Item
import java.util.*


class BooksAdapter(private val context: Context?, private val mData: List<Item>?) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.books_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = mData?.get(position)
        if (news != null) {
            holder.title_book.text = news.volumeInfo.title
            if (!news.volumeInfo.authors.isNullOrEmpty()) {
                holder.author_book.text =
                    Arrays.asList(news.volumeInfo.authors).joinToString { it.toString() }
            }
            holder.published_book.text = news.volumeInfo.publishedDate
            holder.ratings_book.text = news.volumeInfo.averageRating.toString()
            context?.let { context ->
                if (news.volumeInfo.imageLinks != null) {
                    Glide.with(context)
                        .load(news.volumeInfo.imageLinks.thumbnail)
                        .placeholder(android.R.drawable.ic_media_play)
                        .into(holder.img_books)
                }
            }
        }
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
        var title_book: TextView = itemView.findViewById(R.id.title_book)
        var author_book: TextView = itemView.findViewById(R.id.author_book)
        var published_book: TextView = itemView.findViewById(R.id.published_book)
        var ratings_book: TextView = itemView.findViewById(R.id.ratings_book)
        var img_books: ImageView = itemView.findViewById(R.id.img_book)
    }
}
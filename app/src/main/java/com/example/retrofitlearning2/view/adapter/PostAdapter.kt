package com.example.retrofitlearning2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlearning2.R
import com.example.retrofitlearning2.model.Post
import kotlinx.android.synthetic.main.list_post.view.*

class PostAdapter(private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.apply {
                val data = "userId: ${post.userId}\n" +
                        "id: ${post.id}\n" +
                        "title: ${post.title}\n" +
                        "body: ${post.body}"

                dataTv.text = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.list_post, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount() = postList.size
}
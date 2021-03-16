package com.example.retrofitlearning2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlearning2.R
import com.example.retrofitlearning2.model.Comment
import kotlinx.android.synthetic.main.list_post.view.*

class CommentAdapter(private val commentList: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: Comment) {
            itemView.apply {
                val data = "postId: ${comment.postId}\n" +
                        "id: ${comment.id}\n" +
                        "name: ${comment.name}\n" +
                        "email: ${comment.email}\n" +
                        "body: ${comment.body}"

                dataTv.text = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.list_post, parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount() = commentList.size
}
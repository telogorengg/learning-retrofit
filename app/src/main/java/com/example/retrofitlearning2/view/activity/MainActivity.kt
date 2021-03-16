package com.example.retrofitlearning2.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitlearning2.R
import com.example.retrofitlearning2.api.RetrofitBuilder
import com.example.retrofitlearning2.model.Comment
import com.example.retrofitlearning2.model.Post
import com.example.retrofitlearning2.view.adapter.CommentAdapter
import com.example.retrofitlearning2.view.adapter.PostAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var postAdapter: PostAdapter
    private lateinit var commentAdapter: CommentAdapter

    private val postList = ArrayList<Post>()
    private val commentList = ArrayList<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //showPosts()
        //showUserPosts()
        //showUserPostsWithMap()
        //showComments()
        //showCommentsByUrl()
        //createPost()
        //updatePost()
        //deletePost()
    }

    //Show all of posts
    private fun showPosts() {
        postRv.setHasFixedSize(true)
        postRv.layoutManager = LinearLayoutManager(this)

        RetrofitBuilder.instance.getPosts().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                responseCodeTv.text = response.code().toString()

                response.body()?.let {
                    postList.addAll(it)
                }

                postAdapter = PostAdapter(postList)
                postRv.adapter = postAdapter
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                responseCodeTv.text = t.message
            }

        })
    }

    //Show specific user post
    private fun showUserPosts() {
        postRv.setHasFixedSize(true)
        postRv.layoutManager = LinearLayoutManager(this)

        RetrofitBuilder.instance.getUserPosts(4, 32).enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                responseCodeTv.text = response.code().toString()

                response.body()?.let {
                    postList.addAll(it)
                }

                postAdapter = PostAdapter(postList)
                postRv.adapter = postAdapter
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                responseCodeTv.text = t.message
            }
        })
    }

    //Show specific user post with QueryMap
    private fun showUserPostsWithMap() {
        postRv.setHasFixedSize(true)
        postRv.layoutManager = LinearLayoutManager(this)

        val data = HashMap<String, String>()
        data["userId"] = "4"
        data["id"] = "32"

        RetrofitBuilder.instance.getUserPostsWithMap(data).enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                responseCodeTv.text = response.code().toString()

                response.body()?.let {
                    postList.addAll(it)
                }

                postAdapter = PostAdapter(postList)
                postRv.adapter = postAdapter
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                responseCodeTv.text = t.message
            }
        })
    }

    //Show comments from specific userId
    private fun showComments() {
        postRv.setHasFixedSize(true)
        postRv.layoutManager = LinearLayoutManager(this)

        RetrofitBuilder.instance.getComments(2).enqueue(object : Callback<ArrayList<Comment>> {
            override fun onResponse(
                call: Call<ArrayList<Comment>>,
                response: Response<ArrayList<Comment>>
            ) {
                responseCodeTv.text = response.code().toString()

                response.body()?.let {
                    commentList.addAll(it)
                }

                commentAdapter = CommentAdapter(commentList)
                postRv.adapter = commentAdapter
            }

            override fun onFailure(call: Call<ArrayList<Comment>>, t: Throwable) {
               responseCodeTv.text = t.message
            }
        })
    }

    //Show comments from specific userId with Url
    private fun showCommentsByUrl() {
        postRv.setHasFixedSize(true)
        postRv.layoutManager = LinearLayoutManager(this)

        RetrofitBuilder.instance.getCommentsWithUrl("posts/2/comments").enqueue(object : Callback<ArrayList<Comment>> {
            override fun onResponse(
                call: Call<ArrayList<Comment>>,
                response: Response<ArrayList<Comment>>
            ) {
                responseCodeTv.text = response.code().toString()

                response.body()?.let {
                    commentList.addAll(it)
                }

                commentAdapter = CommentAdapter(commentList)
                postRv.adapter = commentAdapter
            }

            override fun onFailure(call: Call<ArrayList<Comment>>, t: Throwable) {
                responseCodeTv.text = t.message
            }

        })
    }

    //Create new post
    private fun createPost() {
        RetrofitBuilder.instance.createPost(
            27,
            "Retrofit Tutorial",
            "This tutorial is for beginner"
        ).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val responseText = "Response code: ${response.code()}\n" +
                        "userId: ${response.body()?.userId}\n" +
                        "id: ${response.body()?.id}\n" +
                        "title: ${response.body()?.title}\n" +
                        "body: ${response.body()?.body}"

                responseCodeTv.text = responseText
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                responseCodeTv.text = t.message
            }
        })
    }

    //Update post
    private fun updatePost() {
        RetrofitBuilder.instance.putPosts(
            5,
            9,
            6,
            null,
            "halo ini adalah text yang sudah diedit"
        ).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val responseText = "Response code: ${response.code()}\n" +
                        "userId: ${response.body()?.userId}\n" +
                        "id: ${response.body()?.id}\n" +
                        "title: ${response.body()?.title}\n" +
                        "body: ${response.body()?.body}"

                responseCodeTv.text = responseText
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                responseCodeTv.text = t.message
            }

        })
    }

    //Delete post
    private fun deletePost() {
        RetrofitBuilder.instance.deletePost(1).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                responseCodeTv.text = response.code().toString()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                responseCodeTv.text = t.message
            }

        })
    }
}
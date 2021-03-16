package com.example.retrofitlearning2.api

import com.example.retrofitlearning2.model.Comment
import com.example.retrofitlearning2.model.Post
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<ArrayList<Post>>

    //GET with Query. Example: posts?userId=1
    @GET("posts")
    fun getUserPosts(
        @Query("userId") userId: Int,
        @Query("id") id: Int
    ): Call<ArrayList<Post>>

    //GET with QueryMap. It's a little bit similar with Query, but you can handle more than one query into a Map
    @GET("posts")
    fun getUserPostsWithMap(
        @QueryMap data: HashMap<String, String>
    ): Call<ArrayList<Post>>

    //GET with Path. Example: posts/1/comments
    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<ArrayList<Comment>>

    //GET with Url. We can use url to GET
    @GET
    fun getCommentsWithUrl(@Url url: String): Call<ArrayList<Comment>>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<Post>

    @FormUrlEncoded
    @PUT("posts/{id}")
    fun putPosts(
        @Path("id") id: Int,
        @Field("userId") userId: Int,
        @Field("id") idField: Int,
        @Field("title") title: String?,
        @Field("body") body: String?
    ): Call<Post>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun patchPosts(
        @Path("id") id: Int,
        @Field("userId") userId: Int,
        @Field("id") idField: Int,
        @Field("title") title: String?,
        @Field("body") body: String?
    ): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Void>
}
package com.example.blog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blog.adapters.PostsAdapter
import com.example.blog.data.api.ApiService
import com.example.blog.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.recyclerview.widget.RecyclerView
import com.example.blog.data.api.Instance

class MainActivity : AppCompatActivity() {
    private lateinit var postAdapter: PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.postsList

        postAdapter = PostsAdapter(emptyList())
        recyclerView.adapter = postAdapter

        GlobalScope.launch {
            val posts = Instance.api.getPosts()
            withContext(Dispatchers.Main) {
                postAdapter = PostsAdapter(posts)
                recyclerView.adapter = postAdapter
            }
        }
    }
}
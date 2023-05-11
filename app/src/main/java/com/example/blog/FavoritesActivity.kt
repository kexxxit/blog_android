package com.example.blog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blog.adapters.FavoritesPostsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.blog.databinding.ActivityFavoritesBinding


class FavoritesActivity : AppCompatActivity() {
    private lateinit var postAdapter: FavoritesPostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.postList
        val db = MainActivity.db

        postAdapter = FavoritesPostsAdapter(emptyList())
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val posts = db.postDao().getAllFavoritePosts()
            withContext(Dispatchers.Main) {
                postAdapter = FavoritesPostsAdapter(posts)
                recyclerView.adapter = postAdapter
            }
        }
    }
}

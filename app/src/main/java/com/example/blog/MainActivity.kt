package com.example.blog

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blog.adapters.PostsAdapter
import com.example.blog.data.api.ApiService
import com.example.blog.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.blog.data.api.Instance
import com.example.blog.data.database.AppDatabase
import com.example.blog.data.model.LoginResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var db: AppDatabase
    }
    lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.postList
        swipeRefreshLayout = binding.swipeRefreshLayout
        APP = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-db"
        ).build()

        postAdapter = PostsAdapter(emptyList())
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            setPosts()
        }

        swipeRefreshLayout.setOnRefreshListener {
            postAdapter = PostsAdapter(emptyList())
            GlobalScope.launch {
                setPosts()
            }
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
            }
            R.id.delete -> {
                GlobalScope.launch {
                    db.postDao().deleteFavoritePosts()
                }
            }
        }
        return true
    }

    private suspend fun setPosts() {

            val posts = Instance.api.getPosts()
            withContext(Dispatchers.Main) {
                postAdapter = PostsAdapter(posts.reversed())
                binding.postList.adapter = postAdapter
            }

    }
}

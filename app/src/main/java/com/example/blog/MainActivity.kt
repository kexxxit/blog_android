package com.example.blog

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
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
    private lateinit var postAdapter: PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.postList
        APP = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-db"
        ).build()

        postAdapter = PostsAdapter(emptyList())
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val posts = Instance.api.getPosts()
            withContext(Dispatchers.Main) {
                postAdapter = PostsAdapter(posts.reversed())
                recyclerView.adapter = postAdapter
            }
        }
    }
}

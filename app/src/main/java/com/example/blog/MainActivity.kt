package com.example.blog

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
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
import com.example.blog.data.database.TokenDatabase
import com.example.blog.data.database.entities.Token
import com.example.blog.data.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var db: TokenDatabase
        private set

    private lateinit var postAdapter: PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.postsList


        db = TokenDatabase.getInstance(this)

        postAdapter = PostsAdapter(emptyList())
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val posts = Instance.api.getPosts()

            withContext(Dispatchers.Main) {
                postAdapter = PostsAdapter(posts)
                recyclerView.adapter = postAdapter
            }

            try {
                val response = Instance.api.getUser()
                var isAdmin: Int? = response.user?.isAdmin
                var email: String? = response.user?.email
                print(email)
                runOnUiThread {
                    binding.textView2.text = email
                    if (isAdmin == 1) {
                        binding.NewPostForm.root.visibility= View.VISIBLE

                        binding.loginButton.visibility = View.GONE
                    }
                    binding.textView2.visibility = View.VISIBLE
                }
            } catch (e: HttpException) {
                if (e.code() == 404) {

                } else {

                }
            }
        }
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

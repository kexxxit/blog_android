package com.example.blog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
                val response = Instance.api.login("kvashevich80@gmail.com", "root")
                var token: String? = response.data?.get(0)?.token
                var isAdmin: Int? = response.data?.get(1)?.isAdmin
                var email: String? = response.data?.get(1)?.email
                db.TokenDao().insertToken(Token(authToken = token, isAdmin = isAdmin))
                runOnUiThread {
                    binding.textView2.text = email
                    if (isAdmin == 1) {
                        binding.NewPostForm.root.visibility= View.VISIBLE
                        binding.textView2.visibility = View.VISIBLE
                        binding.loginButton.visibility = View.GONE
                    }
                }
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    // Выполняем действия для обработки ошибки 404
                    // Например, показываем сообщение об ошибке
                } else {
                    // Обрабатываем другие типы ошибок
                }
            }
        }
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
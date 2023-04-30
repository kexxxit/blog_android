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
import com.example.blog.databinding.ActivityAuthBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var db: TokenDatabase
        private set
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = TokenDatabase.getInstance(this)


        /*GlobalScope.launch {
            try {
                val response = Instance.api.login("kvashevich80@gmail.com", "root1")
                var token: String? = response.data?.get(0)?.token
                var isAdmin: Int? = response.data?.get(1)?.isAdmin
                var email: String? = response.data?.get(1)?.email
                db.TokenDao().insertToken(Token(authToken = token, isAdmin = isAdmin))
                runOnUiThread {
                    binding.textView2.text = email
                    if (isAdmin == 1) {
                        binding.NewPostForm.root.visibility= View.VISIBLE
                        binding.textView2.visibility = View.VISIBLE
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
        }*/
    }
}

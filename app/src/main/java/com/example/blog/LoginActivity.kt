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
        val intent = Intent(this, MainActivity::class.java)
        db = TokenDatabase.getInstance(this)

        binding.loginButton.setOnClickListener {
            GlobalScope.launch {
                try {
                    val response = Instance.api.login(binding.Email.text.toString(), binding.Password.text.toString())
                    var token: String? = response.data?.get(0)?.token
                    var isAdmin: Int? = response.data?.get(1)?.isAdmin
                    var email: String? = response.data?.get(1)?.email
                    db.TokenDao().insertToken(Token(authToken = token, isAdmin = isAdmin))
                    runOnUiThread {
                        binding.textView.text = email

                    }

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                } catch (e: HttpException) {
                    if (e.code() == 404) {
                        runOnUiThread {
                            binding.textView.text = "Не верно введена почта или пароль"
                        }
                    } else {
                        // Обрабатываем другие типы ошибок
                    }
                }
            }
        }
    }
}

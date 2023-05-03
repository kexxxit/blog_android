package com.example.blog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.blog.APP
import com.example.blog.MainActivity
import com.example.blog.data.api.Instance
import com.example.blog.data.database.FavoritePost
import com.example.blog.databinding.PostItemBinding
import com.example.blog.data.model.Post
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        print("posts")
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.postText.text = post.postText
            if (post.postImg != null) {
                binding.postImg.visibility = View.VISIBLE
                Picasso.get().load(post.postImg).into(binding.postImg)
            } else {
                binding.postImg.visibility = View.GONE
            }
            binding.addToFavoritesButton.setOnClickListener {
                val favoritePost = FavoritePost(
                    postId = post.postId,
                    postImg = post.postImg,
                    postText = post.postText
                )
                GlobalScope.launch {
                    val dao = MainActivity.db.postDao()
                    dao.insertFavoritePost(favoritePost)
                }
                Toast.makeText(APP, "Запись добавлена в избранные", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

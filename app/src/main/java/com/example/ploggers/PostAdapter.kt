package com.example.ploggers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ploggers.databinding.ItemPostBinding

class PostAdapter(val post:List<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = post.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(post[position])

    }

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post:Post) {

            Glide.with(binding.root.context)
                .load(post.images[0]) // 이미지 URL
                .into(binding.postImg)

            binding.postWriting.text = post.content
        }
    }


}
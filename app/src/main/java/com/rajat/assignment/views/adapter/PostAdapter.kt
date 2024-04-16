package com.rajat.assignment.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rajat.assignment.databinding.AdapterPostListBinding
import com.rajat.assignment.models.PostsItem
import javax.inject.Inject


class PostAdapter @Inject constructor() : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : AdapterPostListBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<PostsItem>() {
        override fun areItemsTheSame(oldItem: PostsItem, newItem: PostsItem): Boolean {

            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PostsItem, newItem: PostsItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterPostListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postItems = differ.currentList[position]
        holder.binding.apply {
            tvId.text = postItems.id.toString()
            tvUserId.text = postItems.userId.toString()
            tvTitle.text = postItems.title
            tvBody.text = postItems.body
        }

        holder.itemView.setOnClickListener {
            println("item_clicked")
            setPostClickListener?.let {
                it(postItems)
            }
        }
    }

    private var setPostClickListener : ((postsItem: PostsItem)->Unit)? =null

    fun onPostClicked(listener:(PostsItem)->Unit){
        setPostClickListener =listener
    }
}
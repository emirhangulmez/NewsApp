package com.emirhangulmez.newsapp.presentation.news.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.emirhangulmez.newsapp.common.Extensions.getOrWriteNull
import com.emirhangulmez.newsapp.common.Extensions.makeGone
import com.emirhangulmez.newsapp.common.Extensions.makeVisible
import com.emirhangulmez.newsapp.databinding.ItemNewsBinding
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import com.emirhangulmez.newsapp.domain.entity.NewsArg
import com.emirhangulmez.newsapp.presentation.news.NewsFragmentDirections

class NewsAdapter : PagingDataAdapter<ArticleEntity, NewsAdapter.NewsAdapterVH>(ArticleDiff) {

    class NewsAdapterVH(private val binding: ItemNewsBinding) : ViewHolder(binding.root) {
        fun bind(item: ArticleEntity) = with(binding) {
            titleTv.text = item.title
            descriptionTv.text = item.description
            sourceNameTv.text = item.sourceName

            root.setOnClickListener {
                Navigation.findNavController(itemView).navigate(
                    NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(
                        NewsArg(item.url)
                    )
                )
            }

            if (item.urlToImage.isNotEmpty()) {
                //Image Handling
                val imageListener = object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Toast.makeText(
                            itemView.context,
                            e?.message.getOrWriteNull(),
                            Toast.LENGTH_SHORT
                        ).show()
                        newsPictureIv.makeVisible()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        newsPictureIv.makeVisible()
                        progressBar.makeGone()
                        return false
                    }
                }

                Glide.with(itemView.context)
                    .load(item.urlToImage)
                    .listener(imageListener)
                    .centerCrop()
                    .into(newsPictureIv)
            } else {
                progressBar.makeGone()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapterVH =
        NewsAdapterVH(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: NewsAdapterVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    object ArticleDiff : DiffUtil.ItemCallback<ArticleEntity>() {
        override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean =
            oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean =
            oldItem.url == newItem.url
    }
}
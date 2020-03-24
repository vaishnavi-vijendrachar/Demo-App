package com.vaishnavi.telstratest.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vaishnavi.telstratest.R

data class Facts( val title : String, val description : String?, val imageHref : String?){
    companion object {
        @BindingAdapter("image")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String?) {
                Glide.with(imageView.context)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_background)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
        }
    }
}
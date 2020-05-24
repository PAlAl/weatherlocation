package ru.test.weather.ui.global.images

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.request.RequestOptions
import ru.test.weather.R

object ImageLoader {

    private const val defaultRequestTimeout = 10000

    private fun getImageRequestOptions(requestTimeOut: Int, placeholderRes: Int, errorRes: Int): RequestOptions {
        return RequestOptions()
                .timeout(requestTimeOut)
                .placeholder(placeholderRes)
                .error(errorRes)
    }

    fun simpleLoad(view: View, url: String, imageView: ImageView, requestTimeOut: Int = defaultRequestTimeout,
                   @DrawableRes placeholderRes: Int = R.drawable.ic_image_loading_placeholder, @DrawableRes errorRes: Int = R.drawable.ic_image_error) {
        GlideApp.with(view)
                .setDefaultRequestOptions(getImageRequestOptions(requestTimeOut, placeholderRes, errorRes))
                .load(Uri.parse(url))
                .into(imageView)
    }
}
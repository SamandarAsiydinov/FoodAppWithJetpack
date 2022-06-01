package com.example.mvvmrecipes.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.mvvmrecipes.R

const val Empty_image = R.drawable.img_empty

@ExperimentalMaterialApi
@Composable
fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    val context = LocalContext.current

    Glide.with(context)
        .asBitmap()
        .load(defaultImage)
        .into(customTarget(bitmapState))

    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(customTarget(bitmapState))

    return bitmapState
}

fun customTarget(bitmapState: MutableState<Bitmap?>) = object : CustomTarget<Bitmap>() {
    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
        bitmapState.value = resource
    }

    override fun onLoadCleared(placeholder: Drawable?) {}
}
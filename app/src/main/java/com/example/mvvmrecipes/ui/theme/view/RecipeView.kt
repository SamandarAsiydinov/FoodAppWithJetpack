package com.example.mvvmrecipes.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipes.model.Recipe
import com.example.mvvmrecipes.util.Empty_image
import com.example.mvvmrecipes.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun RecipeView(
    recipe: Recipe
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            val image = loadPicture(url = recipe.featuredImage, defaultImage = Empty_image).value
            image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Text(
                        text = recipe.title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = recipe.rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(CenterVertically),
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = "Updated ${recipe.dateUpdated} by ${recipe.publisher}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.caption
                    )
                    for (ing in recipe.ingredients) {
                        Text(
                            text = ing,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}
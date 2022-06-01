package com.example.mvvmrecipes.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipes.R

@Composable
fun NothingHere() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_empty),
                contentDescription = "Empty",
                modifier = Modifier
                    .height(75.dp)
                    .width(75.dp)
            )
            Text(
                text = "There's nothing here",
                modifier = Modifier
                    .padding(8.dp)
                    .align(CenterHorizontally),
                style = MaterialTheme.typography.h4
            )
        }
    }
}
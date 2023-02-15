package com.weiran.lottery.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import com.weiran.lottery.R

@Composable
fun Home() {
    Box {
        InputLottery()
        BackGround()
    }
}

@Composable
private fun BackGround() {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(-1F),
        painter = painterResource(R.mipmap.all_hero),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputLottery() {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            label = { Text(text = PLEASE_INPUT_KEY) },
            onValueChange = { text = it },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            trailingIcon = {
                IconButton(onClick = {}) { Icon(Icons.Filled.Search, null) }
            }
        )
    }
}

private const val PLEASE_INPUT_KEY = "输入队伍密钥"
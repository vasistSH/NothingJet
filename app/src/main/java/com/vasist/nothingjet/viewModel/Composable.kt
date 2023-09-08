package com.vasist.nothingjet.viewModel

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vasist.nothingjet.ui.theme.NothingJetTheme


@Composable
fun reComposableEg(){
    val state = remember { mutableStateOf(0.0) }
    Log.d( "Tagged " ,"initial sate : ")
    Button(onClick = {state.value = Math.random() }) {
        Text(text = state.value.toString())
        Log.d( "Tagged " ,"log during both composition and recomposition ")
    }
}

@Composable
fun notificationScreen(){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        notificationCounter()
    }
}

@Composable
fun Themes(){
    val theme = remember { mutableStateOf(false) }

    NothingJetTheme (theme.value){
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(Dp(40f))) {
            Text(text = "Android",
                style = MaterialTheme.typography.bodyLarge)
            Button(onClick = { theme.value = !theme.value }) {
                Text(text = "change theme")
            }
        }
    }
}

@Composable
fun notificationCounter(){

    val count : MutableState<Int> = rememberSaveable{mutableStateOf(0)}
        Column(verticalArrangement = Arrangement.Center){
            Text(text = "updated data${count.value}")
            Button(onClick = { count.value++
                Log.d("notification","button clicked")
            }) {
                Text(text = "send notification")
            }
        }

}

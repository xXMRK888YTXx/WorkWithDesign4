package com.xxmrk888ytxx.workwithdesign3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "main") {


                composable("main") {

                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = { navController.navigate("Screen1") }) {
                            Text(text = "Screen1")
                        }

                        Button(
                            onClick = { navController.navigate("Screen2") }
                        ) {
                            Text(text = "Screen2")
                        }

                        Button(onClick = { navController.navigate("Screen3") }) {
                            Text(text = "Screen3")
                        }
                    }
                }

                composable("Screen1") {
                    Screen1()
                }

                composable("Screen2") {
                    Screen2()
                }

                composable("Screen3") {
                    Screen3()
                }
            }
        }
    }
}

@Composable
fun Screen3() {

    var alpha by rememberSaveable() {
        mutableStateOf(1f)
    }

    val animatedAlpha by animateFloatAsState(targetValue = alpha, animationSpec = tween(250))

    Column() {
        SubcomposeAsyncImage(
            model =  "https://w.forfun.com/fetch/eb/ebaaaecd899acabd32eac34e2f8cac68.jpeg",
            contentDescription = "",
            modifier = Modifier.size(300.dp),
            loading = {
                CircularProgressIndicator()
            },
            alpha = animatedAlpha
        )

        Button(onClick = {
            alpha = minOf(alpha + 0.1f,1f)
        }) {
            Text(text = "Add alpha")
        }

        Button(onClick = {
            alpha = maxOf(alpha - 0.1f,0f)
        }) {
            Text(text = "Remove alpha")
        }
    }
}


@Composable
fun Screen2() {
    val viewList = remember {
        mutableStateListOf(
            "https://w.forfun.com/fetch/eb/ebaaaecd899acabd32eac34e2f8cac68.jpeg",
        )
    }


    Column(Modifier.fillMaxSize()) {
        LazyRow(Modifier.fillMaxWidth()) {
            items(viewList) {
                SubcomposeAsyncImage(
                    model = it,
                    contentDescription = "",
                    modifier = Modifier.size(300.dp),
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }
        }

        Button(
            onClick = {
                viewList.add("https://w.forfun.com/fetch/eb/ebaaaecd899acabd32eac34e2f8cac68.jpeg")
            }
        ) {
            Text(text = "Add")
        }

        Button(onClick = {
            viewList.removeAt(viewList.lastIndex)
        }) {
            Text(text = "Remove")
        }
    }

}


@Composable
fun Screen1() {

    var isHidden by rememberSaveable() {
        mutableStateOf(false)
    }

    Column(
        Modifier.fillMaxSize()
    ) {

        if(isHidden) {
            Text(text = "Hidden")
        } else {
            SubcomposeAsyncImage(
                model = "https://w.forfun.com/fetch/eb/ebaaaecd899acabd32eac34e2f8cac68.jpeg",
                contentDescription = "",
                modifier = Modifier.size(300.dp),
                loading = { CircularProgressIndicator() }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterHorizontally)
        ) {



            Button(onClick = { isHidden = false }) {
                Text(text = "Show")
            }
            
            Button(onClick = { isHidden = true }) {
                Text(text = "Hide")
            }
        }
    }
}
/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }

}

@Composable
fun DogList(dogs: List<Dog>) {
    val selectDog: (Long) -> Unit = { dogId: Long ->
//        Toast.makeText(this@MainActivity, " $dogId", Toast.LENGTH_SHORT).show()
        Log.e("tag", "         -click")
    }
    Column(modifier = Modifier
        .background(Color.LightGray)
        .padding(12.dp)
        .verticalScroll(
            rememberScrollState()
        )) {
        dogs.forEach { dog ->
            DogRow(dog, selectDog)
        }
    }
}

@Composable
fun DogRow(dog: Dog, onDogClick : (Long) -> Unit) {
        Column(modifier = Modifier
            .background(Color.Blue, MaterialTheme.shapes.medium)
            .padding(12.dp)
            .clickable { onDogClick(dog.id) }
            ) {
    //        Divider(
    //            Modifier.padding(vertical = 12.dp, horizontal = 12.dp),
    //            color = Color.White
    //        )
            Image(
                painterResource(id = R.mipmap.p1), "",
                modifier = Modifier.padding(12.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Inside
            )
            Text(text = dog.image)
            Spacer(Modifier.height(24.dp))
            Text(text = dog.name)
        }
}


@Preview
@Composable
fun DogListPreview() {
    DogList(dogs = listOf(Dog(1, "dog1", "dog1"), Dog(2,"dog1", "dog2"), Dog(3,"dog1", "dog3")))
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Ready... Set... GO!")
    }
}

//@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

data class Dog(var id : Long, var image : String, var name : String)
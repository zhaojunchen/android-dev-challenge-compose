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

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.utils.EdgeToEdgeUtils.setEdgeWithLightTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEdgeWithLightTheme(/*isSystemInDarkTheme()*/true)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    WelcomePage()
}

@Composable
fun WelcomePage() {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary)) {
        Image(painter = rememberVectorPainter(ImageVector.vectorResource(if (isSystemInDarkTheme()) R.drawable.ic_dark_welcome_bg else R.drawable.ic_welcome_bg)),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null)
        WelcomeContent()
    }
}

@Composable
fun WelcomeContent() {
    Column {
        LeafImage()
        Spacer(Modifier.height(48.dp))
        WelcomeTitle()
        Spacer(Modifier.height(40.dp))
        WelcomeButton()
    }

}


@Composable
fun LeafImage() {
    Image(painter = rememberVectorPainter(ImageVector.vectorResource(if (isSystemInDarkTheme()) R.drawable.ic_dark_welcome_illos else R.drawable.ic_welcome_illos)),
        contentDescription = null,
        modifier = Modifier.wrapContentSize().padding(top = 72.dp, start = 88.dp)
    )
}

@Composable
fun WelcomeTitle() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberVectorPainter(ImageVector.vectorResource(if (isSystemInDarkTheme()) R.drawable.ic_dark_logo else R.drawable.ic_logo)),
            contentDescription = null,
        )

        Text("Beautiful home garden solutions")
    }
}

@Composable
fun WelcomeButton() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {},
            modifier = Modifier.fillMaxWidth().height(48.dp).padding(horizontal = 16.dp)
                .clip(shape = shapes.medium),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)) {
            Text("Create account", style = MaterialTheme.typography.button)
        }

        TextButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).wrapContentWidth()) {
            Text(text = "Log in", modifier = Modifier.padding(top = 12.dp),
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onPrimary, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun MockWelcomePageLight() {
    MyTheme(darkTheme = false) {
        WelcomePage()
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MockWelcomePageDark() {
    MyTheme(darkTheme = true) {
        WelcomePage()
    }
}
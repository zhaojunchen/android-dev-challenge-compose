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
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.utils.EdgeToEdgeUtils.setEdgeWithLightTheme

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEdgeWithLightTheme(/*isSystemInDarkTheme()*/true)
        setContent {
            MyTheme {
                HomePage()
            }
        }
    }

    data class ImageItem(val name: String, @IdRes val resId: Int)

    @Composable
    fun HomePage() {
        Scaffold(bottomBar = {


        }
        ) {
            it.calculateBottomPadding()
            LoginContent()

        }
    }

    @Composable
    fun BottomBar(paddingBottom: Dp = 0.dp) {
        BottomNavigation(modifier = Modifier.fillMaxWidth().height(56.dp)
            .background(MaterialTheme.colors.primary)) {

            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_dark_logo),
                contentDescription = null
            )
        }

    }


    @Composable
    fun LoginContent() {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            LoginTitle()
            LoginInput()
            Spacer(Modifier.height(16.dp))
            LogininfoContent()
            Spacer(Modifier.height(16.dp))
            LoginButtonContent()
        }

    }

    @Composable
    fun LoginTitle() {
        Text("Log in with email",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.paddingFromBaseline(top = 184.dp, bottom = 16.dp))
    }

    @Composable
    fun LoginInput() {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            LoginEditFeild("Email address")
            Spacer(modifier = Modifier.height(8.dp))
            LoginEditFeild("Password (8+ characters)")
        }
    }

    @Composable
    fun LoginEditFeild(hint: String) {
        OutlinedTextField(value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().height(56.dp).clip(shape = shapes.small)
                .padding(horizontal = 16.dp),
            placeholder = {
                Text(text = hint,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface)
            })
    }

    @Composable
    fun LogininfoContent() {
        Text(modifier = Modifier.fillMaxWidth().padding(16.dp).padding(horizontal = 6.dp),
            color = MaterialTheme.colors.onBackground,
            text = "By click below, you agree to our _Terms of Use_ and consent to our _Privacy Policy_")
    }

    @Composable
    fun LoginButtonContent() {
        Button(onClick = {},
            modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 16.dp)
                .clip(shapes.medium),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)) {
            Text(text = "Log in",/*
                modifier = Modifier.wrapContentSize(),*/
                style = MaterialTheme.typography.button)
        }
    }


    @Preview(uiMode = UI_MODE_NIGHT_NO)
    @Composable
    fun MockWelcomePageLight() {
        MyTheme(darkTheme = false) {
            HomePage()
        }
    }


    @Preview(uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun MockWelcomePageDark() {
        MyTheme(darkTheme = true) {
            HomePage()
        }
    }
}

